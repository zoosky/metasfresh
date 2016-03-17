package de.metas.procurement.base.order.impl;

import java.util.Date;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.model.PlainContextAware;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;

import de.metas.procurement.base.model.I_PMM_PurchaseCandidate;
import de.metas.procurement.base.model.I_PMM_PurchaseCandidate_OrderLine;
import de.metas.procurement.base.order.IPMMPurchaseCandidateDAO;

/*
 * #%L
 * de.metas.procurement.base
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class PMMPurchaseCandidateDAO implements IPMMPurchaseCandidateDAO
{
	@Override
	public void deletePurchaseCandidateOrderLines(final I_C_Order order)
	{
		retrivePurchaseCandidateOrderLines(order)
				.create()
				.delete();
	}

	@Override
	public IQueryBuilder<I_PMM_PurchaseCandidate_OrderLine> retrivePurchaseCandidateOrderLines(final I_C_Order order)
	{
		return Services.get(IQueryBL.class)
				.createQueryBuilder(I_C_OrderLine.class, order)
				.addEqualsFilter(I_C_OrderLine.COLUMN_C_Order_ID, order.getC_Order_ID())
				.andCollectChildren(I_PMM_PurchaseCandidate_OrderLine.COLUMN_C_OrderLine_ID, I_PMM_PurchaseCandidate_OrderLine.class);
	}

	@Override
	public I_PMM_PurchaseCandidate retrieveFor(final int bpartnerId, final int productId, final Date day)
	{
		final PlainContextAware context = PlainContextAware.createUsingThreadInheritedTransaction();
		return Services.get(IQueryBL.class)
				.createQueryBuilder(I_PMM_PurchaseCandidate.class, context)
				.addEqualsFilter(I_PMM_PurchaseCandidate.COLUMN_C_BPartner_ID, bpartnerId)
				.addEqualsFilter(I_PMM_PurchaseCandidate.COLUMN_M_Product_ID, productId)
				.addEqualsFilter(I_PMM_PurchaseCandidate.COLUMN_DatePromised, day)
				.create()
				.firstOnly(I_PMM_PurchaseCandidate.class);
		
	}
	
	
}