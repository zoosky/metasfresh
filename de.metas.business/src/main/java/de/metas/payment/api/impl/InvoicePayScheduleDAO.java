package de.metas.payment.api.impl;

import java.util.List;

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

	}

	@Override
	public List<I_C_InvoicePaySchedule> retrievePaySchedulesForInvoice(final I_C_Invoice invoice)
	{
		return Services.get(IQueryBL.class).createQueryBuilder(I_C_InvoicePaySchedule.class, invoice)
				.addEqualsFilter(I_C_InvoicePaySchedule.COLUMNNAME_C_Invoice_ID, invoice.getC_Invoice_ID())
				.orderBy()
				.addColumn(I_C_InvoicePaySchedule.COLUMNNAME_DueDate)
				.endOrderBy()
				.create()
				.list();
	}

	@Override
	public List<I_C_InvoicePaySchedule> retrievePaySchedulesForID(final I_C_InvoicePaySchedule paySchedule)
	{
		return Services.get(IQueryBL.class).createQueryBuilder(I_C_InvoicePaySchedule.class, paySchedule)
				.addEqualsFilter(I_C_InvoicePaySchedule.COLUMNNAME_C_InvoicePaySchedule_ID, paySchedule.getC_InvoicePaySchedule_ID())
				.orderBy()
				.addColumn(I_C_InvoicePaySchedule.COLUMNNAME_DueDate)
				.endOrderBy()
				.create()
				.list();
	}
}
