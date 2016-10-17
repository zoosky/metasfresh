package de.metas.handlingunits.model.validator;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;
import org.adempiere.ad.modelvalidator.annotations.Init;

/*
 * #%L
 * de.metas.handlingunits.base
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

import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_C_UOM;
import org.compiere.model.ModelValidator;

import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.InvoiceLineHUPackingAware;
import de.metas.adempiere.service.IInvoiceLineBL;
import de.metas.handlingunits.model.I_C_InvoiceLine;
import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.handlingunits.model.I_M_InOutLine;

@Validator(I_C_InvoiceLine.class)
@Callout(I_C_InvoiceLine.class)
public class C_InvoiceLine
{

	@Init
	public void setupCallouts()
	{
		final IProgramaticCalloutProvider calloutProvider = Services.get(IProgramaticCalloutProvider.class);

		// this will also serve as acallout class
		calloutProvider.registerAnnotatedCallout(this);
	}

	/**
	 * Set both M_HU_PI_ItemProduct and QtyEnteredTU
	 * 
	 * @param invoiceLine
	 */
	@ModelChange(timings = {
			ModelValidator.TYPE_BEFORE_NEW,
			ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = {
					I_C_InvoiceLine.COLUMNNAME_M_InOutLine_ID,
					I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID

	})
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_C_OrderLine_ID, I_C_InvoiceLine.COLUMNNAME_M_InOutLine_ID })
	public void setM_HU_PI_Item_Product(final I_C_InvoiceLine invoiceLine)
	{
		// do nothing in case the invoiceline already has an M_HU_PI_Item_Product
		if (invoiceLine.getM_HU_PI_Item_Product() != null)
		{
			return;
		}

		final I_M_InOutLine iol = InterfaceWrapperHelper.create(invoiceLine.getM_InOutLine(), I_M_InOutLine.class);

		if (iol != null)
		{
			invoiceLine.setM_HU_PI_Item_Product(iol.getM_HU_PI_Item_Product());
			invoiceLine.setQtyEnteredTU(iol.getQtyEnteredTU());
		}

		// fallback to orderline

		final I_C_OrderLine orderLine = InterfaceWrapperHelper.create(invoiceLine.getC_OrderLine(), I_C_OrderLine.class);

		if (orderLine != null)
		{
			invoiceLine.setM_HU_PI_Item_Product(orderLine.getM_HU_PI_Item_Product());
			invoiceLine.setQtyEnteredTU(orderLine.getQtyEnteredTU());
		}
	}

	@ModelChange(timings = {
			ModelValidator.TYPE_BEFORE_NEW,
			ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = { I_C_InvoiceLine.COLUMNNAME_QtyEnteredTU, I_C_InvoiceLine.COLUMNNAME_M_HU_PI_Item_Product_ID

	})
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_QtyEnteredTU, I_C_InvoiceLine.COLUMNNAME_M_HU_PI_Item_Product_ID })
	public void onQtyEnteredChange(final I_C_InvoiceLine invoiceLine)
	{

		final IHUPackingAware packingAware = new InvoiceLineHUPackingAware(invoiceLine);
		final Integer qtyPacks = packingAware.getQtyPacks().intValue();

		// make sure the packingAware object has the correct current UOM
		// useful for callout
		final I_C_UOM uom = invoiceLine.getC_UOM();

		packingAware.setC_UOM(uom);

		Services.get(IHUPackingAwareBL.class).setQty(packingAware, qtyPacks);

		// Update lineNetAmt, because QtyEnteredCU changed : see task 06727
		Services.get(IInvoiceLineBL.class).updateLineNetAmt(invoiceLine, invoiceLine.getQtyEntered());
	}

}
