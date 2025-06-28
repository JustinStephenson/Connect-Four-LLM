package com.justinsoftware.connect_four_llm.controller;

import com.justinsoftware.connect_four_llm.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/connect-four/")
public class LLMController {

    private final LLMService llmService;

    @GetMapping("/")
    public String getLLMResponse() {
        String prompt = "Hello, this is a test prompt";
        llmService.buildResponse(prompt);
        return llmService.getResponse();
    }
}
