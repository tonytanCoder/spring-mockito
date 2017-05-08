package com.tanzl.paycenter.service;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tanzl.paycenter.common.BaseResponse;

/**
 * 威信通（中信）微信公众号支付
 * 
 * @author tan
 *
 */
@Service
@Slf4j
public class CiticWxgzhPayService {
    public BaseResponse<String> recharge(RechargeWxRequet rechargeWxRequet) throws Exception {
        BaseResponse<String> response = new BaseResponse<String>();
        try {
            log.info("中信微信支付充值请求参数,{}", JSON.toJSONString(rechargeWxRequet));
            // 检验结束
            // 获取用户信息
            Map<String, String> messageMap = getReqMap(rechargeWxRequet);
            log.info("中信微信支付充值请求参数messageMap;{}", JSON.toJSONString(messageMap));
            // Map<String, String> resultMap = this.doProcess(messageMap);
            // 将入参转成提现指定报文
            StringEntity specifiesMsg = transform(messageMap);
            // 发送报文
            Map<String, String> messageResult = doSendReq(specifiesMsg);
            log.info("中信微信支付充值请求参数messageResult;{}", JSON.toJSONString(messageResult));

            if (!verifySign(messageResult)) {
                response.setRc(BaseResponse.RC_FAIL);
                response.setData(null);
                response.setMsg("失败");
                return response;
            }
            if ("0".equals(messageResult.get("status"))
                    && "0".equals(messageResult.get("result_code"))
                    && !StringUtils.isBlank(messageResult.get("pay_info"))) {
                log.info(" pay_info:{}", messageResult.get("pay_info"));
                response.setRc(BaseResponse.RC_SUCCESS);
                response.setData(messageResult.get("pay_info"));
                response.setMsg("成功");
                return response;
            }
            if ("0".equals(messageResult.get("status"))
                    && "0".equals(messageResult.get("result_code"))
                    && !StringUtils.isBlank(messageResult.get("token_id"))) {
                log.info(" token_id:{}", messageResult.get("token_id"));
                response.setRc(BaseResponse.RC_SUCCESS);
                response.setData(messageResult.get("token_id"));
                response.setMsg("成功");
                return response;

            }

        } catch (Exception e) {
            log.error(" recharge Exception:{} ", e);
        }
        response.setRc(BaseResponse.RC_FAIL);
        response.setData(null);
        response.setMsg("失败");
        return response;
    }

    public Map<String, String> getReqMap(RechargeWxRequet rechargeWxRequet) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }


    protected StringEntity transform(Map<String, String> message){
        StringEntity entityParams = new StringEntity("", "utf-8");
        return entityParams;
    }

    protected Map<String, String> doSendReq(StringEntity specifiesMsg) {
        Map<String, String> resultMap = new HashMap<String, String>();
        return resultMap;
    }

    
    
    public boolean verifySign(Map<String,String> messageResult){
    	return true;
    }
    
    static class RechargeWxRequet{
    	
    }
}
