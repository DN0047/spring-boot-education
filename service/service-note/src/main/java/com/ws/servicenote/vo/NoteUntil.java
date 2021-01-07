package com.ws.servicenote.vo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class NoteUntil implements InitializingBean {
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.keysecret}")
    private String templateCode;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_TEMPPATECODE;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        ACCESS_TEMPPATECODE=templateCode;
    }




}
