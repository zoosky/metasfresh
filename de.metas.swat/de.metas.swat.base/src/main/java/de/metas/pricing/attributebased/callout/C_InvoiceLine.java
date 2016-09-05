package de.metas.pricing.attributebased.callout;

import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.util.Services;

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
@Callout(I_C_InvoiceLine.class)
public class C_InvoiceLine
{


	public static final C_InvoiceLine INSTANCE = new C_InvoiceLine();

	private C_InvoiceLine()
	{
	};

	/**
	 * update prices on ASI change
	 *
	 * @param invoiceLine
	 * @param field
	 */
	@CalloutMethod(columnNames = { I_C_InvoiceLine.COLUMNNAME_M_AttributeSetInstance_ID })
	public void onASIChange(final I_C_InvoiceLine invoiceLine, final ICalloutField field)
	{
		final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
		invoiceLineBL.updatePrices(invoiceLine);
	}
}
