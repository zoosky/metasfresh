package de.metas.printing.client.endpoint;

import de.metas.printing.client.IPrintConnectionEndpoint;

/**
 * Exception to be thrown by {@link IPrintConnectionEndpoint} on error
 *
 * @author tsa
 *
 */
public class PrintConnectionEndpointException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 3336849851724086746L;

	public PrintConnectionEndpointException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public PrintConnectionEndpointException(final String message)
	{
		super(message);
	}
}
