package de.metas.invoice.model.validator;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;

/*
 * #%L
 * de.metas.swat.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;
import org.adempiere.ad.modelvalidator.annotations.Init;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.adempiere.invoice.service.IInvoiceBL;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Product_Acct;
import org.compiere.model.ModelValidator;

import de.metas.adempiere.model.I_C_Invoice;
import de.metas.adempiere.model.I_C_InvoiceLine;
import de.metas.adempiere.model.I_C_Order;
import de.metas.adempiere.service.IInvoiceLineBL;
import de.metas.product.acct.api.IProductAcctDAO;

@Validator(I_C_InvoiceLine.class)
@Callout(I_C_InvoiceLine.class)
public class C_InvoiceLine
{
	@Init
	public void setupCallouts()
	{
		// Setup callout C_InvoiceLine
		final IProgramaticCalloutProvider calloutProvider = Services.get(IProgramaticCalloutProvider.class);
		// this will also serve as acallout class
		calloutProvider.registerAnnotatedCallout(this);
	}

	/**
	 * Set QtyInvoicedInPriceUOM, just to make sure is up2date.
	 */
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void setQtyInvoicedInPriceUOM(final I_C_InvoiceLine invoiceLine)
	{
		Services.get(IInvoiceLineBL.class).setQtyInvoicedInPriceUOM(invoiceLine);
	}

	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW })
	public void updateIsReadOnly(final I_C_InvoiceLine invoiceLine)
	{
		Services.get(IInvoiceBL.class).updateInvoiceLineIsReadOnlyFlags(InterfaceWrapperHelper.create(invoiceLine.getC_Invoice(), I_C_Invoice.class), invoiceLine);
	}

	/**
	 * Set the product as soon as the order line is set
	 * 
	 * @param invoiceLine
	 * @param field
	 */
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID })
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_CHANGE, ModelValidator.TYPE_BEFORE_NEW }, ifColumnsChanged = I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID)
	public void onOrderLineSet(final I_C_InvoiceLine invoiceLine)
	{
		final I_C_OrderLine orderLine = invoiceLine.getC_OrderLine();

		if (orderLine == null)
		{
			// set the product to null if the orderline was set to null
			invoiceLine.setM_Product(null);

			// in case the c_orderline_id is removed, make sure the ASI is also removed. The user can set it manually
			invoiceLine.setM_AttributeSetInstance_ID(-1);

			// in case the c_orderline_id is removed, make sure the flag is on false. The user can set it on true, manually
			invoiceLine.setIsPackagingMaterial(false);

			return;
		}

		// set the product
		final I_M_Product product = orderLine.getM_Product();

		invoiceLine.setM_Product(product);

		// set the attribute set instance

		Services.get(IAttributeSetInstanceBL.class).cloneASI(invoiceLine, orderLine);

		// set isPackagingMaterial
		final de.metas.interfaces.I_C_OrderLine ol = InterfaceWrapperHelper.create(invoiceLine.getC_OrderLine(), de.metas.interfaces.I_C_OrderLine.class);

		invoiceLine.setIsPackagingMaterial(ol.isPackagingMaterial());
	}
	
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_C_Order_ID })
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_CHANGE, ModelValidator.TYPE_BEFORE_NEW }, ifColumnsChanged = I_C_InvoiceLine.COLUMNNAME_C_Order_ID)
	public void onOrderSet(final I_C_InvoiceLine invoiceLine)
	{
		final I_C_Order order = invoiceLine.getC_Order();

		if (order == null)
		{
			invoiceLine.setC_OrderLine_ID(-1);
		}
	}

	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_M_Product_ID })
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = { I_C_InvoiceLine.COLUMNNAME_M_Product_ID })
	public void updateActivity(final I_C_InvoiceLine invoiceLine)
	{
		if (invoiceLine.getC_Activity_ID() > 0)
		{
			return; // was already set, so don't try to auto-fill it
		}

		if (invoiceLine.getM_Product_ID() <= 0)
		{
			return;
		}

		final I_M_Product product = invoiceLine.getM_Product();
		final I_M_Product_Acct productAcct = Services.get(IProductAcctDAO.class).retrieveProductAcctOrNull(product);
		if (productAcct != null)
		{
			invoiceLine.setC_Activity_ID(productAcct.getC_Activity_ID());
		}
	}

	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_QtyEntered,
			I_C_InvoiceLine.COLUMNNAME_M_Product_ID,
			I_C_InvoiceLine.COLUMNNAME_PriceEntered,
			I_C_InvoiceLine.COLUMNNAME_PriceActual,
			I_C_InvoiceLine.COLUMNNAME_QtyInvoicedInPriceUOM })
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = { I_C_InvoiceLine.COLUMNNAME_QtyEntered,
			I_C_InvoiceLine.COLUMNNAME_M_Product_ID,
			I_C_InvoiceLine.COLUMNNAME_PriceEntered,
			I_C_InvoiceLine.COLUMNNAME_PriceActual,
			I_C_InvoiceLine.COLUMNNAME_QtyInvoicedInPriceUOM })
	public void setQtyInvoicedInPriceUOM_AndLineNetAMT(final I_C_InvoiceLine invoiceLine)
	{
		if (invoiceLine == null)
		{
			// nothing to do
			return;
		}

		final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
		final IInvoiceBL invoiceBL = Services.get(IInvoiceBL.class);

		invoiceLineBL.setQtyInvoicedInPriceUOM(invoiceLine);

		invoiceBL.setLineNetAmt(invoiceLine);
	}

	/**
	 * update prices on ASI change
	 * 
	 * @param invoiceLine
	 * @param field
	 */
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_M_AttributeSetInstance_ID })
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = { I_C_InvoiceLine.COLUMNNAME_M_AttributeSetInstance_ID })
	public void onASIChange(final I_C_InvoiceLine invoiceLine)
	{
		Services.get(IInvoiceLineBL.class).updatePrices(invoiceLine);
	}
}
