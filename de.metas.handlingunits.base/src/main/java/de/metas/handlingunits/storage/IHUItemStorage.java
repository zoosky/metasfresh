package de.metas.handlingunits.storage;

/*
 * #%L
 * de.metas.handlingunits.base
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;

import de.metas.handlingunits.IHUCapacityDefinition;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.allocation.IAllocationRequest;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Item;

/**
 * HU Item Storage
 *
 * NOTE:
 * <ul>
 * <li>{@link #requestQtyToAllocate(IAllocationRequest)} and {@link #requestQtyToDeallocate(IAllocationRequest)} are allowed only on virtual HU items because only on those we allocate/deallocate
 * </ul>
 *
 * @author tsa
 *
 */
public interface IHUItemStorage extends IGenericHUStorage
{
	@Override
	IHUStorageFactory getHUStorageFactory();

	@Override
	IHUStorage getParentStorage();

	@Override
	void addQty(I_M_Product product, BigDecimal qty, I_C_UOM uom);

	@Override
	BigDecimal getQty(I_M_Product product, I_C_UOM uom);

	@Override
	boolean isEmpty();

	@Override
	boolean isEmpty(I_M_Product product);

	I_M_HU_Item getM_HU_Item();

	/**
	 * Gets total capacity.
	 *
	 * If a custom capacity is set (see {@link #setCustomCapacity(IHUCapacityDefinition)}), that one will be considered first.
	 *
	 * @param product
	 * @param uom
	 * @param date
	 * @return total capacity
	 */
	IHUCapacityDefinition getCapacity(I_M_Product product, I_C_UOM uom, Date date);

	/**
	 * Override current total capacity settings
	 *
	 * @param capacity
	 * @see #getCapacity(I_M_Product, I_C_UOM, Date)
	 */
	void setCustomCapacity(IHUCapacityDefinition capacity);

	/**
	 * Gets available capacity (i.e. how much is free)
	 *
	 * @param product
	 * @param uom
	 * @param date
	 * @return available capacity
	 */
	IHUCapacityDefinition getAvailableCapacity(I_M_Product product, I_C_UOM uom, Date date);

	IAllocationRequest requestQtyToAllocate(IAllocationRequest request);

	IAllocationRequest requestQtyToDeallocate(IAllocationRequest request);

	boolean requestNewHU();

	boolean releaseHU(I_M_HU hu);

	int getHUCount();

	int getHUCapacity();

	IProductStorage getProductStorage(I_M_Product product, I_C_UOM uom, Date date);

	List<IProductStorage> getProductStorages(Date date);

	/**
	 *
	 * @return true if this storage allows negative storages
	 */
	boolean isAllowNegativeStorage();

	/**
	 * @return true if the item is part of a virtual HU.
	 */
	@Override
	boolean isVirtual();

	/**
	 * @return true if the item is pure virtual (i.e. {@link #isVirtual()} and the HU is linked to a material item)
	 * @see IHandlingUnitsBL#isPureVirtual(I_M_HU_Item)
	 */
	boolean isPureVirtual();
}
