package com.seleznov.task.shop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

/**
 * @author illcko
 */
public class ConverterConfig {

    public static final String BASE_PACKAGE = "com.seleznov.task";

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(ConvertersCreateHelper.getConverters(BASE_PACKAGE));
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }




}
