package com.tanzl.paycenter.service;

import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.tanzl.paycenter.Bootstrap;
import com.tanzl.paycenter.common.BaseResponse;
import com.tanzl.paycenter.service.CiticWxgzhPayService.RechargeWxRequet;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
@WebAppConfiguration
public class CiticWxgzhPayServiceMockTest extends MockitoBasedTest {
 //String jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"0\"}";
    /**
     * 被@Mock标注的对象会自动注入到被@InjectMocks标注的对象中
     */
	@Mock
    private CiticWxgzhPayService citicWxgzhPayService;
    @InjectMocks
    private CiticWxgzhPayService citicWxgzhPayServiceMock = new CiticWxgzhPayService();

    /**签名失败
     * @throws Exception
     */
    @Test
    public void testRechargeSignFail() throws Exception {
    	String jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"0\"}";
        /**
         * statsu :0 表示成功非 0 表示失败此字段是通信标识， 非交易标识， 交易是否成功需要查看 result_code 来判断
         */
        /**
         * 签名失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnotSignjsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"1\"}";
        Mockito.doReturn(JSON.parseObject(statusnotSignjsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnotSignjsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        BaseResponse<String> result= citicWxgzhPayServiceMock.recharge(new RechargeWxRequet());
        org.junit.Assert.assertEquals(BaseResponse.RC_FAIL, result.getRc());
        /**
         * statsu :非 0 表示通信失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnot0jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"1\"}";
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        BaseResponse<String> statusnot0result= citicWxgzhPayServiceMock.recharge(Mockito.any(RechargeWxRequet.class));
        org.junit.Assert.assertEquals(BaseResponse.RC_FAIL, statusnot0result.getRc());
        
        // Mockito.when(citicWxgzhPayServiceMock.doSendReq(entityParams)).thenReturn(JSON.parseObject(statusnotSignjsonMap, Map.class));
       
        
        try {
         //   System.out.println(JSON.toJSONString(citicWxgzhPayServiceMock.recharge(rechargeWxRequet)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**通信失败 
     * statsu :0 表示成功非 0 表示失败此字段是通信标识， 非交易标识， 交易是否成功需要查看 result_code 来判断
     * @throws Exception
     */
    @Test
    public void testRechargeTXFail() throws Exception {
    	 /**
         * statsu :非 0 表示通信失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnot0jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"1\"}";
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        Mockito.doReturn(true).when(citicWxgzhPayServiceMock).verifySign(Mockito.any(Map.class));
        BaseResponse<String> statusnot0result= citicWxgzhPayServiceMock.recharge(Mockito.any(RechargeWxRequet.class));
        org.junit.Assert.assertEquals(BaseResponse.RC_FAIL, statusnot0result.getRc());
    }
    
    
    /**通信成功 statsu :0 表示成功非 0   result_code 没返回  交易是否成功  结果返回失败
     * @throws Exception
     */
    @Test
    public void testRechargeTradeFail1() throws Exception {
    	 /**
         * statsu :非 0 表示通信失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnot0jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"1\"}";
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        Mockito.doReturn(true).when(citicWxgzhPayServiceMock).verifySign(Mockito.any(Map.class));
        BaseResponse<String> statusnot0result= citicWxgzhPayServiceMock.recharge(Mockito.any(RechargeWxRequet.class));
        org.junit.Assert.assertEquals(BaseResponse.RC_FAIL, statusnot0result.getRc());
    }
    
    
    /**通信成功 statsu :0 表示成功非 0   result_code 有返回 返回0
     * @throws Exception
     */
    @Test
    public void testRechargeTradeSuccess() throws Exception {
    	 /**
         * statsu :非 0 表示通信失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnot0jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"0\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"0\"}";
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        Mockito.doReturn(true).when(citicWxgzhPayServiceMock).verifySign(Mockito.any(Map.class));
        BaseResponse<String> statusnot0result= citicWxgzhPayServiceMock.recharge(Mockito.any(RechargeWxRequet.class));
        org.junit.Assert.assertEquals(BaseResponse.RC_SUCCESS, statusnot0result.getRc());
    }
    
    
    /**通信成功 statsu :0 表示成功非 0   result_code 有返回 返回非0
     * @throws Exception
     */
    @Test
    public void testRechargeTradeFail() throws Exception {
    	 /**
         * statsu :非 0 表示通信失败
         */
        citicWxgzhPayServiceMock = Mockito.spy(citicWxgzhPayServiceMock);
        String statusnot0jsonMap="{\"charset\":\"UTF-8\",\"nonce_str\":\"wxzf2017050300012\",\"token_id\":\"23fffc23e542648e1de1c006ee2eb4a2e\",\"appid\":\"wx1f87d44db95cba7a\",\"sign\":\"6111FA36CBEBD2C4844B2E9474774B55\",\"result_code\":\"2\",\"mch_id\":\"7551000001\",\"sign_type\":\"MD5\",\"version\":\"2.0\",\"status\":\"0\"}";
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).getReqMap(Mockito.any(RechargeWxRequet.class));
        Mockito.doReturn(JSON.parseObject(statusnot0jsonMap, Map.class)).when(citicWxgzhPayServiceMock).doSendReq(Mockito.any(StringEntity.class));
        Mockito.doReturn(true).when(citicWxgzhPayServiceMock).verifySign(Mockito.any(Map.class));
        BaseResponse<String> statusnot0result= citicWxgzhPayServiceMock.recharge(Mockito.any(RechargeWxRequet.class));
        org.junit.Assert.assertEquals(BaseResponse.RC_FAIL, statusnot0result.getRc());
    }
}
