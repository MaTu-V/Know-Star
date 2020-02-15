package com.wd.zykt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadProteriesUtil {

    @Value("${zykt.com.tokenSecret}")
    public String tokenSecret;
    @Value("${zykt.com.aesSecret}")
    public String aesSecret;

    // 公共资源访问（服务器允许访问目录）
    @Value("${zykt.com.fileSpace}")
    public String FILE_SPACE;

    @Value("${zykt.com.wxAppid}")
    public String wxAppid;
    @Value("${zykt.com.wxSecretKey}")
    public String wxSecretKey;

    @Value("${zykt.com.qqAppid}")
    public String qqAppid;
    @Value("${zykt.com.qqSecretKey}")
    public String qqSecretKey;

}
