package de.metas.printing.client.endpoint;

import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import de.metas.printing.client.Context;
import de.metas.printing.client.IPrintConnectionEndpoint;
import de.metas.printing.esb.api.IMetasfreshEPSync;
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
@Service
public class CxfOverJmsPrintConnectionendpoint implements IPrintConnectionEndpoint
{

	private static final transient Logger logger = LoggerFactory.getLogger(CxfOverJmsPrintConnectionendpoint.class);

	private Context _ctx;
	private String serverUrl;

	@Autowired
	private IMetasfreshEPSync metasfreshEPSync;

	/**
	 * Creates the {@link IServerSync} client endpoint which this application can use to talk to the metasfresh server.
	 *
	 * @return
	 */
	@Bean
	public IMetasfreshEPSync metasfreshEPSync(
			final JacksonJaxbJsonProvider jacksonJaxbJsonProvider,
			final LoggingFeature loggingFeature)
	{
		//
		// Get server's URL
		_ctx = Context.getContext();
		serverUrl = _ctx.getProperty(RestHttpPrintConnectionEndpoint.CTX_ServerUrl);
		if (serverUrl == null || serverUrl.isEmpty())
		{
			throw new PrintConnectionEndpointException("Config " + RestHttpPrintConnectionEndpoint.CTX_ServerUrl + " not found");
		}

		//
		// Get MediaType
		final MediaType mediaType = MediaType.APPLICATION_JSON_TYPE;

		//
		// Create the server binding.
		final IMetasfreshEPSync serverSync = JAXRSClientFactory.create(
				serverUrl.trim(),
				IMetasfreshEPSync.class,
				Collections.singletonList(jacksonJaxbJsonProvider),
				Collections.singletonList((Feature)loggingFeature),
				null); // not providing a particular configLocation

		WebClient.client(serverSync)
				.type(mediaType)
				.accept(mediaType);
		return serverSync;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) throws LoginFailedPrintConnectionEndpointException
	{
		return metasfreshEPSync.login(loginRequest);
	}

	@Override
	public void addPrinterHW(PrinterHWList printerHWList)
	{
		metasfreshEPSync.addPrinterHW(printerHWList);
	}

	@Override
	public PrintPackage getNextPrintPackage()
	{
		return metasfreshEPSync.getNextPrintPackage();
	}

	@Override
	public InputStream getPrintPackageData(PrintPackage printPackage)
	{
		return metasfreshEPSync.getPrintPackageData(printPackage);
	}

	@Override
	public void sendPrintPackageResponse(PrintJobInstructionsConfirm response)
	{
		metasfreshEPSync.sendPrintPackageResponse(response);
	}

}
