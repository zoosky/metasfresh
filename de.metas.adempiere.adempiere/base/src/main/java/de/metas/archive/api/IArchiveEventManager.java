package de.metas.archive.api;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Archive;
import org.compiere.model.I_AD_User;

import de.metas.archive.spi.IArchiveEventListener;

public interface IArchiveEventManager extends ISingletonService
{
	String STATUS_Success = "Success";
	String STATUS_Failure = "Failure";

	/**
	 * To be used where you need to pass the "1 copy" parameter.
	 * 
	 * NOTE: please use this constant instead of hardcoding the number "1" because in most of the cases, you are passing this because the copies is not available or is not implemented.
	 */
	int COPIES_ONE = 1;


	void registerArchiveEventListener(IArchiveEventListener listener);


	void firePdfUpdate(I_AD_Archive archive, I_AD_User user);


	void fireEmailSent(I_AD_Archive archive, String action, I_AD_User user, String from, String to, String cc, String bcc, String status);


	void firePrintOut(I_AD_Archive archive, I_AD_User user, String printerName, int copies, String status);
}
