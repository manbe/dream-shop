package com.seleznov.task.shop.sku;

import com.seleznov.task.shop.exception.IllegalDecreaseAmountException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author illcko
 */
@RunWith(MockitoJUnitRunner.class)
public class StockKeepingUnitServiceImplTest {

    public static final int FIRST_AMOUNT = 5;
    public static final long ID = 1l;
    @InjectMocks
    private StockKeepingUnitService stockKeepingUnitService = new StockKeepingUnitServiceImpl();

    @Mock
    private StockKeepingUnitRepository stockKeepingUnitRepository;

    private StockKeepingUnit stockKeepingUnit;


    @Before
    public void init() {
        stockKeepingUnit = new StockKeepingUnit();
        stockKeepingUnit.setVersion(ID);
        stockKeepingUnit.setId(ID);
        stockKeepingUnit.setAmount(FIRST_AMOUNT);
        when(stockKeepingUnitRepository.save(stockKeepingUnit)).thenReturn(stockKeepingUnit);
        when(stockKeepingUnitRepository.findAll()).thenReturn(Arrays.asList(stockKeepingUnit));
        when(stockKeepingUnitRepository.findOne(ID)).thenReturn(stockKeepingUnit);
    }

    @Test
    public void testDecreaseStockKeepingUnitAmount() throws Exception {
        StockKeepingUnit stockKeepingUnitResult = stockKeepingUnitService.decreaseStockKeepingUnitAmount(this.stockKeepingUnit, 3);
        assertEquals(Integer.valueOf(2), stockKeepingUnitResult.getAmount());
    }

    @Test(expected = IllegalDecreaseAmountException.class)
    public void testDecreaseStockKeepingUnitAmountWithIllegalValue() throws Exception {
        stockKeepingUnitService.decreaseStockKeepingUnitAmount(this.stockKeepingUnit, 6);
    }

    @Test
    public void testGetAllStockKeepingUnits() throws Exception {
        Collection<StockKeepingUnit> allStockKeepingUnits = stockKeepingUnitService.getAllStockKeepingUnits();
        assertEquals(stockKeepingUnit, allStockKeepingUnits.iterator().next());
        assertEquals(1, allStockKeepingUnits.size());
    }


}