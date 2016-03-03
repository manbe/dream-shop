package com.seleznov.task.shop.sku;

import com.seleznov.task.shop.exception.IllegalDecreaseAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author illcko
 */
@Service

public class StockKeepingUnitServiceImpl implements StockKeepingUnitService {

    @Autowired
    private StockKeepingUnitRepository stockKeepingUnitRepository;

    @Override
    @Transactional
    public StockKeepingUnit decreaseStockKeepingUnitAmount(Long stockKeepingUnitId, Integer decreaseAmount) {
        StockKeepingUnit stockKeepingUnit = getStockKeepingUnit(stockKeepingUnitId);
        Integer currentAmount = stockKeepingUnit.getAmount();
        Integer newAmount = currentAmount - decreaseAmount;
        if (newAmount < 0) {
            throw new IllegalDecreaseAmountException("Current amount=" + currentAmount + " Decrease amount=" + decreaseAmount);
        }
        stockKeepingUnit.setAmount(newAmount);
        return stockKeepingUnitRepository.save(stockKeepingUnit);

    }

    @Override
    public Collection<StockKeepingUnit> getAllStockKeepingUnits() {
        return stockKeepingUnitRepository.findAll();
    }

    @Override
    @Transactional
    public StockKeepingUnit getStockKeepingUnit(Long id) {
        return stockKeepingUnitRepository.findOne(id);
    }
}
