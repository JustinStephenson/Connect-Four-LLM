package com.justinsoftware.connect_four_llm.llm.ollama;

import com.justinsoftware.connect_four_llm.llm.LLMClient;
import com.justinsoftware.connect_four_llm.llm.LLMHelper;
import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.StructuredChatCompletion;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.justinsoftware.connect_four_llm.llm.LLMHelper.ResponseObject;

@Component(value = "ollamaClient")
public class OllamaLLMClient implements LLMClient {

    private final OpenAIClient client;

    @Value("${llm.model}")
    private String model;

    private StructuredChatCompletion<ResponseObject> chatCompletion;

    public OllamaLLMClient(@Qualifier("ollama") OpenAIClient client) {
        this.client = client;
    }

    @Override
    public void buildResponse(String prompt) {
        StructuredChatCompletionCreateParams<ResponseObject> params = ChatCompletionCreateParams.builder()
                .model(model)
                .addSystemMessage(LLMHelper.CONNECT_FOUR_PROMPT)
                .responseFormat(ResponseObject.class)
                .addUserMessage(prompt)
                .build();

        chatCompletion = client.chat().completions().create(params);
    }

    @Override
    public String getResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        chatCompletion.choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
