package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.*;

public class Board {
    File tttFile;
    String[][] board = {{"", "", ""},
                        {"", "", ""},
                        {"", "", ""}};
    HashMap<Integer[], Integer> utility = new HashMap<>();
    

    public Board(File tttFile) {
        this.tttFile = tttFile;
    }

    // populate String[][] board using tttFile
    public void createBoard() throws IOException {

        // read TTT board from file 
        FileReader fr = new FileReader(tttFile);
        BufferedReader br = new BufferedReader(fr);

        // input tttFile into board 
        String temp = "";
        int i = 0;
        while ((temp = br.readLine()) != null) {
			board[i][0] = temp.substring(0, 1);
            board[i][1] = temp.substring(1, 2); 
            board[i][2] = temp.substring(2, 3);

            i++;
		}

    }

    // return board
    public void returnBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n------------");

    }

    // check valid turn (returns false if more Xs than Os)
    public boolean checkValidTurn() {
        int Xcount = 0; 
        int Ocount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j].equals("X")) {
                    Xcount++; 
                } else if (board[i][j].equals("O")) {
                    Ocount++;
                }

            }
        }

        if (Xcount > Ocount) {
            return false; 
        } else {
            return true;
        }
    
    }

    // return empty positions on board
    public void returnEmptyPos() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(".")) {
                    
                    // put y (=i), x (=j) into map 
                    Integer[] intoMap = {i, j};
                    utility.put(intoMap, 0);
                }

            }
        }

    }

    // get utility 
    public void getUtility() { 
        
        // for every empty pos
        for (Integer[] pos : utility.keySet()) {

            // create temp board (copy of board)
            String[][] tempBoard = {{"", "", ""},
                                    {"", "", ""},
                                    {"", "", ""}};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tempBoard[i][j] = board[i][j];
                    
                }
                
            }

            // place "X" at pos
            placePiece(tempBoard, "X", pos);

            // evaluate board, with new "X" at pos
            utility.put(pos, evaluateBoard(tempBoard, pos));

        }

    }

    // evaluate board 
    public int evaluateBoard(String[][] board, Integer[] position) {

        int[] results = {checkRows(board), checkColumns(board), checkDiag1(board), checkDiag2(board)};
        int result = 0;
        for (int r : results) { 
            if (r == 1) {
                result = 1; 
                break;
            } else if (r == -1) {
                result = -1;
            }
        }

        return result; 

    }

    // check rows 
    public int checkRows(String[][] board) {

        int result = 0;

        for (int i = 0; i < 3; i++) {
            int Xcounter = 0; 
            int Ocounter = 0; 
            int dotCounter = 0; 

            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("X")){
                    Xcounter++; 
                } else if (board[i][j].equals("O")) {
                    Ocounter++; 
                } else if (board[i][j].equals(".")) {
                    dotCounter++;
                }
            }

            if (Xcounter == 3) {
                result = 1; 
                return result;
            } else if (Ocounter == 2 && dotCounter == 1) {
                result = -1; 
            } 


        }

        return result;

    }

    // check columns 
    public int checkColumns(String[][] board) {

        int result = 0;

        for (int i = 0; i < 3; i++) {
            int Xcounter = 0; 
            int Ocounter = 0; 
            int dotCounter = 0; 

            for (int j = 0; j < 3; j++) {
                if (board[j][i].equals("X")){
                    Xcounter++; 
                } else if (board[j][i].equals("O")) {
                    Ocounter++; 
                } else if (board[j][i].equals(".")) {
                    dotCounter++;
                }
            }

            if (Xcounter == 3) {
                result = 1; 
                return result;
            } else if (Ocounter == 2 && dotCounter == 1) {
                result = -1; 
            } 

        }

        return result;

    }

    // check diag1
    public int checkDiag1(String[][] board) {

        int result = 0;

        String diag1A = board[0][0];
        String diag1B = board[1][1];
        String diag1C = board[2][2];
        String[] diag1 = {diag1A, diag1B, diag1C};

        int Xcounter = 0; 
        int Ocounter = 0; 
        int dotCounter = 0; 

        for (String s : diag1) {

            if (s.equals("X")){
                Xcounter++; 
            } else if (s.equals("O")) {
                Ocounter++; 
            } else if (s.equals(".")) {
                dotCounter++;
            }

        }

        if (Xcounter == 3) {
            result = 1; 
            return result;
        } else if (Ocounter == 2 && dotCounter == 1) {
            result = -1; 
        } 

        return result;

    }

    // check diag2 
    public int checkDiag2(String[][] board) {

        int result = 0;

        String diag2A = board[0][2]; 
        String diag2B = board[1][1];
        String diag2C = board[2][0];
        String[] diag2 = {diag2A, diag2B, diag2C};

        int Xcounter = 0; 
        int Ocounter = 0; 
        int dotCounter = 0; 

        for (String s : diag2) {

            if (s.equals("X")){
                Xcounter++; 
            } else if (s.equals("O")) {
                Ocounter++; 
            } else if (s.equals(".")) {
                dotCounter++;
            }

        }

        if (Xcounter == 3) {
            result = 1; 
            return result;
        } else if (Ocounter == 2 && dotCounter == 1) {
            result = -1; 
        } 

        return result;

    }

    // place piece on board 
    public void placePiece(String[][] board, String marker, Integer[] position) {

        int y = position[0];
        int x = position[1];

        board[y][x] = marker;

    }

    // print empty positions + utility 
    public void returnUtility() { 
        for (Integer[] xy : utility.keySet()) {
            System.out.printf("y=%d, x=%d, utility=%d\n", xy[0], xy[1], utility.get(xy));
        }
    }

}
