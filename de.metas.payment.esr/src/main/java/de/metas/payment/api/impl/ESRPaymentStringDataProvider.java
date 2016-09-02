package de.metas.payment.api.impl;

import org.adempiere.model.IContextAware;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.compiere.model.I_C_Currency;

import de.metas.banking.payment.IPaymentString;
import de.metas.banking.payment.impl.AbstractPaymentStringDataProvider;
import de.metas.bpartner.IBPartnerDAO;
import de.metas.currency.ICurrencyDAO;
import de.metas.payment.esr.api.IESRBPBankAccountDAO;
import de.metas.payment.esr.model.I_C_BP_BankAccount;

/**
 * @author al
 */
public class ESRPaymentStringDataProvider extends AbstractPaymentStringDataProvider
{
	public ESRPaymentStringDataProvider(final IPaymentString paymentString)
	{
		super(paymentString);
	}

	@Override
	public I_C_BP_BankAccount getC_BP_BankAccountOrNull()
	{
		final IPaymentString paymentString = getPaymentString();

		final String postAccountNo = paymentString.getPostAccountNo();
		final String innerAccountNo = paymentString.getInnerAccountNo();

		final I_C_BP_BankAccount bankAccount = Services.get(IESRBPBankAccountDAO.class).retrieveESRBPBankAccountOrNull(postAccountNo, innerAccountNo);
		return bankAccount;
	}

	@Override
	public de.metas.interfaces.I_C_BP_BankAccount createNewC_BP_BankAccount(final IContextAware contextProvider, final int bpartnerId)
	{
		final IPaymentString paymentString = getPaymentString();

		final I_C_BP_BankAccount bpBankAccount = InterfaceWrapperHelper.newInstance(I_C_BP_BankAccount.class, contextProvider);
		if (Check.isEmpty(paymentString.getBPValue()))
		{
			Check.assume(bpartnerId > 0, "If this instance's paymentString has no BPValue, then we assume the bPartnerId to be greater than 0. This={}", this);
			bpBankAccount.setC_BPartner_ID(bpartnerId);
		}
		else
		{
			Services.get(IBPartnerDAO.class).retrieveBPartnerByValue(contextProvider.getCtx(), paymentString.getBPValue());
		}
		// bpBankAccount.setC_Bank_ID(C_Bank_ID); // introduce a standard ESR-Dummy-Bank, or leave it empty

		final I_C_Currency currency = Services.get(ICurrencyDAO.class).retrieveCurrencyByISOCode(contextProvider.getCtx(), "CHF"); // CHF, because it's ESR
		bpBankAccount.setC_Currency(currency);
		bpBankAccount.setIsEsrAccount(true); // ..because we are creating this from an ESR string
		bpBankAccount.setIsACH(true);
		bpBankAccount.setA_Name(bpBankAccount.getC_BPartner().getName());

		bpBankAccount.setAccountNo(paymentString.getInnerAccountNo());
		bpBankAccount.setESR_RenderedAccountNo(paymentString.getPostAccountNo());

		InterfaceWrapperHelper.save(bpBankAccount);

		return bpBankAccount;
	}

	@Override
	public String toString()
	{
		return String.format("ESRPaymentStringDataProvider [getPaymentString()=%s]", getPaymentString());
	}
}
