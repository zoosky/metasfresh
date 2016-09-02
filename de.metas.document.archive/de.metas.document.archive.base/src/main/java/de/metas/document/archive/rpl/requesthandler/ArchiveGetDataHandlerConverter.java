package de.metas.document.archive.rpl.requesthandler;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.adempiere.util.collections.Converter;

import de.metas.archive.api.IArchiveStorageFactory;
import de.metas.archive.spi.IArchiveStorage;
import de.metas.document.archive.model.I_AD_Archive;

public class ArchiveGetDataHandlerConverter implements Converter<I_AD_Archive, I_AD_Archive>
{
	public static final transient ArchiveGetDataHandlerConverter instance = new ArchiveGetDataHandlerConverter();

	@Override
	public I_AD_Archive convert(final I_AD_Archive archiveRequest)
	{
		final IArchiveStorage storage = Services.get(IArchiveStorageFactory.class).getArchiveStorage(archiveRequest);
		final byte[] data = storage.getBinaryData(archiveRequest);

		//
		// NOTE: We are setting directly the byte data to BinaryData column
		// We do this because replication module will fetch the values directly from there
		archiveRequest.setBinaryData(data);
		// Prevent saving this archive because this archive is prepared for replication module, but saving it will result in big inconsistencies
		InterfaceWrapperHelper.setSaveDeleteDisabled(archiveRequest, true);

		return archiveRequest;
	}

}
