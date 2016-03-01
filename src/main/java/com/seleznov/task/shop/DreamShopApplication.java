package com.seleznov.task.shop;

import com.seleznov.task.shop.config.ConverterConfig;
import com.seleznov.task.shop.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SwaggerConfig.class, ConverterConfig.class})
public class DreamShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamShopApplication.class, args);
	}
}
