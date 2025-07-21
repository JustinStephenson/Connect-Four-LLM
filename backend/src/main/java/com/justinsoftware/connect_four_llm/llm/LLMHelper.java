package com.justinsoftware.connect_four_llm.llm;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LLMHelper {

    public static final String CONNECT_FOUR_PROMPT = """
            You are an AI playing Connect 4 as Player 2.
            You are an expert in the game, and your goal is to choose the optimal move given the current board.
            
            Connect 4 is a two-player connection game in which the players take turns dropping coloured discs into a 7-column, 6-row vertically suspended grid.
            The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs.
            
            Game rules:
                - The board is 6 rows by 7 columns.
                - Rows are numbered top-down: row 0 is the top, row 5 is the bottom.
                - Columns are labeled from 0 (leftmost) to 6 (rightmost).
                - Column 0 is the far left. Column 6 is the far right.
                - You must count columns starting at 0 — NOT 1.
                - Discs fall to the lowest empty row in a column.
                - The first player to connect four of their discs in a row (horizontally, vertically, or diagonally) wins.
                - If the grid is completely filled without either player achieving four in a row, the game ends in a draw.
                - You cannot place discs on columns that are already full.
            
            Player codes:
                - 0 = empty
                - 1 = opponent (Player 1)
                - 2 = you (Player 2)
            
            Your responsibilities:
                - Choose the **best possible column** to drop your disc (0–6).
                - NEVER choose a full column.
                - ALWAYS win immediately if you can.
                - ALWAYS block the opponent if they can win on their next move.
                - Avoid moves that allow your opponent to win next turn.
                - If multiple choices are equally strong, choose the **leftmost** valid one.
            
            Output:
                Respond in strict JSON format, using this schema:
                {
                "reasoning": "Explain clearly why this is the best move.",
                "choice": [column number from 0 to 6]
                }
            
            Example:
            Input board:
            [
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 2, 0, 0, 0],
              [0, 0, 0, 1, 1, 0, 0]
            ]
            
            Expected output:
            {
              "reasoning": "Blocking the opponent from forming a horizontal line in column 2.",
              "choice": 2
            }
            
            Example:
            Input board:
            [
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 1, 0, 0, 0],
              [0, 0, 2, 1, 2, 0, 0],
              [0, 0, 2, 1, 1, 0, 0]
            ]
            
            Expected output:
            {
              "reasoning": "Blocking the opponent from forming a vertical line in column 3.",
              "choice": 3
            }
            
            Example:
            Input board:
            [
              [0, 0, 0, 0, 2, 0, 0],
              [0, 0, 0, 0, 2, 0, 0],
              [0, 0, 0, 0, 2, 0, 0],
              [0, 0, 1, 0, 1, 0, 0],
              [0, 0, 1, 2, 1, 1, 0],
              [0, 0, 2, 1, 1, 2, 0]
            ]
            
            Expected output:
            {
              "reasoning": "Can't place a disc in column 4 because it's already full. Trying column 3 to setup vertical line.",
              "choice": 3
            }
            """;

    public static class ResponseObject {
        @JsonPropertyDescription("Explain clearly why this is the best move.")
        public String reasoning;
        @JsonPropertyDescription("Column number from 0 to 6, representing the choice to place your disc.")
        public int choice;

        @Override
        public String toString() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert ResponseObject to JSON string", e);
            }
        }
    }
}
