package com.mivebe;

public class Sudoku {
    private static final int GRID_SIZE = 9;
    private static final int EMPTY_SPACES = 50;

    public static void solve() {
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3},
        };

        board = generateBoard(EMPTY_SPACES, GRID_SIZE);

        printBoard(board);
        System.out.println();

        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
            System.out.println();
            System.out.println();
        }
        else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);

    }

    private static boolean isNumberInRow (int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++){
           if(board[row][i] == number){
               return true;
           }
        }
        return false;
    }

    private static boolean isNumberInColumn (int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++){
           if(board[i][column] == number){
               return true;
           }
        }
        return false;
    }

    private static boolean isNumberInBox (int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement (int [][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard (int [][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column ++){
                if (board[row][column] == 0){
                    for (int numberAttempt = 1; numberAttempt <= GRID_SIZE; numberAttempt++){
                        if (isValidPlacement(board, numberAttempt, row, column)){
                            board[row][column] = numberAttempt;

                            if (solveBoard(board)) {
                                return true;
                            }else {
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

    private static void printBoard (int [][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    public static int[][] generateBoard (int K , int N) {
        int[][] mat = new int[N][N];
        Double SRNd = Math.sqrt(N);
        int SRN = SRNd.intValue();

        for (int i = 0; i < N; i = i + SRN) {
            fillBox(mat, i, i, SRN, N);
        }

        fillRemaining(mat,0,0, N, SRN);

        removeKDigits(mat, K, N);
        return mat;
    }

    private static void fillBox(int[][] mat, int row, int col, int SRN, int N) {
        int randomNum;
        for (int i = 0; i < SRN; i++) {
            for (int j=0; j < SRN; j++) {
                do {
                    randomNum = RNG(N);
                }
                while (!unUsedInBox(mat, row, col, randomNum, SRN));

                mat[row+i][col+j] = randomNum;
            }
        }
    }

    private static int RNG(int num) {
        return (int) Math.floor((Math.random()*num+1));
    }

    private static boolean checkIfSafe(int[][] mat, int i, int j, int num, int N, int SRN) {
        return (unUsedInRow(mat, i, num, N) &&
                unUsedInCol(mat, j, num, N) &&
                unUsedInBox(mat, i - i % SRN, j - j % SRN, num, SRN));
    }

    private static boolean unUsedInRow(int[][] mat, int i, int num, int N) {
        for (int j = 0; j < N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    private static boolean unUsedInCol(int[][] mat, int j, int num, int N) {
        for (int i = 0; i < N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    private static boolean unUsedInBox(int[][] mat, int rowStart, int colStart, int num, int SRN) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (mat[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    private static boolean fillRemaining(int[][] mat, int i, int j, int N, int SRN) {
        //  System.out.println(i+" "+j);
        if (j >= N && i < N - 1)
        {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N - SRN)
        {
            if (j == (i / SRN) * SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++)
        {
            if (checkIfSafe(mat, i, j, num, N, SRN))
            {
                mat[i][j] = num;
                if (fillRemaining(mat, i, j+1, N, SRN))
                    return true;

                mat[i][j] = 0;
            }
        }
        return false;
    }

    public static void removeKDigits(int[][] mat, int K, int N) {
        int count = K;
        while (count != 0)
        {
            int cellId = RNG(N * N) - 1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/N);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
    }
}