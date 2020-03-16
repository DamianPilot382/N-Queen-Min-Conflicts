/**
 * @author Damian Ugalde
 * @date 2020-03-15
 * @version 1.0
 *
 * Project 3
 * CS 4200 - Artificial Intelligence
 * California State Polytechnic University, Pomona
 * Computer Science Department
 *
 * Instructor: Dominick A. Atanasio
 *
 */
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChessBoard extends GridPane {

    //Store all the button pointers
    private Button[][] grid;

    private Board board;

    /**
     * Creates a new ChessBoard object for the GUI.
     * @param size size of the board for the n x n Queens problem.
     */
    public ChessBoard(int size){
        super();

        this.grid = new Button[size][size];

        //Fill the grid array with button objects
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                //Create a button
                Button current = new Button();

                //Set the button preferences
                current.setDisable(true);
                current.setPrefSize(35, 35);
                current.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                //Color the button as in chess.
                if(i % 2 == j % 2)
                    current.setStyle("-fx-color: white");
                else
                    current.setStyle("-fx-color: gray");

                //Assign the button to the grid and add it to the GUI.
                this.grid[i][j] = current;
                this.add(current, i, j);
            }
        }
    }

    /**
     * Run the minimum conflicts algorithm on this board.
     * @return time it took to run the algorithm in milliseconds.
     */
    public long minConflictAlgorithm(){

        //Create a new conflict algorithm solver        
        MinConflict conflict = new MinConflict(25, 10000);

        //Start the timer
        long time = System.currentTimeMillis();

        //Solve the board and save a pointer to the solution.
        board = conflict.search();

        //End the timer and return the difference
        return System.currentTimeMillis() - time;
    }

    /**
     * Draw the queens on the board based on the board object stored.
     */
    public void placeQueens(){

        //Get the board data
        int[] data = board.getData();

        //Loop through the board
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data.length; j++){

                //If a queen belongs here, place it. Otherwise, set it to empty.
                if(j == data[i])
                    grid[i][j].setText("\u265B");
                else
                    grid[i][j].setText("");
            }
        }
    }

    /**
     * Gets the board object stored. This object should be a solution to the problem.
     * @return Board object stored
     */
    public Board getPuzzle(){
        return this.board;
    }



}
