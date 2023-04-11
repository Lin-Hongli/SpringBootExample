package com.example.test;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.www.protocol.http.Handler;

import java.io.*;
import java.net.*;

@SpringBootTest
class TestApplicationTests {
    private Handler handler = new Handler();

    @Test
    void contextLoads(){
        //String ip = AppEnv.getInstance().get(AppEnv.DLDZ);
        //String port = AppEnv.getInstance().get(AppEnv.DLDK);

        String ip = "127.0.0.1";
        String port = "10809";
        String URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
        InetSocketAddress address = new InetSocketAddress(ip,Integer.parseInt(port));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection(proxy);
            con.setRequestProperty("Authorization","Bearer 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");
            con.setRequestProperty("Content-Type","application/json"); // 传递请求体时必须设置
            //con.setRequestProperty("charset", "utf-8");
            con.setRequestMethod("POST");

   /*         con.setDoOutput(true);
            OutputStream outputStream = con.getOutputStream();
            String params = String.format(
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
            outputStream.write(params.getBytes());*/
            con.setDoOutput(true);
            OutputStream outputStream = con.getOutputStream();
            String params = String.format(
                    "{\n" +
                            "    \"prompt\": \"写一段Java代码\",\n" +
                            "    \"max_tokens\": 2048,\n" +
                            "    \"temperature\": 0.9\n" +
                            "}"
            );
            outputStream.write(params.getBytes());



            //获取内容
            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            System.out.println(stringBuffer.toString());



            String responseMessage = con.getResponseMessage();
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            System.out.println(responseMessage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //throw new HttpException(-1, "传入的URL不合法,URL:" + "URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
