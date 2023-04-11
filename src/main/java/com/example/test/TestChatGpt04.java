package com.example.test;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import retrofit2.HttpException;

import java.net.*;

public class TestChatGpt04 {
    public static void main(String[] args) throws Exception {


        String token = System.getenv("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");

        OpenAiService service = new OpenAiService("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");
        System.out.println("Creating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                //.model("text-davinci-003")
                .prompt("你好")
                .echo(true)
                .temperature(0.9)
                .maxTokens(2048)
                .frequencyPenalty(0.8)
                .presencePenalty(0.8)
                .user("LinHL")
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
