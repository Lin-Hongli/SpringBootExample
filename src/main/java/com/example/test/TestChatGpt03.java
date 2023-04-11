package com.example.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TestChatGpt03 {
    public static void main(String[] args) {

       /* //设置代理
        String proxy = "openproxy.huawei.com";
        int port = 8080;
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(port));
        System.setProperty("proxyHost", proxy);
        System.setProperty("proxySet", "true");

        try {
            SSLContext sc = SSLContext.getInstance("SSL");

            //指定信任https
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            // 运行错误，好像只能用https
            // URL console = new URL(url);
            // 可用用http
            URL console= new URL(null, url, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            System.out.println("返回结果：" + conn.getResponseMessage());

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String curLine = "";
            while ((curLine = reader.readLine()) != null) {
                System.out.println(curLine);
            }

            is.close();
            return conn.getResponseMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }
*/



        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer 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");
        httpHeaders.add("Content-Type", "application/json"); // 传递请求体时必须设置
//        String requestJson = "{\n" +
//                "    \"model\": \"text-davinci-003\",\n" +
//                "     \"prompt\": \"你好\",\n" +
//                "      \"temperature\": 0, \n" +
//                "      \"max_tokens\": 2048\n" +
//                "}";
        String requestJson = String.format(
                "{\n" +
                        "    \"model\": \"text-davinci-003\",\n" +
                        "     \"prompt\": \"%s\",\n" +
                        "      \"temperature\": 0, \n" +
                        "      \"max_tokens\": 2048\n" +
                        "}", "{ " +
                        "\"username\": \"lin\"," +
                        "\"data\": \"hi\"" +
                        " }"
        );
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.openai.com/v1/completions", HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        JSONArray choices = jsonObject.getJSONArray("choices");
        String text = choices.getJSONObject(0).getString("text");
        System.out.println(text);
    }
}

