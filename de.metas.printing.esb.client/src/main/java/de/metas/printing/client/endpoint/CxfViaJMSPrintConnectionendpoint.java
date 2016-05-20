package de.metas.printing.client.endpoint;

import java.io.InputStream;

import de.metas.printing.client.IPrintConnectionEndpoint;
import de.metas.printing.esb.api.protocol.LoginRequest;
import de.metas.printing.esb.api.protocol.LoginResponse;
import de.metas.printing.esb.api.protocol.PrintJobInstructionsConfirm;
import de.metas.printing.esb.api.protocol.PrintPackage;
import de.metas.printing.esb.api.protocol.PrinterHWList;

/*
 * #%L
 * de.metas.printing.esb.client
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

public class CxfViaJMSPrintConnectionendpoint implements IPrintConnectionEndpoint
{

	@Override
	public LoginResponse login(LoginRequest loginRequest) throws LoginFailedPrintConnectionEndpointException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPrinterHW(PrinterHWList printerHWList)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public PrintPackage getNextPrintPackage()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getPrintPackageData(PrintPackage printPackage)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendPrintPackageResponse(PrintJobInstructionsConfirm response)
	{
		// TODO Auto-generated method stub

	}

}
