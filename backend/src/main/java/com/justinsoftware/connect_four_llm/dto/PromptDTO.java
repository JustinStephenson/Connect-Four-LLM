package com.justinsoftware.connect_four_llm.dto;

import java.util.List;

public record PromptDTO(String message, List<List<Integer>> boardState) {
}
