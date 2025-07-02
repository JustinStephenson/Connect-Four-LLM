export const createMatrix = (rows: number, cols: number) => {
  let num = 1;
  const matrix: number[][] = [];
  for (let i = 0; i < rows; i++) {
    const row: number[] = [];
    for (let j = 0; j < cols; j++) {
      row.push(num++);
    }
    matrix.push(row);
  }
  return matrix;
};
