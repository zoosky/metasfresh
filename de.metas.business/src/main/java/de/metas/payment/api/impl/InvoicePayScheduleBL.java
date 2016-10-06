package de.metas.payment.api.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoicePaySchedule;
import org.compiere.model.I_C_PaySchedule;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

import de.metas.currency.ICurrencyDAO;
import de.metas.payment.api.IInvoicePayScheduleBL;
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

public class InvoicePayScheduleBL implements IInvoicePayScheduleBL
{

	@Override
	public I_C_InvoicePaySchedule createInvoicePaySchedule(final I_C_Invoice invoice, final I_C_PaySchedule paySchedule)
	{
		// new invoicePaySchedule instance created based on the information from the given invoice and paySchedule
		final I_C_InvoicePaySchedule invoicePaySchedule = InterfaceWrapperHelper.newInstance(I_C_InvoicePaySchedule.class);

		invoicePaySchedule.setC_Invoice_ID(invoice.getC_Invoice_ID());
		invoicePaySchedule.setAD_Org_ID(invoice.getAD_Org_ID());
		invoicePaySchedule.setC_PaySchedule_ID(paySchedule.getC_PaySchedule_ID());

		final Properties ctx = InterfaceWrapperHelper.getCtx(invoice);

		// Amounts
		final int scale = Services.get(ICurrencyDAO.class).getStdPrecision(ctx, invoice.getC_Currency_ID());

		BigDecimal due = invoice.getGrandTotal();

		if (due.signum() == 0)
		{
			invoicePaySchedule.setDueAmt(BigDecimal.ZERO);
			invoicePaySchedule.setDiscountAmt(BigDecimal.ZERO);
			invoicePaySchedule.setIsValid(false);
		}
		else
		{
			due = due.multiply(paySchedule.getPercentage())
					.divide(Env.ONEHUNDRED, scale, BigDecimal.ROUND_HALF_UP);

			invoicePaySchedule.setDueAmt(due);

			final BigDecimal discount = due.multiply(paySchedule.getDiscount())
					.divide(Env.ONEHUNDRED, scale, BigDecimal.ROUND_HALF_UP);

			invoicePaySchedule.setDiscountAmt(discount);
			invoicePaySchedule.setIsValid(true);
		}

		// Dates
		final Timestamp dueDate = TimeUtil.addDays(invoice.getDateInvoiced(), paySchedule.getNetDays());
		invoicePaySchedule.setDueDate(dueDate);

		final Timestamp discountDate = TimeUtil.addDays(invoice.getDateInvoiced(), paySchedule.getDiscountDays());
		invoicePaySchedule.setDiscountDate(discountDate);

		return invoicePaySchedule;
	}

	@Override
	public boolean validatePaySchedule(final I_C_Invoice invoice)
	{
		final List<I_C_InvoicePaySchedule> invoicePaySchedules = Services.get(IInvoicePayScheduleDAO.class).retrievePaySchedulesForInvoice(invoice);

		// No invoice pay schedules found.
		if (invoicePaySchedules.isEmpty())
		{
			invoice.setIsPayScheduleValid(false);
			return false;
		}

		// Add up due amounts
		BigDecimal total = BigDecimal.ZERO;

		for (final I_C_InvoicePaySchedule invoicePaySchedule : invoicePaySchedules)
		{
			// schedule[i].setParent(this);
			final BigDecimal due = invoicePaySchedule.getDueAmt();
			if (due != null)
			{
				total = total.add(due);
			}
		}
		boolean valid = invoice.getGrandTotal().compareTo(total) == 0;

		invoice.setIsPayScheduleValid(valid);

		// Update Schedule Lines
		for (final I_C_InvoicePaySchedule invoicePaySchedule : invoicePaySchedules)
		{
			if (invoicePaySchedule.isValid() != valid)
			{
				invoicePaySchedule.setIsValid(valid);
				InterfaceWrapperHelper.save(invoicePaySchedule);
			}
		}
		return valid;
	}
}
