package com.justinsoftware.connect_four_llm.llm;

public interface LLMClient {
    /**
     * Builds and returns a LLM response based on the provided input prompt.
     *
     * @param prompt The input text which a response is to be generated.
     */
    void buildResponse(String prompt);

    /**
     * Retrieves the generated response from the client LLM.
     *
     * @return The last generated response as a string.
     */
    String getResponse();
}
