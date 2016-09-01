package de.metas.invoice.model.interceptor;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.invoice.service.IInvoiceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_Product;
import org.compiere.model.ModelValidator;

import de.metas.adempiere.model.I_C_Invoice;
import de.metas.adempiere.model.I_C_InvoiceLine;
import de.metas.invoice.IInvoiceLineBL;

/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2016 metas GmbH
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
@Interceptor(I_C_InvoiceLine.class)
@Callout(I_C_InvoiceLine.class)
public class C_InvoiceLine
{
	public static final C_InvoiceLine INSTANCE = new C_InvoiceLine();

	private C_InvoiceLine()
	{
	};


	/**
	 *
	 * @param invoiceLine
	 * @task https://github.com/metasfresh/metasfresh/issues/346
	 */
	@ModelChange(timings = ModelValidator.TYPE_BEFORE_CHANGE, ifColumnsChanged = { I_C_InvoiceLine.COLUMNNAME_PriceEntered, I_C_InvoiceLine.COLUMNNAME_Discount })
	@CalloutMethod(skipIfCopying=true, columnNames = { I_C_InvoiceLine.COLUMNNAME_PriceEntered, I_C_InvoiceLine.COLUMNNAME_Discount })
	public void updatePriceActual(final I_C_InvoiceLine invoiceLine)
	{
		final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
		invoiceLineBL.calculatePriceActual(invoiceLine, -1);
	}

	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_QtyEntered,
			I_C_InvoiceLine.COLUMNNAME_M_Product_ID,
			I_C_InvoiceLine.COLUMNNAME_PriceEntered,
			I_C_InvoiceLine.COLUMNNAME_PriceActual,
			I_C_InvoiceLine.COLUMNNAME_QtyInvoicedInPriceUOM })
	public void setQtyInvoicedInPriceUOM_AndLineNetAMT(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
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
	public void onASIChange(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		Services.get(IInvoiceLineBL.class).updatePrices(invoiceLine);

	}

	/**
	 * Set the product as soon as the order line is set
	 *
	 * @param invoiceLine
	 * @param field
	 */
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID })
	public void setProduct(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		if (InterfaceWrapperHelper.isNull(invoiceLine, I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID))
		{
			// set the product to null if the orderline was set to null
			invoiceLine.setM_Product(null);

			return;
		}

		final I_C_OrderLine ol = invoiceLine.getC_OrderLine();

		final I_M_Product product = ol.getM_Product();

		invoiceLine.setM_Product(product);
	}


	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID })
	public void setIsPackagingMaterial(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		if (InterfaceWrapperHelper.isNull(invoiceLine, I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID))
		{
			//in case the c_orderline_id is removed, make sure the flag is on false. The user can set it on true, manually
			invoiceLine.setIsPackagingMaterial(false);
			return;
		}

		final de.metas.interfaces.I_C_OrderLine ol =  InterfaceWrapperHelper.create(invoiceLine.getC_OrderLine(), de.metas.interfaces.I_C_OrderLine.class);

		invoiceLine.setIsPackagingMaterial(ol.isPackagingMaterial());
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

	@ModelChange(timings = {
			ModelValidator.TYPE_BEFORE_CHANGE,
	}, ifColumnsChanged = I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID)
	public void setIsPackagingMaterial(final I_C_InvoiceLine invoiceLine)
	{
		if(invoiceLine.getC_OrderLine() == null)
		{
			// in case the c_orderline_id is removed, make sure the flag is on false. The user can set it on true, manually
			invoiceLine.setIsPackagingMaterial(false);
			return;
		}

		final de.metas.interfaces.I_C_OrderLine ol = InterfaceWrapperHelper.create(invoiceLine.getC_OrderLine(), de.metas.interfaces.I_C_OrderLine.class);

		invoiceLine.setIsPackagingMaterial(ol.isPackagingMaterial());
	}
}
