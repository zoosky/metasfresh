package de.metas.handlingunits.callout;

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

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.util.Services;

import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.InvoiceLineHUPackingAware;
import de.metas.handlingunits.model.I_C_InvoiceLine;
import de.metas.invoice.IInvoiceLineBL;

@Callout(I_C_InvoiceLine.class)
public class C_InvoiceLine
{
	public static final C_InvoiceLine instance = new C_InvoiceLine();

	/**
	 * Task 06915: If QtyEnteredTU or M_HU_PI_Item_Product_ID change, then update QtyEntered (i.e. the CU qty).
	 *
	 * @param orderLine
	 * @param field
	 */
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_QtyEnteredTU, I_C_InvoiceLine.COLUMNNAME_M_HU_PI_Item_Product_ID })
	public void onQtyEnteredChange(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		final IHUPackingAware packingAware = new InvoiceLineHUPackingAware(invoiceLine);
		final Integer qtyPacks = packingAware.getQtyPacks().intValue();

		final IHUPackingAwareBL huPackingAwareBL = Services.get(IHUPackingAwareBL.class);
		huPackingAwareBL.setQty(packingAware, qtyPacks);

		// Update LineNetAmt, because QtyEnteredCU changed : see task 06727
		// No, don't make the call here:
		// "QtyEnteredCU" is QtyEntered, i.e. a standard (non-HU) field and if it's changed,
		// then a callout in de.metas.invoice has the job of updating further standard fields.
		// final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
		// invoiceLineBL.setQtyInvoicedInPriceUOM_AND_LineNetAmt(invoiceLine);
	}

	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_M_HU_PI_Item_Product_ID })
	public void onHUPIIPChange(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
		invoiceLineBL.updatePrices(invoiceLine);

		// Don't make this call here.
		// A de.metas.invoice callout is supposed to make further updates,
		// if the prices were changed due to the changed M_HU_PI_Item_Product_ID
		// invoiceLineBL.setQtyInvoicedInPriceUOM_AND_LineNetAmt(invoiceLine);
	}

}
