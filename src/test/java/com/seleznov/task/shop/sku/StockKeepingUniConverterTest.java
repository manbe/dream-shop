package com.seleznov.task.shop.sku;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author illcko
 */
public class StockKeepingUniConverterTest {

    public static final Long ID = 1l;
    public static final Integer AMOUNT = 5;
    public static final String NAME = "name";
    public static final Integer PRICE = 10;

    @Test
    public void testConvert() throws Exception {
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit();
        stockKeepingUnit.setId(ID);
        stockKeepingUnit.setAmount(AMOUNT);
        stockKeepingUnit.setName(NAME);
        stockKeepingUnit.setPrice(PRICE);

        StockKeepingUniConverter stockKeepingUniConverter = new StockKeepingUniConverter();
        StockKeepingUnitView stockKeepingUnitView = stockKeepingUniConverter.convert(stockKeepingUnit);
        assertEquals(ID, stockKeepingUnitView.getId());
        assertEquals(AMOUNT, stockKeepingUnitView.getAmount());
        assertEquals(NAME, stockKeepingUnitView.getName());
        assertEquals(PRICE, stockKeepingUnitView.getPrice());
    }
}