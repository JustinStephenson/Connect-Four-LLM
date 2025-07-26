export type TokenType = 0 | 1 | 2;
export type PlayerPos = { row: number; col: number };
export type LLMResponse = {
  reasoning: string;
  choice: number;
} | null;
