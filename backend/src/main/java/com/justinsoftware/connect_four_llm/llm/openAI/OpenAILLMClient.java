package com.justinsoftware.connect_four_llm.llm.openAI;

import com.justinsoftware.connect_four_llm.llm.LLMClient;
import com.justinsoftware.connect_four_llm.llm.LLMHelper;
import com.openai.client.OpenAIClient;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.StructuredResponse;
import com.openai.models.responses.StructuredResponseCreateParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.justinsoftware.connect_four_llm.llm.LLMHelper.ResponseObject;

@Component(value = "openAIClient")
public class OpenAILLMClient implements LLMClient {

    private final OpenAIClient client;

    @Value("${llm.model}")
    private String model;

    private StructuredResponse<ResponseObject> response;

    public OpenAILLMClient(@Qualifier("openAI") OpenAIClient client) {
        this.client = client;
    }

    @Override
    public void buildResponse(String prompt) {
        String fullPrompt = LLMHelper.CONNECT_FOUR_PROMPT + "\n\n" + prompt;

        StructuredResponseCreateParams<ResponseObject> params = ResponseCreateParams.builder()
                .model(model)
                .text(ResponseObject.class)
                .input(fullPrompt)
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
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
