package de.metas.printing.esb.base.inout.bean;

import de.metas.printing.esb.api.protocol.LoginRequest;
import de.metas.printing.esb.base.jaxb.JAXBConstants;
import de.metas.printing.esb.base.jaxb.generated.PRTLoginRequestType;
import de.metas.printing.esb.base.jaxb.generated.ReplicationEventEnum;
import de.metas.printing.esb.base.jaxb.generated.ReplicationModeEnum;
import de.metas.printing.esb.base.jaxb.generated.ReplicationTypeEnum;
import de.metas.printing.esb.base.util.Check;
import de.metas.printing.esb.base.util.collections.Converter;

/**
 * Converts from {@link LoginRequest}(json) to {@link PRTLoginRequestType}(xml)
 *
 * @author tsa
 *
 */
public class LoginRequestConverter implements Converter<PRTLoginRequestType, LoginRequest>
{
	public static final transient LoginRequestConverter instance = new LoginRequestConverter();

	@Override
	public PRTLoginRequestType convert(final LoginRequest loginRequest)
	{
		Check.assumeNotNull(loginRequest, "loginRequest not null");
		final PRTLoginRequestType xmlLoginRequest = new PRTLoginRequestType();

		// ADempiere Specific Data
		xmlLoginRequest.setReplicationEventAttr(ReplicationEventEnum.AfterChange);
		xmlLoginRequest.setReplicationModeAttr(ReplicationModeEnum.Table);
		xmlLoginRequest.setReplicationTypeAttr(ReplicationTypeEnum.Merge);
		xmlLoginRequest.setVersionAttr(JAXBConstants.PRT_LOGINREQUEST_VERSION);
		xmlLoginRequest.setADClientValueAttr("SYSTEM"); // FIXME: workaround to let ADempiere replication know which is the #AD_Client_ID

		xmlLoginRequest.setUserName(loginRequest.getUsername());
		xmlLoginRequest.setPassword(loginRequest.getPassword());
		xmlLoginRequest.setHostKey(loginRequest.getHostKey());
		return xmlLoginRequest;
	}
}
