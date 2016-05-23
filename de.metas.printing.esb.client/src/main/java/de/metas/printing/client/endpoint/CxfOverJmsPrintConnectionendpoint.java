package de.metas.printing.client.endpoint;

import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.base.Strings;

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

public class CxfOverJmsPrintConnectionendpoint implements IPrintConnectionEndpoint
{

	private static final transient Logger logger = LoggerFactory.getLogger(CxfOverJmsPrintConnectionendpoint.class);

	/**
	 * The server-URL to be used by the client. It contains {@link #brokerUrl}, {@link #metasfreshQueueRequest} and {@link #metasfreshQueueResponse} as substrings.
	 */
	@Value("${mf.sync.url:}")
	private String serverUrl;

	@Autowired
	private IMetasfreshEPSync metasfreshEPSync;

	/**
	 * Creates the {@link IServerSync} client endpoint which this application can use to talk to the metasfresh server.
	 *
	 * @return
	 */
	public IMetasfreshEPSync metasfreshEPSync(
			final JacksonJaxbJsonProvider jacksonJaxbJsonProvider,
			final LoggingFeature loggingFeature)
	{
		//
		// Get server's URL
		logger.info("mfprocurement.sync.url: {}", serverUrl);
		if (Strings.isNullOrEmpty(serverUrl))
		{
			// TODO: fail
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

	/**
	 *
	 * @return
	 * @task https://metasfresh.atlassian.net/browse/FRESH-87
	 */

	public LoggingFeature createLoggingFeature()
	{
		final boolean prettyPrint = true;
		final boolean showBinary = true;

		// see LoggingFeature.initializeProvider()...we want to make sure that showBinary is not ignored
		final int limit = AbstractLoggingInterceptor.DEFAULT_LIMIT + 1;

		final LoggingFeature loggingFeature = new LoggingFeature(
				null,     // use default
				null,     // use default
				limit,
				prettyPrint,
				showBinary);
		return loggingFeature;
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
