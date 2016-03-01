package com.seleznov.task.shop.sku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
@RestController
@RequestMapping("shop/api/stockKeepingUnit")
public class StockKeepingUnitController {

    @Autowired
    private StockKeepingUnitService stockKeepingUnitService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<StockKeepingUnitView>> getAllStockKeepingViews(){
        Set<StockKeepingUnitView> stockKeepingUnitViews = stockKeepingUnitService.getAllStockKeepingUnits().stream()
                .map(stockKeepingUnit -> conversionService.convert(stockKeepingUnit, StockKeepingUnitView.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(stockKeepingUnitViews, HttpStatus.OK);
    }

}
