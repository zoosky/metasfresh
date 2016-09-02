/**
 * 
 */
package de.metas.location.impl;

import de.metas.location.ICountryCustomInfo;

/**
 * @author cg
 * 
 */
public class CountryCustomInfoImpl implements ICountryCustomInfo
{
	private final String captureSequence;
	private final int C_Country_ID;

	CountryCustomInfoImpl(final String captureSequence, final int C_Country_ID)
	{
		this.captureSequence = captureSequence;
		this.C_Country_ID = C_Country_ID;
	}

	@Override
	public String getCaptureSequence()
	{
		return captureSequence;
	}

	@Override
	public int getC_Country_ID()
	{
		return C_Country_ID;
	}

}
