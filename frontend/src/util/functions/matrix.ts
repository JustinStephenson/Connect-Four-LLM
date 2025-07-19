import type { Types } from "../../types/types.ts";

export const createMatrix = (rows: number, cols: number): Types[][] => {
  const matrix: Types[][] = [];
  for (let i = 0; i < rows; i++) {
    const row: Types[] = [];
    for (let j = 0; j < cols; j++) {
      row.push(1);
    }
    matrix.push(row);
  }
  return matrix;
};
