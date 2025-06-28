package com.justinsoftware.connect_four_llm.service;

import com.justinsoftware.connect_four_llm.llm.LLMClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LLMService {

    private final LLMClient client;

    public LLMService(@Value("${llm.client}") String clientName, Map<String, LLMClient> clients) {
        this.client = clients.get(clientName);
        if (client == null) {
            throw new IllegalArgumentException("No LLMClient found for: " + clientName);
        }
    }

    public void buildResponse(String prompt) {
        client.buildResponse(prompt);
    }

    public String getResponse() {
        return client.getResponse();
    }
}
