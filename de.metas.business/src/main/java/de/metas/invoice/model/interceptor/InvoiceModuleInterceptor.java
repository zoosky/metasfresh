package de.metas.invoice.model.interceptor;

import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;
import org.adempiere.ad.modelvalidator.AbstractModuleInterceptor;
import org.adempiere.ad.modelvalidator.IModelValidationEngine;
import org.adempiere.ad.ui.api.ITabCalloutFactory;
import org.compiere.model.I_AD_Client;

import de.metas.adempiere.model.I_C_InvoiceLine;

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

public class InvoiceModuleInterceptor extends AbstractModuleInterceptor
{
	public static final InvoiceModuleInterceptor INSTANCE = new InvoiceModuleInterceptor();

	private InvoiceModuleInterceptor()
	{
	};

	@Override
	protected void registerInterceptors(final IModelValidationEngine engine, final I_AD_Client client)
	{
		engine.addModelValidator(C_Invoice.INSTANCE, client); // 03771
		engine.addModelValidator(C_InvoiceLine.INSTANCE, client);

		engine.addModelValidator(M_MatchInv.INSTANCE, client);
	}

	@Override
	protected void registerTabCallouts(final ITabCalloutFactory tabCalloutsRegistry)
	{
		tabCalloutsRegistry.registerTabCalloutForTable(I_C_InvoiceLine.Table_Name, C_InvoiceLine_TabCallout.class);
	}

	@Override
	protected void registerCallouts(final IProgramaticCalloutProvider calloutsRegistry)
	{
		calloutsRegistry.registerAnnotatedCallout(C_Invoice.INSTANCE);
		calloutsRegistry.registerAnnotatedCallout(C_InvoiceLine.INSTANCE);
	}
}
