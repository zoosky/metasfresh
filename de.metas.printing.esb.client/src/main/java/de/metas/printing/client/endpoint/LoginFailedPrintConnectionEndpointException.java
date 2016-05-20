package de.metas.printing.client.endpoint;

import de.metas.printing.client.IPrintConnectionEndpoint;

/**
 * Exception thrown if {@link IPrintConnectionEndpoint#login(de.metas.printing.esb.api.LoginRequest)} fails.
 *
 * @author tsa
 *
 */
public class LoginFailedPrintConnectionEndpointException extends PrintConnectionEndpointException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 8932182388809426483L;

	public LoginFailedPrintConnectionEndpointException(final String message)
	{
		super(message);
	}

	public LoginFailedPrintConnectionEndpointException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

}
