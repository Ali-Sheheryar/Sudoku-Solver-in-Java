package SudokuSolver;

public class SudokuSolver {

	private static final int GRID_BOARD = 9;

	public static void main(String[] args) {

		int[][] board = { // input numbers in the folllowing board
				
				{ 7, 0, 2,   0, 5, 0,   6, 0, 0 }, 
				{ 0, 0, 0,   0, 0, 3,   0, 0, 0 }, 
				{ 1, 0, 0,   0, 0, 9,   5, 0, 0 },
				
				{ 8, 0, 0,   0, 0, 0,   0, 9, 0 }, 
				{ 0, 4, 3,   0, 0, 0,   7, 5, 0 }, 
				{ 0, 9, 0,   0, 0, 0,   0, 0, 8 },
				
				{ 0, 0, 9,   7, 0, 0,   0, 0, 5 }, 
				{ 0, 0, 0,   2, 0, 0,   0, 0, 0 }, 
				{ 0, 0, 7,   0, 4, 0,   2, 0, 3 } 
				
		};
		
//int[][] board = { // Solved Sudoku Board
//				
//				{ 1, 2, 3,   4, 5, 6,   7, 8, 9 }, 
//				{ 7, 8, 9,   1, 2, 3,   4, 5, 6 }, 
//				{ 4, 5, 6,   7, 8, 9,   1, 2, 3 },
//				
//				{ 3, 1, 2,   6, 4, 5,   9, 7, 8 }, 
//				{ 8, 9, 7,   2, 3, 1,   5, 6, 4 }, 
//				{ 6, 4, 5,   9, 7, 8,   2, 3, 1 },
//				
//				{ 2, 3, 1,   5, 6, 4,   8, 9, 7 }, 
//				{ 9, 7, 8,   3, 1, 2,   6, 4, 5 }, 
//				{ 5, 6, 4,   8, 9, 7,   3, 1, 2 } 
//				
//		};

		printBoard(board);

		if (solveBoard(board)) {
			System.out.println("\nSolved Successfully \n");
			printBoard(board);
		} else {
			System.out.println("\nBetter luck next time! \n");
		}
	}

	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_BOARD; row++) {
			for (int column = 0; column < GRID_BOARD; column++) {
				if (board[row][column] == 0) {
					for (int tryNum = 1; tryNum <= GRID_BOARD; tryNum++) {
						if (isValidPlace(board, row, column, tryNum)) {
							board[row][column] = tryNum;
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isNoInRow(int[][] board, int row, int num) {
		for (int i = 0; i < GRID_BOARD; i++) {
			if (board[row][i] == num)
				return true;
		}
		return false;
	}

	private static boolean isNoInColumn(int[][] board, int column, int num) {
		for (int i = 0; i < GRID_BOARD; i++) {
			if (board[i][column] == num)
				return true;
		}
		return false;
	}

	private static boolean isNoInBox(int[][] board, int row, int column, int num) {
		int localRow = row - row % 3;
		int localColumn = column - column % 3;

		for (int i = localRow; i < localRow + 3; i++) {
			for (int j = localColumn; j < localColumn + 3; j++) {
				if (board[i][j] == num)
					return true;
			}
		}
		return false;
	}

	private static boolean isValidPlace(int[][] board, int row, int column, int num) {
		return !isNoInBox(board, row, column, num) && !isNoInRow(board, row, num) && !isNoInColumn(board, column, num);
	}

	private static void printBoard(int[][] board) {
		for (int row = 0; row < GRID_BOARD; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			for (int column = 0; column < GRID_BOARD; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}
}
