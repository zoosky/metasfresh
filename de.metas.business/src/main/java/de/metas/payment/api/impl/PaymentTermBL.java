package de.metas.payment.api.impl;

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoicePaySchedule;
import org.compiere.model.I_C_PaySchedule;
import org.compiere.model.I_C_PaymentTerm;

import de.metas.payment.api.IInvoicePayScheduleDAO;
import de.metas.payment.api.IPayScheduleDAO;
import de.metas.payment.api.IPaymentTermBL;

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

public class PaymentTermBL implements IPaymentTermBL
{

	@Override
	public boolean applyPaymentTermToInvoice(final I_C_Invoice invoice, final I_C_PaymentTerm paymentTerm)
	{
		final IPayScheduleDAO payScheduleDAO = Services.get(IPayScheduleDAO.class);

		if (invoice == null)
		{
			return false;
		}

		final boolean isValidPaymentTerm = paymentTerm.isValid();

		if (!isValidPaymentTerm)
		{
			return applyNoSchedule(invoice, paymentTerm);
		}

		//
		final List<I_C_PaySchedule> payScheduleForPaymentTerm = payScheduleDAO.retrievePayScheduleForPaymentTerm(paymentTerm);

		if (Check.isEmpty(payScheduleForPaymentTerm))
		{
			return applyNoSchedule(invoice, paymentTerm);
		}

		else
		{
			//TODO
			//return applySchedule(invoice);
		}
		return false;

	}

	/**
	 * Apply Payment Term without schedule to Invoice
	 * 
	 * @param invoice invoice
	 * @return false as no payment schedule
	 */
	private boolean applyNoSchedule(final I_C_Invoice invoice, final I_C_PaymentTerm paymentTerm)
	{
		Services.get(IInvoicePayScheduleDAO.class).deleteInvoicePaySchedule(invoice);

		// updateInvoice
		if (invoice.getC_PaymentTerm_ID() != paymentTerm.getC_PaymentTerm_ID())
		{
			invoice.setC_PaymentTerm_ID(paymentTerm.getC_PaymentTerm_ID());
		}

		if (invoice.isPayScheduleValid())
		{
			invoice.setIsPayScheduleValid(false);
		}

		return false;
	}	// applyNoSchedule

	/**
	 * Apply Payment Term with schedule to Invoice
	 * 
	 * @param invoice invoice
	 * @return true if payment schedule is valid
	 */
	private boolean applySchedule(final I_C_Invoice invoice, final I_C_PaymentTerm paymentTerm)
	{
		Services.get(IInvoicePayScheduleDAO.class).deleteInvoicePaySchedule(invoice);

		I_C_InvoicePaySchedule ips = null;
		// Create Schedule

		BigDecimal remainder = invoice.getGrandTotal();

		final List<I_C_PaySchedule> payScheduleForPaymentTerm = Services.get(IPayScheduleDAO.class).retrievePayScheduleForPaymentTerm(paymentTerm);

		for(final I_C_PaySchedule paySchedule : payScheduleForPaymentTerm)
		{
			// TODO
		}
		//TODO
//		for (int i = 0; i < m_schedule.length; i++)
//		{
//			ips = new MInvoicePaySchedule(invoice, m_schedule[i]);
//			ips.save(invoice.get_TrxName());
//			log.debug(ips.toString());
//			remainder = remainder.subtract(ips.getDueAmt());
//		}  	// for all schedules
//		// Remainder - update last
//		if (remainder.compareTo(Env.ZERO) != 0 && ips != null)
//		{
//			ips.setDueAmt(ips.getDueAmt().add(remainder));
//			ips.save(invoice.get_TrxName());
//			log.debug("Remainder=" + remainder + " - " + ips);
//		}
//
//		// updateInvoice
//		if (invoice.getC_PaymentTerm_ID() != getC_PaymentTerm_ID())
//			invoice.setC_PaymentTerm_ID(getC_PaymentTerm_ID());
	//	return invoice.validatePaySchedule();
		return false;
	}	// applySchedule

}
