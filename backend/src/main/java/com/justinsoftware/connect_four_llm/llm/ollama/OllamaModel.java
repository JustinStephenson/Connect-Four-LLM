package com.justinsoftware.connect_four_llm.llm.ollama;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OllamaModel {
    DeepSeek_r1("deepseek-r1"),
    Gemma("gemma"),
    Mistral("mistral"),
    LLama3_2("llama3.2");

    private final String modelName;
}
