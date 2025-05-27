package com.example.blinddate.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.blinddate.common.CodeUtil;
import com.example.blinddate.common.SmsTool;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
public class SmsCtrl {

    /**
     * 发送短信
     * @param phone
     * @param request
     * @return
     * @throws ClientException
     */
    @RequestMapping(value = "/smsXxs",  method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> smsXxs(String phone, HttpServletRequest request) throws ClientException {
        Map<String,Object> map = new HashMap<>();
        // 验证码（指定长度的随机数）
        String code = CodeUtil.generateVerifyCode(6);
        String TemplateParam = "{\"code\":\""+code+"\"}";
        // 短信模板id
        String TemplateCode = "SMS_465725397";
        SendSmsResponse response = SmsTool.sendSms(phone,TemplateParam,TemplateCode);
        map.put("verifyCode",code);
        map.put("phone",phone);
        if(response.getCode().equals("OK")) {
            map.put("isOk","OK");

        }
        return map;
    }
}
