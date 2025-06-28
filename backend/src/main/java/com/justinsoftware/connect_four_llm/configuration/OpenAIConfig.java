package com.justinsoftware.connect_four_llm.configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    @Qualifier("openAI")
    public OpenAIClient openAIClientConfig() {
        String apiKey = Dotenv.load().get("OPENAI_API_KEY");
        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    @Bean
    @Qualifier("ollama")
    public OpenAIClient ollamaClientConfig() {
        return OpenAIOkHttpClient.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("ollama")
                .build();
    }

}
