// Name: Viktoriia Sahaidak
// Class: CS143 Java 2
// Assignment: SudokuBoard #3
//HW Core topics: recursion, backtracking
// This program will read data from text file, load it into the class, 
// and solve the puzzle

import java.io.*;
import java.util.*;

public class SudokuBoard {
   private char[][] board;
   
   //pre: readable text file with 9 lines and 9 char each
   //post: fills the 9x9 char array with digits or '.' from the file
   public SudokuBoard(String fileName) throws FileNotFoundException {
      board = new char[9][9]; 
      
      Scanner input = new Scanner(new File(fileName));
      
      for (int row = 0; row < 9; row++) {
         String line = input.nextLine().trim();
         
         for (int col = 0; col < 9; col++) {
            board[row][col] = line.charAt(col); 
         }
      }
      
      input.close();
   } 
   
   //pre: none 
   //post: returns a formatted string showing the current board
      public String toString() {
      String result = "";
      for (int row = 0; row < 9; row++) {
         if (row % 3 == 0) {
            result += "+-------+-------+-------+\n";
         }
         for (int col = 0; col < 9; col++) {
            if (col % 3 == 0) {
               result += "| ";
            } 
            result += board[row][col] + " ";
         }
         result += "|\n";
      }
         
      result += "+-------+-------+-------+\n";
      return result;
   }
   
   //pre:  isValid passed
   //post: returns true if passes the requirements 
   public boolean isSolved() {
     if (!isValid()) return false;
     int[] count = new int[10];
     for (int r = 0; r < 9; r++) {
         for (int c = 0; c < 9; c++) {
            char ch = board[r][c];
            if (ch == '.') return false;
            int d = ch - '0';   
            count[d]++;
         } 
     }
     for (int d = 1; d <= 9; d++) {
        if (count[d] != 9) return false;
     }
      return true;
   }
   
   //pre:  4 other methods exist and return something
   //post: passes the validation if each method returns true
      public boolean isValid() {
      return dataValid() && rowValid() && colValid() && boxValid();
  }
  
  //pre:  board[r][c] exists and has data in it
  //post: returns true if data is 1-9 or '.'
     private boolean dataValid() {
      Set<Character> allowed = new HashSet<>(Arrays.asList(
      '.','1','2','3','4','5','6','7','8','9'));
      
      for (int r = 0; r < 9; r++) {
         for (int c = 0; c < 9; c++) {
            if (!allowed.contains(board[r][c])) return false;
         }
      }
      return true;
   } 
   
  //pre:  board[r][c] exists and has data in it
  //post: returns true if row has no duplicates
   private boolean rowValid() {
      for (int r = 0; r < 9; r++) {
         if (!checkUnits(r, 0, 0, 1)) return false;
      }
      return true; 
   } 
   
   //pre:  board[r][c] exists and has data in it
   //post: returns true if colom has no duplicates
   private boolean colValid() {
      for (int c = 0; c < 9; c++) {
         if (!checkUnits(0, c, 1, 0)) return false;
      }
      return true;
   } 
   
   //pre:  board[r][c] exists and has data in it
   //returns true if mini square has no duplicates
   private boolean boxValid() {
      for (int sr = 0; sr < 9; sr += 3) {
         for (int sc = 0; sc < 9; sc += 3) {
            boolean[] seen = new boolean[10];
            for (int r = 0; r < 3; r++) {
               for (int c = 0; c < 3; c++) {
                  char ch = board[r+sr][c+sc];
                  if (ch=='.') continue;
                  int d = ch - '0';
                  if (seen[d]) return false;
                  seen[d] = true;
               }
            }
         }
      }
      
      return true;
   }
   
   //helpers
   //pre:  board[r][c] exists and has data in it
   //post: returns true if doesn't have duplicates
      public boolean checkUnits(int r0, int c0, int dr, int dc) {
      boolean[] seen = new boolean[10];            
      int r = r0;
      int c = c0;
      for (int i = 0; i < 9; i++) {
         char ch = board[r][c];
         if (ch != '.') {
            int d = ch - '0';
            if (d < 1 || d > 9 || seen[d]) {
               return false;
            }
            seen[d] = true;
         }
         r += dr;
         c += dc;
      }
      return true;
  }
  
  //pre:  board is loaded
  //post: returns true if solution was found
   public boolean solve() {
      if (!isValid()) { 
         return false;
      } 
      if (isSolved()) {
         return true;
      }
      return solveHelper();
   }
   
   //pre:  board is valid
   //post: fills empty cells by backtracking; 
   //      returns true if solved, false if no solution
   public boolean solveHelper() {
      int row = -1;
      int col = -1;
      boolean found = false;
      for (int r = 0; r < 9 && !found; r++) {
         for (int c = 0; c < 9 && !found; c++) {
            if (board[r][c] == '.') {
               row = r;
               col = c;
               found = true;
            }
         }
      }
      if (!found) {
         return isSolved();
      }
      for (char ch = '1'; ch <= '9'; ch++) {
         board[row][col] = ch;
         if (isValid() && solveHelper()) {
            return true;
         }
         board[row][col] = '.';
      }
      return false;
   }
}

/*
 ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
 +-------+-------+-------+
 | . 3 4 | 6 7 8 | 9 1 2 |
 | . 7 2 | 1 9 5 | 3 4 8 |
 | 1 9 8 | 3 4 2 | 5 6 7 |
 +-------+-------+-------+
 | . . 9 | . 6 1 | 4 2 3 |
 | . 2 6 | 8 5 3 | 7 9 1 |
 | . 1 3 | 9 2 4 | . 5 6 |
 +-------+-------+-------+
 | . 6 1 | 5 3 7 | 2 8 4 |
 | . 8 . | 4 1 9 | 6 3 5 |
 | 3 4 5 | . 8 6 | 1 7 9 |
 +-------+-------+-------+
 
 
 Solving board...SOLVED in 0,006 seconds.
 
 +-------+-------+-------+
 | 5 3 4 | 6 7 8 | 9 1 2 |
 | 6 7 2 | 1 9 5 | 3 4 8 |
 | 1 9 8 | 3 4 2 | 5 6 7 |
 +-------+-------+-------+
 | 8 5 9 | 7 6 1 | 4 2 3 |
 | 4 2 6 | 8 5 3 | 7 9 1 |
 | 7 1 3 | 9 2 4 | 8 5 6 |
 +-------+-------+-------+
 | 9 6 1 | 5 3 7 | 2 8 4 |
 | 2 8 7 | 4 1 9 | 6 3 5 |
 | 3 4 5 | 2 8 6 | 1 7 9 |
 +-------+-------+-------+
 
 
  ----jGRASP: Operation complete.
  */