import type { TokenType } from "../../types/types.ts";

export const createMatrix = (rows: number, cols: number): TokenType[][] => {
  const matrix: TokenType[][] = [];
  for (let i = 0; i < rows; i++) {
    const row: TokenType[] = [];
    for (let j = 0; j < cols; j++) {
      row.push(0);
    }
    matrix.push(row);
  }
  return matrix;
};
