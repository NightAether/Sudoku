// Name: Viktoriia Sahaidak
// Class: CS143 Java 2
// Assignment: SudokuBoard
// This program will read data from text file and load it into the class, preparing data for the sudoku game

import java.io.*;
import java.util.*;

public class SudokuBoard {
   private char[][] board;   //or int[][]
   
   //pre: readable text file with 9 lines and 9 char each
   //post: fills the 9x9 char array with digits or '.' from the file
   public SudokuBoard(String fileName) throws FileNotFoundException {
      board = new char[9][9]; //creates the board 9x9
      
      Scanner input = new Scanner(new File(fileName));
      
      for (int row = 0; row < 9; row++) {
         String line = input.nextLine().trim();
         
         for (int col = 0; col < 9; col++) {
            board[row][col] = line.charAt(col); 
         }
      }
      
      input.close();
   } 
   
   //pre: array lists filled with data and initialized 
   //post: returns a formatted string with 9 lines
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
         
}

/*
 ----jGRASP exec: java PlaySudoku
 +-------+-------+-------+
 | 2 . . | 1 . 5 | . . 3 |
 | . 5 4 | . . . | 7 1 . |
 | . 1 . | 2 . 3 | . 8 . |
 +-------+-------+-------+
 | 6 . 2 | 8 . 7 | 3 . 4 |
 | . . . | . . . | . . . |
 | 1 . 5 | 3 . 9 | 8 . 6 |
 +-------+-------+-------+
 | . 2 . | 7 . 1 | . 6 . |
 | . 8 1 | . . . | 2 4 . |
 | 7 . . | 4 . 2 | . . 1 |
 +-------+-------+-------+
 
 
  ----jGRASP: Operation complete.
 
*/