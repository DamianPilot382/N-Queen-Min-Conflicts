/**
 * @author Damian Ugalde
 * @date 2020-03-08
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
import java.util.ArrayList;
import java.util.Collections;

public class Board implements Comparable<Board> {

    //Each index in this array holds the col, while the int stored holds the row for the queen.
    private int data[];

    /**
     * Creates a random board with the specified size.
     * @param size size of one side of the n x n board.
     */
    public Board(int size){
        randomBoard(size);
    }

    /**
     * Create a board with a specific configuration.
     * @param data board configuration to use.
     */
    public Board(int data[]){
        this.data = data;
    }

    /**
     * Creates a new random board.
     * @param size size of the side of the n x n board.
     */
    private void randomBoard(int size){

        //Create a new array
        this.data = new int[size];

        //Create a new arraylist, and add all the elements from 0 to size to it.
        //Since each queen has to be in a unique row and column, this guarantees that no
        //queen will be in the same row.
        ArrayList<Integer> temp = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            temp.add(i);
        }

        //Shuffle the arraylist
        Collections.shuffle(temp);

        //Copy the shuffled arraylist to the array
        for(int i = 0; i < this.data.length; i++){
            this.data[i] = temp.get(i);
        }

    }

    /**
     * Gets the number of queens that are in attack mode.
     * This does not count duplicates. Given a queen 'a' and a queen 'b',
     * 'a' will be attacking 'b' while 'b' is also attacking 'a'.
     * This method only counts that as one attack.
     * @return Queens in attack mode.
     */
    public int getAttackingValue(){

        int count = 0;

        for(int i = 0; i < data.length; i++){
            for(int j = i + 1; j < data.length; j++){
                
                //Check for diagonal attacks and same row
                if((j - i) == Math.abs(data[j] - data[i]) || data[i] == data[j])
                    count++;
            }
        }

        return count;
    }

    /**
     * Gets the data for this board
     * @return data array for this board.
     */
    public int[] getData(){
        return this.data;
    }

    /**
     * Gets the size n of this board
     * @return Size of the board.
     */
    public int getSize(){
        return this.data.length;
    }

    /**
     * Gets a random successor for this board.
     * @return Random successor
     */
    public Board getRandomSuccessor(){

        //Get a first random col
        int col1 = (int)(Math.random() * this.data.length);

        //Get a second random col, while making sure that it's different than col1.
        int col2 = 0;
        do{
            col2 = (int)(Math.random() * this.data.length);
        }while(col1 == col2);

        //Clone the data into a new array for the new board.
        int[] arr = this.data.clone();

        //Swap the two column values.
        int temp = arr[col1];
        arr[col1] = arr[col2];
        arr[col2] = temp;

        //Make a new board with the new data and return it.
        return new Board(arr);

    }

    /**
     * Checks which board should come first when being ordered.
     * @param other the other board to compare.
     * @return < 0 if this board comes first, > 0 if the other board comes first. 0 if equal.
     */
    @Override
    public int compareTo(Board other){
        return this.getAttackingValue() - other.getAttackingValue();
    }

    /**
     * Gets the data in a string representation so that it can be printed.
     */
    @Override
    public String toString(){
        
        //Create a StringBuilder for efficiency
        StringBuilder builder = new StringBuilder(this.data.length * 2);

        //Add all the data items separated by a space.
        for(int i : this.data){
            builder.append(i + " ");
        }

        //Add the final attacking value for reference.
        builder.append("\nAttacking: " + this.getAttackingValue());

        //Return the string created.
        return builder.toString();
    }
    
}