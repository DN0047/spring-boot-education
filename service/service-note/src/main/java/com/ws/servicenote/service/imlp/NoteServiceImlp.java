package com.ws.servicenote.service.imlp;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.annotations.Until;
import com.ws.servicenote.service.NoteService;
import com.ws.servicenote.vo.NoteUntil;
import com.ws.servicenote.vo.untils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class NoteServiceImlp implements NoteService {



    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean noteSms(String pp) {
        if(StringUtils.isEmpty(pp)) return false;
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        String s = untils.nextForsint();
        objectObjectHashMap.put("code",s);

        DefaultProfile profile =
                DefaultProfile.getProfile("default", NoteUntil.ACCESS_KEY_ID, NoteUntil.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", pp);
        request.putQueryParameter("SignName", "教育网站");
        request.putQueryParameter("TemplateCode", NoteUntil.ACCESS_TEMPPATECODE);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(objectObjectHashMap));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            boolean success = response.getHttpResponse().isSuccess();
            if (success){
                redisTemplate.opsForValue().set("code"+pp,s,5, TimeUnit.MINUTES);
            }

            return success;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }







}
