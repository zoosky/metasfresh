/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package de.metas.handlingunits.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for DD_OrderLine_HU_Candidate
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public class X_DD_OrderLine_HU_Candidate extends org.compiere.model.PO implements I_DD_OrderLine_HU_Candidate, org.compiere.model.I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1464720323L;

    /** Standard Constructor */
    public X_DD_OrderLine_HU_Candidate (Properties ctx, int DD_OrderLine_HU_Candidate_ID, String trxName)
    {
      super (ctx, DD_OrderLine_HU_Candidate_ID, trxName);
      /** if (DD_OrderLine_HU_Candidate_ID == 0)
        {
			setDD_OrderLine_HU_Candidate_ID (0);
			setDD_OrderLine_ID (0);
			setM_HU_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_OrderLine_HU_Candidate (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }


    /** Load Meta Data */
    @Override
    protected org.compiere.model.POInfo initPO (Properties ctx)
    {
      org.compiere.model.POInfo poi = org.compiere.model.POInfo.getPOInfo (ctx, Table_Name, get_TrxName());
      return poi;
    }

	/** Set Distribution Order Line HU Candidate.
		@param DD_OrderLine_HU_Candidate_ID Distribution Order Line HU Candidate	  */
	@Override
	public void setDD_OrderLine_HU_Candidate_ID (int DD_OrderLine_HU_Candidate_ID)
	{
		if (DD_OrderLine_HU_Candidate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_HU_Candidate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_HU_Candidate_ID, Integer.valueOf(DD_OrderLine_HU_Candidate_ID));
	}

	/** Get Distribution Order Line HU Candidate.
		@return Distribution Order Line HU Candidate	  */
	@Override
	public int getDD_OrderLine_HU_Candidate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OrderLine_HU_Candidate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_DD_OrderLine getDD_OrderLine() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_DD_OrderLine_ID, org.eevolution.model.I_DD_OrderLine.class);
	}

	@Override
	public void setDD_OrderLine(org.eevolution.model.I_DD_OrderLine DD_OrderLine)
	{
		set_ValueFromPO(COLUMNNAME_DD_OrderLine_ID, org.eevolution.model.I_DD_OrderLine.class, DD_OrderLine);
	}

	/** Set Distribution Order Line.
		@param DD_OrderLine_ID Distribution Order Line	  */
	@Override
	public void setDD_OrderLine_ID (int DD_OrderLine_ID)
	{
		if (DD_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_ID, Integer.valueOf(DD_OrderLine_ID));
	}

	/** Get Distribution Order Line.
		@return Distribution Order Line	  */
	@Override
	public int getDD_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	public de.metas.handlingunits.model.I_M_HU getM_HU() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_M_HU_ID, de.metas.handlingunits.model.I_M_HU.class);
	}

	@Override
	public void setM_HU(de.metas.handlingunits.model.I_M_HU M_HU)
	{
		set_ValueFromPO(COLUMNNAME_M_HU_ID, de.metas.handlingunits.model.I_M_HU.class, M_HU);
	}

	/** Set Handling Units.
		@param M_HU_ID Handling Units	  */
	@Override
	public void setM_HU_ID (int M_HU_ID)
	{
		if (M_HU_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_HU_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_HU_ID, Integer.valueOf(M_HU_ID));
	}

	/** Get Handling Units.
		@return Handling Units	  */
	@Override
	public int getM_HU_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_HU_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}