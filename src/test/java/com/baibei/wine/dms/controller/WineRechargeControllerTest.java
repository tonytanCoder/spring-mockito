package com.baibei.wine.dms.controller;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.baibei.wine.paycenter.Bootstrap;
import com.baibei.wine.paycenter.vo.request.RechargeH5Requet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Bootstrap.class)
@WebIntegrationTest({"server.port=5050", "management.port=1010"})
public class WineRechargeControllerTest {
    // private RestTemplate restTemplate;
    @Value("${local.server.port}")
    protected int port;
    protected URL base;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/pgw/winerecharg");
        // template = new TestRestTemplate();
    }

//    @Test
//    public void testDoRechargeJdH5() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        RechargeH5Requet rechargeH5Request = new RechargeH5Requet();
//        rechargeH5Request.setAccountId(123L);
//        rechargeH5Request.setAmount(1000L);
//        rechargeH5Request.setBankAccount("6225882012044555");
//        rechargeH5Request.setBusinessType("WINE");
//        rechargeH5Request.setCallbackUrl("http://www.wine.com/callbackUrl");
//        rechargeH5Request.setChannelCode("JDPAYH5");
//        rechargeH5Request.setOrderId(System.currentTimeMillis() + "");
//        rechargeH5Request.setUserId("123");
//        HttpEntity<RechargeH5Requet> formEntity = new HttpEntity<RechargeH5Requet>(rechargeH5Request, headers);
//        String result =
//                restTemplate.postForObject(String.format("%s/%s", base.toString(), "/dorechargeh5"),
//                        formEntity,
//                        String.class);
//        System.out.println(result);
//    }
    
    @Test
    public void testDoRechargeTLH5() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        RechargeH5Requet rechargeH5Request = new RechargeH5Requet();
        rechargeH5Request.setAccountId(123L);
        rechargeH5Request.setAmount(1L);
        rechargeH5Request.setBankAccount("6228480089489739071");
        rechargeH5Request.setBusinessType("WINE");
        rechargeH5Request.setCallbackUrl("http://www.wine.com/callbackUrl");
        rechargeH5Request.setChannelCode("TLPAYH5");
        rechargeH5Request.setOrderId(System.currentTimeMillis() + "");
        rechargeH5Request.setUserId("123");
        HttpEntity<RechargeH5Requet> formEntity = new HttpEntity<RechargeH5Requet>(rechargeH5Request, headers);
        String result =
                restTemplate.postForObject(String.format("%s/%s", base.toString(), "/dorechargeh5"),
                        formEntity,
                        String.class);
        System.out.println(result);
    }
    
    
}
