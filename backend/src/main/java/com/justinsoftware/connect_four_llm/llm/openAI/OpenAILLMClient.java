package com.justinsoftware.connect_four_llm.llm.openAI;

import com.justinsoftware.connect_four_llm.llm.LLMClient;
import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "openAIClient")
public class OpenAILLMClient implements LLMClient {

    private final OpenAIClient client;

    private Response response;

    public OpenAILLMClient(@Qualifier("openAI") OpenAIClient client) {
        this.client = client;
    }

    @Override
    public void buildResponse(String prompt) {
        ResponseCreateParams params = ResponseCreateParams.builder()
                .model(ChatModel.GPT_4O_MINI)
                .input(prompt)
                .build();

        response = client.responses().create(params);
    }

    @Override
    public String getResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .forEach(outputText -> stringBuilder.append(outputText.text()));

        return stringBuilder.toString();
    }
}
