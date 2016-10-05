package de.metas.payment.api.impl;

import java.util.List;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.util.Services;
import org.compiere.model.I_C_PaySchedule;
import org.compiere.model.I_C_PaymentTerm;

import de.metas.payment.api.IPayScheduleDAO;

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

public class PayScheduleDAO implements IPayScheduleDAO
{

	@Override
	public List<I_C_PaySchedule> retrievePayScheduleForPaymentTerm(final I_C_PaymentTerm paymentTerm)
	{
		return Services.get(IQueryBL.class).createQueryBuilder(I_C_PaySchedule.class, paymentTerm)
				.addEqualsFilter(I_C_PaySchedule.COLUMNNAME_C_PaymentTerm_ID, paymentTerm.getC_PaymentTerm_ID())
				.addOnlyActiveRecordsFilter()
				.orderBy()
				.addColumn(I_C_PaySchedule.COLUMNNAME_NetDays)
				.endOrderBy()
				.create()
				.list();
	}

}
