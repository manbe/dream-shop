package com.seleznov.task.shop.config;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
public class ConvertersCreateHelper {

    private static final Logger log = LoggerFactory.getLogger(ConvertersCreateHelper.class);
    
    private static final Function<Class<? extends Converter>, Converter> classConverterCreateFunction = converterClass -> {
        try {
            return converterClass.newInstance();
        } catch (Exception ex) {
            log.error("Unable to create converter " + converterClass, ex);
            throw new RuntimeException(ex);
        }
    };

    public static Set<Converter> getConverters(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends Converter>> classes = reflections.getSubTypesOf(Converter.class);

        return classes.stream()
                .map(classConverterCreateFunction)
                .collect(Collectors.toSet());
    }
    
}
