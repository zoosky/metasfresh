package de.metas.invoice;

/*
 * #%L
 * de.metas.swat.base
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.pricing.api.IEditablePricingContext;
import org.adempiere.pricing.api.IPricingContext;
import org.adempiere.util.ISingletonService;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_Tax;
import org.compiere.model.MInvoiceLine;

import de.metas.adempiere.model.I_C_InvoiceLine;

/**
 *
 * Note that many methods you might be searching here might be in {@link IInvoiceBL}.
 *
 */
public interface IInvoiceLineBL extends ISingletonService
{

	void setTaxAmtInfo(Properties ctx, I_C_InvoiceLine il, String getTrxName);

	/**
	 * Retrieves the il's C_Tax_ID if the il has an inout line. {@link MInvoiceLine#getTax()} only uses the bill location date and address, which is not correct (for us).
	 * <p/>
	 * <b>IMPORTANT:</b> if the il has M_InoutLine_ID<=0, the method does nothing!
	 *
	 * @param ctx
	 * @param il
	 * @param getTrxName
	 */
	boolean setTax(Properties ctx, org.compiere.model.I_C_InvoiceLine il, String getTrxName);

	/**
	 * @param invoiceLine
	 * @return true if invoice line's prices are locked (i.e. should be the same as linked OrderLine)
	 */
	boolean isPriceLocked(I_C_InvoiceLine invoiceLine);

	/**
	 * Retrieves the the {@code M_PriceList_Version} for the {@code DateInvoiced} and {@code M_PriceList} values of the given {@code invoiceLine}'s invoice. Then retrieves the {@code M_ProductPrice}
	 * for the invoiceLine's {@code M_Product_ID} and the M_PriceList_Version. Finally returns that pp's {@code C_TaxCategory_ID}.
	 * <p>
	 * This method assumes that
	 * <ul>
	 * <li>invoiceLine.getM_Product_ID() > 0
	 * <li>invoiceLine.getInvoice().getM_PriceList_ID() > 0
	 * <li>a M_PriceList_Version exists for the DateInvoiced and M_PriceList if the invoiceLine's invoice
	 * <li>a M_ProductPrice exists for the invoiceLine's product and the PLV
	 * </ul>
	 *
	 * @param invoiceLine
	 * @return C_TaxCategory_ID
	 * @see org.adempiere.util.Check#assume(boolean, String, Object...)
	 */
	int getC_TaxCategory_ID(org.compiere.model.I_C_InvoiceLine invoiceLine);

	IEditablePricingContext createPricingContext(I_C_InvoiceLine invoiceLine);

	/**
	 * Converts the given <code>qty</code> to the given <code>il</code>'s price UOM.
	 *
	 * @param qty the "raw" Qty in terms of invoice line's product's UOM
	 * @param il the invoice line whose price UOM and product we use for the conversion.
	 * @param errorIfNotPossible if <code>true</code> and the given invoice line has no price, product or the prod has no UOM, then throw an exception. If <code>false</code>, then just return the
	 *            given qty.
	 *
	 * @return the "price" qty.
	 */
	// BigDecimal calculatedQtyInPriceUOM(BigDecimal qty, I_C_InvoiceLine il, boolean errorIfNotPossible);

	/**
	 * Updates
	 * <ul>
	 * <li><code>PriceList</code>
	 * <li><code>PriceLimit</code>
	 * <li><code>Price_UOM_ID</code>
	 * <li><code>PriceEntered</code>
	 * <li><code>PriceActual</code>
	 * <li><code>Discount</code>
	 * <li><code>PriceActual</code>
	 * </ul>
	 *
	 * Based on
	 * <ul>
	 * <li>C_InvoiceLine.M_Product_ID: if less or euqal zero, then nothing is done.
	 * <li>C_InvoiceLine.IsManualPrice: if yes, then the pricing engine is not invoked, but only <code>PriceActual</code> is updated from <code>PriceEntered</code> and <code>Discount</code>.
	 * <li><code>C_InvoiceLine.C_Invoice.M_PriceList</code>
	 * <li><code>C_InvoiceLine.QtyInvoiced</code>
	 * <li>the given <code>invoiceLine</code> itself {@link IPricingContext#getReferencedObject()}: we know that the referenced object is used for ASI based price calculation.
	 * <li>...all the other things like <code>C_Invoice.C_BPartner</code>, <code>C_Invoice.DateInvoiced</code> etc that go into the price calculation
	 * </ul>
	 */
	void updatePrices(I_C_InvoiceLine invoiceLine);

	/**
	 * Updates the given invoice line's <code>M_Product_ID</code>, <code>C_UOM_ID</code> and <code>M_AttributeSetInstance_ID</code>.<br>
	 * Product ID and UOM ID are set to the product and it's respective UOM or to 0 if the given <code>productId</code> is 0. The ASI ID is always set to 0.
	 * <p>
	 * Important note: what we do <b>not</b> set there is the price UOM because that one is set only together with the price.
	 *
	 * @param invoiceLine
	 * @param productId
	 */
	void setProductAndUOM(I_C_InvoiceLine invoiceLine, int productId);



	/**
	 * Set the given invoiceline's QtyInvoiced, QtyEntered and QtyInvoicedInPriceUOM.
	 * This method assumes that the given invoice Line has a product (with an UOM) and a C_UOM and Price_UOM set.
	 *
	 * @param invoiceLine
	 * @param qtyInvoiced qtyInvoiced value to be set. The other two values are computed from it, using UOM conversions.
	 */
	void setQtys(I_C_InvoiceLine invoiceLine, BigDecimal qtyInvoiced);

	/**
	 * Set the given <code>invoiceLine</code>'s <code>InvoicedInPriceUOM</code> and <code>LineNetAmt</code>, based on
	 * <ul>
	 * <li><code>PriceActual</code>
	 * <li><code>QtyInvoiced</code>
	 * <li><code>Price_UOM_ID</code>: used to calculate QtyInvoicedInPriceUOM (from QtyInvoiced)
	 * <li><code>C_Tax.IsDocumentLevel</code>
	 * <li><code>C_Tax.IsWholeTax</code>: only relevant if IsDocumentLevel=Y
	 * <li><code>C_Invoice.IsTaxIncluded</code>: only relevant if IsDocumentLevel=Y
	 * <li><code>C_Invoice.C_Currency.StdPrecision</code>: the end result is rounded to this precision
	 * </ul>
	 *
	 * Note that this method makes use of {@link #calculatedQtyInPriceUOM(BigDecimal, I_C_InvoiceLine)}.
	 *
	 * @param invoiceLine
	 */
	void updateQtyInvoicedInPriceUomAndLineNetAmt(I_C_InvoiceLine invoiceLine);

	/**
	 * Call {@link IInvoiceBL#isTaxIncluded(I_C_Invoice, I_C_Tax)} for the given <code>invoiceLine</code>'s <code>C_Invoice</code> and <code>C_Tax</code>.
	 *
	 * @param invoiceLine
	 * @return
	 */
	boolean isTaxIncluded(org.compiere.model.I_C_InvoiceLine invoiceLine);

	/**
	 * Calculate Tax Amt. Assumes Line Net is calculated
	 */
	void setTaxAmt(I_C_InvoiceLine invoiceLine);

	/**
	 * Uses the given <code>qty</code> and the given <code>invoiceLine</code>'s <code>C_UOM</code> and <code>Price_UOM</code> to compute the qantity in proce
	 *
	 * @param qty
	 * @param invoiceLine
	 * @param errorIfNotPossible
	 * @return
	 */
	BigDecimal calculatedQtyInPriceUOM(BigDecimal qty, I_C_InvoiceLine invoiceLine, boolean errorIfNotPossible);
}
