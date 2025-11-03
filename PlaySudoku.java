// Name: Viktoriia Sahaidak
// Class: CS143 Java 2
// Assignment: SudokuBoard
//This program will create the sudoku board with the already uploaded data

import java.io.*;

public class PlaySudoku {
   public static void main(String[] args) throws FileNotFoundException {
      SudokuBoard puzzle = new SudokuBoard("data.sdk");
      
      System.out.println(puzzle);
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