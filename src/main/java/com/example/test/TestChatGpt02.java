package com.example.test;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;


public class TestChatGpt02 {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/text-davinci-003/completions";

    public static void main(String[] args) throws IOException {

        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
