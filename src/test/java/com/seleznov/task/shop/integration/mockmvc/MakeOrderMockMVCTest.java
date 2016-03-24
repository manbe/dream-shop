package com.seleznov.task.shop.integration.mockmvc;

import com.google.gson.Gson;
import com.seleznov.task.shop.DreamShopApplication;
import com.seleznov.task.shop.order.view.OrderView;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static com.seleznov.task.shop.integration.util.CreateViewHelper.prepareOrderView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author seleznev on 24.03.16.
 */

public class MakeOrderMockMVCTest extends BaseMockMVCTest {

    public static final Long STOCK_KEEPING_UNIT_ID = 1l;

    @Test
    @Ignore
    public void makeOrderTest() throws Exception {
        OrderView orderView = prepareOrderView(2, STOCK_KEEPING_UNIT_ID);

        Gson gson = new Gson();
        String json = gson.toJson(orderView);
        mockMvc.perform(post("/shop/api/customer/1/order").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", Matchers.equalTo(20)));

    }

}
