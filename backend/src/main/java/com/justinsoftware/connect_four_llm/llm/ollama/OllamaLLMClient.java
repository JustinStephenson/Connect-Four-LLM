package com.justinsoftware.connect_four_llm.llm.ollama;

import com.justinsoftware.connect_four_llm.llm.LLMClient;
import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "ollamaClient")
public class OllamaLLMClient implements LLMClient {

    private final OpenAIClient client;

    @Value("${llm.model}")
    private String model;

    private ChatCompletion chatCompletion;

    public OllamaLLMClient(@Qualifier("ollama") OpenAIClient client) {
        this.client = client;
    }

    @Override
    public void buildResponse(String prompt) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model(model)
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
