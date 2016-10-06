/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved. *
 * This program is free software; you can redistribute it and/or modify it *
 * under the terms version 2 of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. *
 * See the GNU General Public License for more details. *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA. *
 * For the text or an alternative of this public license, you may reach us *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA *
 * or via info@compiere.org or http://www.compiere.org/license.html *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.adempiere.util.api.IMsgBL;
import org.compiere.model.I_C_InvoicePaySchedule;
import org.compiere.model.MInvoice;

import de.metas.payment.api.IInvoicePayScheduleDAO;

/**
 * Validate Invoice Payment Schedule
 * 
 * @author Jorg Janke
 * @version $Id: InvoicePayScheduleValidate.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class InvoicePayScheduleValidate extends SvrProcess
{
	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.error("prepare - Unknown Parameter: " + name);
		}
	}	// prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception if not successful
	 */
	@Override
	protected String doIt() throws Exception
	{
		log.info("C_InvoicePaySchedule_ID=" + getRecord_ID());

		final I_C_InvoicePaySchedule paySchedule = getRecord(I_C_InvoicePaySchedule.class);

		final List<I_C_InvoicePaySchedule> invoicePaySchedules = Services.get(IInvoicePayScheduleDAO.class).retrievePaySchedulesForID(paySchedule);

		// MInvoicePaySchedule[] schedule = MInvoicePaySchedule.getInvoicePaySchedule
		// (getCtx(), 0, getRecord_ID(), null);
		if (invoicePaySchedules.isEmpty())
		{
			throw new IllegalArgumentException("InvoicePayScheduleValidate - No Schedule");
		}
		// Get Invoice
		MInvoice invoice = new MInvoice(getCtx(), invoicePaySchedules.get(0).getC_Invoice_ID(), null);

		if (invoice.get_ID() == 0)
		{
			throw new IllegalArgumentException("InvoicePayScheduleValidate - No Invoice");
		}
		//
		BigDecimal total = BigDecimal.ZERO;

		for (final I_C_InvoicePaySchedule invoicePaySchedule : invoicePaySchedules)
		{
			BigDecimal due = invoicePaySchedule.getDueAmt();
			if (due != null)
				total = total.add(due);
		}

		boolean valid = invoice.getGrandTotal().compareTo(total) == 0;
		invoice.setIsPayScheduleValid(valid);
		invoice.save();
		// Schedule
		for (final I_C_InvoicePaySchedule invoicePaySchedule : invoicePaySchedules)
		{
			if (invoicePaySchedule.isValid() != valid)
			{
				invoicePaySchedule.setIsValid(valid);
				InterfaceWrapperHelper.save(invoicePaySchedule);
			}
		}

		String msg = "@OK@";
		if (!valid)
			msg = "@GrandTotal@ = " + invoice.getGrandTotal()
					+ " <> @Total@ = " + total
					+ "  - @Difference@ = " + invoice.getGrandTotal().subtract(total);
		
		return Services.get(IMsgBL.class).parseTranslation(getCtx(), msg);
	}	// doIt

}	// InvoicePayScheduleValidate
