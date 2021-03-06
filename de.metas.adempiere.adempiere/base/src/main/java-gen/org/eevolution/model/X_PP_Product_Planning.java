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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for PP_Product_Planning
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public class X_PP_Product_Planning extends org.compiere.model.PO implements I_PP_Product_Planning, org.compiere.model.I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 245923013L;

    /** Standard Constructor */
    public X_PP_Product_Planning (Properties ctx, int PP_Product_Planning_ID, String trxName)
    {
      super (ctx, PP_Product_Planning_ID, trxName);
      /** if (PP_Product_Planning_ID == 0)
        {
			setIsCreatePlan (true);
// Y
			setIsDocComplete (false);
// N
			setIsPhantom (false);
			setIsRequiredDRP (false);
			setIsRequiredMRP (false);
			setM_Product_ID (0);
			setPP_POQ_AggregateOnBPartnerLevel (false);
// N
			setPP_Product_Planning_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_Product_Planning (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    @Override
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    @Override
    protected org.compiere.model.POInfo initPO (Properties ctx)
    {
      org.compiere.model.POInfo poi = org.compiere.model.POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_PP_Product_Planning[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	@Override
	public org.compiere.model.I_AD_Workflow getAD_Workflow() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_AD_Workflow_ID, org.compiere.model.I_AD_Workflow.class);
	}

	@Override
	public void setAD_Workflow(org.compiere.model.I_AD_Workflow AD_Workflow)
	{
		set_ValueFromPO(COLUMNNAME_AD_Workflow_ID, org.compiere.model.I_AD_Workflow.class, AD_Workflow);
	}

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	@Override
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1) 
			set_Value (COLUMNNAME_AD_Workflow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	@Override
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_C_BPartner_ID, org.compiere.model.I_C_BPartner.class);
	}

	@Override
	public void setC_BPartner(org.compiere.model.I_C_BPartner C_BPartner)
	{
		set_ValueFromPO(COLUMNNAME_C_BPartner_ID, org.compiere.model.I_C_BPartner.class, C_BPartner);
	}

	/** Set Geschäftspartner.
		@param C_BPartner_ID 
		Bezeichnet einen Geschäftspartner
	  */
	@Override
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Geschäftspartner.
		@return Bezeichnet einen Geschäftspartner
	  */
	@Override
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_DD_NetworkDistribution getDD_NetworkDistribution() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_DD_NetworkDistribution_ID, org.eevolution.model.I_DD_NetworkDistribution.class);
	}

	@Override
	public void setDD_NetworkDistribution(org.eevolution.model.I_DD_NetworkDistribution DD_NetworkDistribution)
	{
		set_ValueFromPO(COLUMNNAME_DD_NetworkDistribution_ID, org.eevolution.model.I_DD_NetworkDistribution.class, DD_NetworkDistribution);
	}

	/** Set Network Distribution.
		@param DD_NetworkDistribution_ID Network Distribution	  */
	@Override
	public void setDD_NetworkDistribution_ID (int DD_NetworkDistribution_ID)
	{
		if (DD_NetworkDistribution_ID < 1) 
			set_Value (COLUMNNAME_DD_NetworkDistribution_ID, null);
		else 
			set_Value (COLUMNNAME_DD_NetworkDistribution_ID, Integer.valueOf(DD_NetworkDistribution_ID));
	}

	/** Get Network Distribution.
		@return Network Distribution	  */
	@Override
	public int getDD_NetworkDistribution_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_NetworkDistribution_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Zugesicherte Lieferzeit.
		@param DeliveryTime_Promised 
		Promised days between order and delivery
	  */
	@Override
	public void setDeliveryTime_Promised (java.math.BigDecimal DeliveryTime_Promised)
	{
		set_Value (COLUMNNAME_DeliveryTime_Promised, DeliveryTime_Promised);
	}

	/** Get Zugesicherte Lieferzeit.
		@return Promised days between order and delivery
	  */
	@Override
	public java.math.BigDecimal getDeliveryTime_Promised () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeliveryTime_Promised);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Create Plan.
		@param IsCreatePlan 
		Indicates whether planned orders will be generated by MRP
	  */
	@Override
	public void setIsCreatePlan (boolean IsCreatePlan)
	{
		set_Value (COLUMNNAME_IsCreatePlan, Boolean.valueOf(IsCreatePlan));
	}

	/** Get Create Plan.
		@return Indicates whether planned orders will be generated by MRP
	  */
	@Override
	public boolean isCreatePlan () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreatePlan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsDocComplete .
		@param IsDocComplete IsDocComplete 	  */
	@Override
	public void setIsDocComplete (boolean IsDocComplete)
	{
		set_ValueNoCheck (COLUMNNAME_IsDocComplete, Boolean.valueOf(IsDocComplete));
	}

	/** Get IsDocComplete .
		@return IsDocComplete 	  */
	@Override
	public boolean isDocComplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsDocComplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** 
	 * IsManufactured AD_Reference_ID=319
	 * Reference name: _YesNo
	 */
	public static final int ISMANUFACTURED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISMANUFACTURED_Yes = "Y";
	/** No = N */
	public static final String ISMANUFACTURED_No = "N";
	/** Set Manufactured.
		@param IsManufactured Manufactured	  */
	@Override
	public void setIsManufactured (java.lang.String IsManufactured)
	{

		set_Value (COLUMNNAME_IsManufactured, IsManufactured);
	}

	/** Get Manufactured.
		@return Manufactured	  */
	@Override
	public java.lang.String getIsManufactured () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_IsManufactured);
	}

	/** Set Is MPS.
		@param IsMPS Is MPS	  */
	@Override
	public void setIsMPS (boolean IsMPS)
	{
		set_Value (COLUMNNAME_IsMPS, Boolean.valueOf(IsMPS));
	}

	/** Get Is MPS.
		@return Is MPS	  */
	@Override
	public boolean isMPS () 
	{
		Object oo = get_Value(COLUMNNAME_IsMPS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Phantom.
		@param IsPhantom 
		Phantom Component
	  */
	@Override
	public void setIsPhantom (boolean IsPhantom)
	{
		set_Value (COLUMNNAME_IsPhantom, Boolean.valueOf(IsPhantom));
	}

	/** Get Phantom.
		@return Phantom Component
	  */
	@Override
	public boolean isPhantom () 
	{
		Object oo = get_Value(COLUMNNAME_IsPhantom);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** 
	 * IsPurchased AD_Reference_ID=319
	 * Reference name: _YesNo
	 */
	public static final int ISPURCHASED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISPURCHASED_Yes = "Y";
	/** No = N */
	public static final String ISPURCHASED_No = "N";
	/** Set Eingekauft.
		@param IsPurchased 
		Die Organisation kauft dieses Produkt ein
	  */
	@Override
	public void setIsPurchased (java.lang.String IsPurchased)
	{

		set_Value (COLUMNNAME_IsPurchased, IsPurchased);
	}

	/** Get Eingekauft.
		@return Die Organisation kauft dieses Produkt ein
	  */
	@Override
	public java.lang.String getIsPurchased () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_IsPurchased);
	}

	/** Set Required Calculate DRP.
		@param IsRequiredDRP Required Calculate DRP	  */
	@Override
	public void setIsRequiredDRP (boolean IsRequiredDRP)
	{
		set_ValueNoCheck (COLUMNNAME_IsRequiredDRP, Boolean.valueOf(IsRequiredDRP));
	}

	/** Get Required Calculate DRP.
		@return Required Calculate DRP	  */
	@Override
	public boolean isRequiredDRP () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequiredDRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required Calculate MRP.
		@param IsRequiredMRP Required Calculate MRP	  */
	@Override
	public void setIsRequiredMRP (boolean IsRequiredMRP)
	{
		set_ValueNoCheck (COLUMNNAME_IsRequiredMRP, Boolean.valueOf(IsRequiredMRP));
	}

	/** Get Required Calculate MRP.
		@return Required Calculate MRP	  */
	@Override
	public boolean isRequiredMRP () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequiredMRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	@Override
	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_M_Product_ID, org.compiere.model.I_M_Product.class);
	}

	@Override
	public void setM_Product(org.compiere.model.I_M_Product M_Product)
	{
		set_ValueFromPO(COLUMNNAME_M_Product_ID, org.compiere.model.I_M_Product.class, M_Product);
	}

	/** Set Produkt.
		@param M_Product_ID 
		Produkt, Leistung, Artikel
	  */
	@Override
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Produkt.
		@return Produkt, Leistung, Artikel
	  */
	@Override
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_M_Warehouse_ID, org.compiere.model.I_M_Warehouse.class);
	}

	@Override
	public void setM_Warehouse(org.compiere.model.I_M_Warehouse M_Warehouse)
	{
		set_ValueFromPO(COLUMNNAME_M_Warehouse_ID, org.compiere.model.I_M_Warehouse.class, M_Warehouse);
	}

	/** Set Lager.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	@Override
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Lager.
		@return Storage Warehouse and Service Point
	  */
	@Override
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Order Qty.
		@param Order_Max 
		Maximum order quantity in UOM
	  */
	@Override
	public void setOrder_Max (java.math.BigDecimal Order_Max)
	{
		set_Value (COLUMNNAME_Order_Max, Order_Max);
	}

	/** Get Maximum Order Qty.
		@return Maximum order quantity in UOM
	  */
	@Override
	public java.math.BigDecimal getOrder_Max () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Max);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Mindestbestellmenge.
		@param Order_Min 
		Minimum order quantity in UOM
	  */
	@Override
	public void setOrder_Min (java.math.BigDecimal Order_Min)
	{
		set_Value (COLUMNNAME_Order_Min, Order_Min);
	}

	/** Get Mindestbestellmenge.
		@return Minimum order quantity in UOM
	  */
	@Override
	public java.math.BigDecimal getOrder_Min () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Min);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Packungsgröße.
		@param Order_Pack 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	@Override
	public void setOrder_Pack (java.math.BigDecimal Order_Pack)
	{
		set_Value (COLUMNNAME_Order_Pack, Order_Pack);
	}

	/** Get Packungsgröße.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	@Override
	public java.math.BigDecimal getOrder_Pack () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Pack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Period.
		@param Order_Period Order Period	  */
	@Override
	public void setOrder_Period (java.math.BigDecimal Order_Period)
	{
		set_Value (COLUMNNAME_Order_Period, Order_Period);
	}

	/** Get Order Period.
		@return Order Period	  */
	@Override
	public java.math.BigDecimal getOrder_Period () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Period);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** 
	 * Order_Policy AD_Reference_ID=53228
	 * Reference name: PP_Product_Planning Order Policy
	 */
	public static final int ORDER_POLICY_AD_Reference_ID=53228;
	/** Fixed Order Quantity = FOQ */
	public static final String ORDER_POLICY_FixedOrderQuantity = "FOQ";
	/** Lot-for-Lot = LFL */
	public static final String ORDER_POLICY_Lot_For_Lot = "LFL";
	/** Period Order Quantity = POQ */
	public static final String ORDER_POLICY_PeriodOrderQuantity = "POQ";
	/** Set Order Policy.
		@param Order_Policy Order Policy	  */
	@Override
	public void setOrder_Policy (java.lang.String Order_Policy)
	{

		set_Value (COLUMNNAME_Order_Policy, Order_Policy);
	}

	/** Get Order Policy.
		@return Order Policy	  */
	@Override
	public java.lang.String getOrder_Policy () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_Order_Policy);
	}

	/** Set Order Qty.
		@param Order_Qty Order Qty	  */
	@Override
	public void setOrder_Qty (java.math.BigDecimal Order_Qty)
	{
		set_Value (COLUMNNAME_Order_Qty, Order_Qty);
	}

	/** Get Order Qty.
		@return Order Qty	  */
	@Override
	public java.math.BigDecimal getOrder_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	@Override
	public org.compiere.model.I_AD_User getPlanner() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_Planner_ID, org.compiere.model.I_AD_User.class);
	}

	@Override
	public void setPlanner(org.compiere.model.I_AD_User Planner)
	{
		set_ValueFromPO(COLUMNNAME_Planner_ID, org.compiere.model.I_AD_User.class, Planner);
	}

	/** Set Planner.
		@param Planner_ID Planner	  */
	@Override
	public void setPlanner_ID (int Planner_ID)
	{
		if (Planner_ID < 1) 
			set_Value (COLUMNNAME_Planner_ID, null);
		else 
			set_Value (COLUMNNAME_Planner_ID, Integer.valueOf(Planner_ID));
	}

	/** Get Planner.
		@return Planner	  */
	@Override
	public int getPlanner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Planner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Aggregate on BPartner Level.
		@param PP_POQ_AggregateOnBPartnerLevel Aggregate on BPartner Level	  */
	@Override
	public void setPP_POQ_AggregateOnBPartnerLevel (boolean PP_POQ_AggregateOnBPartnerLevel)
	{
		set_Value (COLUMNNAME_PP_POQ_AggregateOnBPartnerLevel, Boolean.valueOf(PP_POQ_AggregateOnBPartnerLevel));
	}

	/** Get Aggregate on BPartner Level.
		@return Aggregate on BPartner Level	  */
	@Override
	public boolean isPP_POQ_AggregateOnBPartnerLevel () 
	{
		Object oo = get_Value(COLUMNNAME_PP_POQ_AggregateOnBPartnerLevel);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	@Override
	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_PP_Product_BOM_ID, org.eevolution.model.I_PP_Product_BOM.class);
	}

	@Override
	public void setPP_Product_BOM(org.eevolution.model.I_PP_Product_BOM PP_Product_BOM)
	{
		set_ValueFromPO(COLUMNNAME_PP_Product_BOM_ID, org.eevolution.model.I_PP_Product_BOM.class, PP_Product_BOM);
	}

	/** Set BOM & Formula.
		@param PP_Product_BOM_ID 
		BOM & Formula
	  */
	@Override
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, Integer.valueOf(PP_Product_BOM_ID));
	}

	/** Get BOM & Formula.
		@return BOM & Formula
	  */
	@Override
	public int getPP_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Planning.
		@param PP_Product_Planning_ID Product Planning	  */
	@Override
	public void setPP_Product_Planning_ID (int PP_Product_Planning_ID)
	{
		if (PP_Product_Planning_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Product_Planning_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Product_Planning_ID, Integer.valueOf(PP_Product_Planning_ID));
	}

	/** Get Product Planning.
		@return Product Planning	  */
	@Override
	public int getPP_Product_Planning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_Planning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Safety Stock Qty.
		@param SafetyStock 
		Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	@Override
	public void setSafetyStock (java.math.BigDecimal SafetyStock)
	{
		set_Value (COLUMNNAME_SafetyStock, SafetyStock);
	}

	/** Get Safety Stock Qty.
		@return Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	@Override
	public java.math.BigDecimal getSafetyStock () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SafetyStock);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	@Override
	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
	{
		return get_ValueAsPO(COLUMNNAME_S_Resource_ID, org.compiere.model.I_S_Resource.class);
	}

	@Override
	public void setS_Resource(org.compiere.model.I_S_Resource S_Resource)
	{
		set_ValueFromPO(COLUMNNAME_S_Resource_ID, org.compiere.model.I_S_Resource.class, S_Resource);
	}

	/** Set Ressource.
		@param S_Resource_ID 
		Resource
	  */
	@Override
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Ressource.
		@return Resource
	  */
	@Override
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Time Fence.
		@param TimeFence Time Fence	  */
	@Override
	public void setTimeFence (java.math.BigDecimal TimeFence)
	{
		set_Value (COLUMNNAME_TimeFence, TimeFence);
	}

	/** Get Time Fence.
		@return Time Fence	  */
	@Override
	public java.math.BigDecimal getTimeFence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TimeFence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transfert Time.
		@param TransfertTime Transfert Time	  */
	@Override
	public void setTransfertTime (java.math.BigDecimal TransfertTime)
	{
		set_Value (COLUMNNAME_TransfertTime, TransfertTime);
	}

	/** Get Transfert Time.
		@return Transfert Time	  */
	@Override
	public java.math.BigDecimal getTransfertTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TransfertTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Working Time.
		@param WorkingTime 
		Workflow Simulation Execution Time
	  */
	@Override
	public void setWorkingTime (java.math.BigDecimal WorkingTime)
	{
		set_Value (COLUMNNAME_WorkingTime, WorkingTime);
	}

	/** Get Working Time.
		@return Workflow Simulation Execution Time
	  */
	@Override
	public java.math.BigDecimal getWorkingTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WorkingTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Yield %.
		@param Yield 
		The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	@Override
	public void setYield (int Yield)
	{
		set_Value (COLUMNNAME_Yield, Integer.valueOf(Yield));
	}

	/** Get Yield %.
		@return The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	@Override
	public int getYield () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Yield);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}