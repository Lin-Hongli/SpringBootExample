package com.example.test;

import lombok.extern.slf4j.Slf4j;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.URL;

/**
 * @author 许培宇
 * @date 2022/6/1 15:31
 */
@Slf4j
public class TestHttpProxy {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.useSystemProxies","true");
        String property = System.getProperty("java.net.useSystemProxies");
        log.info("java.net.useSystemProxies:{}", property);
        HttpURLConnection googleConnection = null;
        Object content = null;
        try {
            URL url = new URL("http://www.google.com");
            googleConnection = (HttpURLConnection) url.openConnection();
            googleConnection.setConnectTimeout(3000);
            googleConnection.connect();
            content = googleConnection.getContent();
            log.info("withOutProxy:{}", content);
        } catch (IOException e) {
            log.info("withOutProxy occur error");
        }

        try {
            googleConnection = new HttpURLConnection(new URL("https://api.openai.com"), "127.0.0.1", 10809);
            //googleConnection = new HttpURLConnection(new URL("http://www.google.com"), "127.0.0.1", 10809);
            googleConnection.setConnectTimeout(3000);
            googleConnection.connect();
            content = googleConnection.getContent();
            log.info("customProxy:{}", content);
        } catch (IOException e) {
            log.info("customProxy occur error");
        }


        try {

            googleConnection = new HttpURLConnection(new URL("http://www.google.com"), null);
            googleConnection.setConnectTimeout(3000);
            googleConnection.connect();
            content = googleConnection.getContent();
            log.info("systemProxy:{}", content);
        } catch (IOException e) {
            log.info("systemProxy occur error");
        }

        // 输出结果
        // 10:34:36.114 [main] INFO cn.ikarosx.Demo - java.net.useSystemProxies:false
        // 10:34:39.169 [main] INFO cn.ikarosx.Demo - withOutProxy occur error
        // 10:34:39.265 [main] INFO cn.ikarosx.Demo - customProxy:sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@5479e3f
        // 10:34:42.279 [main] INFO cn.ikarosx.Demo - systemProxy occur error

        // java.net.useSystemProxies可以指定使用系统代理参数
        // 需要添加虚拟机启动参数-Djava.net.useSystemProxies=true，不能在代码设置
        // On recent Windows systems and on Gnome 2.x systems it is possible to tell the java.net stack, setting this property to true, to use the system proxy settings (both these systems let you set proxies globally through their user interface). Note that this property is checked only once at startup.


        // 系统代理输出结果
        // 10:55:49.416 [main] INFO cn.ikarosx.Demo - java.net.useSystemProxies:true
        // 10:55:49.542 [main] INFO cn.ikarosx.Demo - withOutProxy:sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@5479e3f
        // 10:55:49.653 [main] INFO cn.ikarosx.Demo - customProxy:sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@27082746
        // 10:55:49.773 [main] INFO cn.ikarosx.Demo - systemProxy:sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@66133adc


/*
        System.setProperty("java.net.useSystemProxies","true");
        String property = System.getProperty("java.net.useSystemProxies");
        log.info("java.net.useSystemProxies:{}", property);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://www.google.com", String.class);
        System.out.println(forEntity.getStatusCode());*/

    }
}


