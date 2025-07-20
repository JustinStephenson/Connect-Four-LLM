package com.justinsoftware.connect_four_llm.controller;

import com.justinsoftware.connect_four_llm.dto.PromptDTO;
import com.justinsoftware.connect_four_llm.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/connect-four/")
public class LLMController {

    private final LLMService llmService;

    @GetMapping("/")
    public String getLLMResponse() {
        String prompt = "Can you respond with 'GET, I am the best LLM'";
        llmService.buildResponse(prompt);
        return llmService.getResponse();
    }

    @PostMapping("/prompt")
    public String getLLMResponse(@RequestBody PromptDTO request) {
        llmService.buildResponse(request.prompt());
        return llmService.getResponse();
    }
}
