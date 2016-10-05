package de.metas.payment.api.impl;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoicePaySchedule;

import de.metas.payment.api.IInvoicePayScheduleDAO;

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

public class InvoicePayScheduleDAO implements IInvoicePayScheduleDAO
{
	@Override
	public void deleteInvoicePaySchedule(final I_C_Invoice invoice)
	{
		Services.get(IQueryBL.class).createQueryBuilder(I_C_InvoicePaySchedule.class, invoice)
				.addEqualsFilter(I_C_InvoicePaySchedule.COLUMNNAME_C_Invoice_ID, invoice.getC_Invoice_ID())
				.create()
				.delete();

	}	// deleteInvoicePaySchedule

}
