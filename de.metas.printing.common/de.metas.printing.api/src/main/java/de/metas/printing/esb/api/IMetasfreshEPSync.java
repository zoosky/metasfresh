package de.metas.printing.esb.api;

/*
 * #%L
 * de.metas.printing.esb.client
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.io.InputStream;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import de.metas.printing.esb.api.protocol.LoginRequest;
import de.metas.printing.esb.api.protocol.LoginResponse;
import de.metas.printing.esb.api.protocol.PrintJobInstructionsConfirm;
import de.metas.printing.esb.api.protocol.PrintPackage;
import de.metas.printing.esb.api.protocol.PrinterHWList;

/**
 * Sync endpoint that is implemented on the metasfresh's side, to be called by the printing client.
 *
 * @author metas-dev <dev@metasfresh.com>
 *
 */
@Path("/printing")
public interface IMetasfreshEPSync
{
	/**
	 * Login.
	 *
	 * @return valid login response (with {@link LoginResponse#getSessionId()} filled).
	 * @throws LoginFailedPrintConnectionEndpointException in case something went wrong.
	 */
	@POST
	@Path("login")
	LoginResponse login(LoginRequest loginRequest);

	/**
	 * Send printer hardware configuration to ADempiere.
	 *
	 * @param printerHWList
	 */
	@POST
	@Path("addPrinterHW")
	void addPrinterHW(PrinterHWList printerHWList);

	/**
	 * Retrieve the next print package.
	 *
	 * @return next {@link PrintPackage} or null if there is no next print package
	 */
	@POST // TODO i don't see why it shouldn't be a get in future
	@Path("getNextPrintPackage")
	PrintPackage getNextPrintPackage();

	/**
	 * Send printPackage data request to camel->AD.
	 *
	 * @param printPackage
	 * @return PDF Stream, already decoded
	 */
	@POST // TODO i don't see why it shouldn't be a get in future
	@Path("getPrintPackageData")
	InputStream getPrintPackageData(PrintPackage printPackage);

	/**
	 * Send printPackage response to camel->AD after the print data is sent to printer.
	 *
	 * @param response
	 */
	@POST
	@Path("sendPrintPackageResponse")
	void sendPrintPackageResponse(PrintJobInstructionsConfirm response);
}
