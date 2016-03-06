package com.seleznov.task.shop.integration;

import com.seleznov.task.shop.DreamShopApplication;
import com.seleznov.task.shop.integration.config.IntegrationTestConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author illcko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port:0")
@SpringApplicationConfiguration(classes = {IntegrationTestConfig.class, DreamShopApplication.class})
@TestPropertySource(locations="classpath:test.properties")
public abstract class BaseIntegrationTest {

    @Value("${local.server.port}")
    public int targetWebServerPort;

    protected String BASE_URL;

    @Before
    public void createURLs(){
        BASE_URL = "http://localhost:"+targetWebServerPort + "/shop/api";
    }


}
