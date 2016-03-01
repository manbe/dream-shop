package com.seleznov.task.shop.sku;


import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * @author illcko
 */
public class StockKeepingUniConverter implements Converter<StockKeepingUnit, StockKeepingUnitView> {
    @Override
    public StockKeepingUnitView convert(StockKeepingUnit stockKeepingUnit) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(stockKeepingUnit, StockKeepingUnitView.class);
    }
}
