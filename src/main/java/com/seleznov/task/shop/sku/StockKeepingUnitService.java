package com.seleznov.task.shop.sku;

import java.util.Collection;

/**
 * @author illcko
 */
public interface StockKeepingUnitService {

    StockKeepingUnit decreaseStockKeepingUnitAmount(StockKeepingUnit stockKeepingUnit, Integer decreaseAmount);

    Collection<StockKeepingUnit> getAllStockKeepingUnits();

    StockKeepingUnit getStockKeepingUnit(Long id);

}
