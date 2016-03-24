package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.DreamShopApplication;
import com.seleznov.task.shop.integration.config.IntegrationTestConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


/**
 * @author illcko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = {IntegrationTestConfig.class, DreamShopApplication.class})
@TestPropertySource(locations="classpath:test.properties")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseIntegrationTest {

    public static final String HTTP_LOCALHOST = "http://localhost:";
    public static final String SHOP_API = "/shop/api";
    public static final String CUSTOMER = "/customer/";

    @Value("${local.server.port}")
    protected int targetWebServerPort;

    protected String BASE_URL;

    @Before
    public void createURLs(){
        BASE_URL = HTTP_LOCALHOST +targetWebServerPort + SHOP_API;
    }


}
