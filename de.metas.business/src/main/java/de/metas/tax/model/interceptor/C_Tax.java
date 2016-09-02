package de.metas.tax.model.interceptor;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
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
import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;
import org.adempiere.ad.modelvalidator.annotations.Init;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Tax;
import org.compiere.model.ModelValidator;

import de.metas.tax.api.ITaxBL;

@Interceptor(I_C_Tax.class)
@Callout(I_C_Tax.class)
public class C_Tax
{
	public static final C_Tax INSTANCE = new C_Tax();

	private C_Tax()
	{
	};

	@Init
	public void init()
	{
		final IProgramaticCalloutProvider programaticCalloutProvider = Services.get(IProgramaticCalloutProvider.class);
		programaticCalloutProvider.registerAnnotatedCallout(new de.metas.tax.model.interceptor.C_Tax());
	}

	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	@CalloutMethod(columnNames = { I_C_Tax.COLUMNNAME_IsWholeTax })
	public void setupIfIsWholeTax(final I_C_Tax tax)
	{
		Services.get(ITaxBL.class).setupIfIsWholeTax(tax);
	}

}
