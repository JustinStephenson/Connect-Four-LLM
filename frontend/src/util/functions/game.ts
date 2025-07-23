import type { TokenType } from "../../types/types.ts";

/**
 * An array of direction objects representing matrix traversal directions.
 * Each direction contains the change in row (`rowStep`) and column (`colStep`) indices.
 *
 * Directions:
 * - { rowStep: 0, colStep: 1 }: Horizontal movement (right).
 * - { rowStep: 1, colStep: 0 }: Vertical movement (down).
 * - { rowStep: 1, colStep: 1 }: Diagonal movement (down-right).
 * - { rowStep: -1, colStep: 1 }: Diagonal movement (up-right).
 *
 * Each object represents a unit step in the respective direction.
 */
const directions: { rowStep: number; colStep: number }[] = [
  { rowStep: 0, colStep: 1 },
  { rowStep: 1, colStep: 0 },
  { rowStep: 1, colStep: 1 },
  { rowStep: -1, colStep: 1 },
];

/**
 * Checks if a player has won.
 *
 * @param {TokenType[][]} matrix - The game board represented as a 2D array of tokens.
 * @param {TokenType} playerToken - The token representing the player.
 * @param {number} startRow - The row index of the player.
 * @param {number} startCol - The column index of the player.
 * @returns {boolean} Returns true if the player has a winning sequence in any direction, otherwise false.
 */
export const checkWin = (
  matrix: TokenType[][],
  playerToken: TokenType,
  startRow: number,
  startCol: number,
): boolean => {
  const totalRows: number = matrix.length;
  const totalCols: number = matrix[0].length;

  for (const { rowStep, colStep } of directions) {
    let count: number = 1; // start with a placed token

    let currentRow: number = 0;
    let currentCol: number = 0;

    // Check forward
    currentRow = startRow + rowStep;
    currentCol = startCol + colStep;
    while (
      checkRowBounds(currentRow, totalRows) &&
      checkColBounds(currentCol, totalCols) &&
      matrix[currentRow][currentCol] === playerToken
    ) {
      count++;
      currentRow += rowStep;
      currentCol += colStep;
    }

    // Check backward
    currentRow = startRow - rowStep;
    currentCol = startCol - colStep;
    while (
      checkRowBounds(currentRow, totalRows) &&
      checkColBounds(currentCol, totalCols) &&
      matrix[currentRow][currentCol] === playerToken
    ) {
      count++;
      currentRow -= rowStep;
      currentCol -= colStep;
    }

    if (count >= 4) {
      return true;
    }
  }

  return false;
};

const checkRowBounds = (currentRow: number, totalRows: number): boolean => {
  return currentRow >= 0 && currentRow < totalRows;
};

const checkColBounds = (currentCol: number, totalCols: number): boolean => {
  return currentCol >= 0 && currentCol < totalCols;
};

/**
 * Places a token on the game board in the specified column.
 * The token is placed in the lowest possible empty row within the column.
 *
 * @param {TokenType[][]} board - A 2D array representing the game board. Each element represents a token or an empty space.
 * @param {TokenType} token - The token to place on the board.
 * @param {number} columnIndex - The index of the column where the token should be placed.
 * @param {Function} [onPlaced] - An optional callback function invoked after the token is successfully placed.
 */
export const placeToken = (
  board: TokenType[][],
  token: TokenType,
  columnIndex: number,
  onPlaced?: (row: number, col: number) => void,
): void => {
  for (let row: number = board.length - 1; row >= 0; row--) {
    if (board[row][columnIndex] === 0) {
      board[row][columnIndex] = token;
      if (onPlaced) {
        onPlaced(row, columnIndex);
      }
      break;
    }
  }
};
