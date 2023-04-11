package com.example.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class TestChatGpt {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/text-davinci-003/completions";
    //private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "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";
    private static final String PROXY_IP = "127.0.0.1";
    private static final String  PROXY_port = "10809";

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(PROXY_IP,Integer.parseInt(PROXY_port));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理

        OkHttpClient client = new OkHttpClient().newBuilder()
                .proxy(proxy)  //配置本地系统外网的代理
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        //model="text-davinci-003",
        Scanner sc=new Scanner(System.in);
        while (true){
            String requestBody = "{" +
                    "\"prompt\":\"" + sc.next() + "\"," +
                    "\"temperature\":0," +
                    "\"max_tokens\":3000," +
                    "\"top_p\":1," +
                    "\"frequency_penalty\":0," +
                    "\"presence_penalty\":0" +
                    //"\"stop\": [\"\"] " +
                    "}";
            //System.out.println(requestBody);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            Request request = new Request.Builder()
                    .url(API_ENDPOINT)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(mediaType, requestBody))
                    .build();

            Call call = client.newCall(request);
            call.timeout().timeout(180, TimeUnit.SECONDS);
            Response response = call.execute();

            String responseBody = response.body().string();
            //System.out.println(responseBody);

            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            JSONArray choices = jsonObject.getJSONArray("choices");
            String text = choices.getJSONObject(0).getString("text");
            /*while(text.startsWith("\n")){
                text = text.replaceFirst("\n","");
            }*/
            System.out.println(text+"\n");
        }
    }
}
