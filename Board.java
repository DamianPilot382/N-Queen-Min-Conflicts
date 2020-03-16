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

        this.data = new int[size];

        //Fill each spot in the array with a number from 0 to size-1.
        for(int i = 0; i < data.length; i++){
            data[i] = (int)(Math.random() * size);
        }

    }

    /**
     * Gets the number of queens that are in attack mode.
     * This does not count duplicates. Given a queen 'a' and a queen 'b',
     * 'a' will be attacking 'b' while 'b' is also attacking 'a'.
     * This method only counts that as one attack.
     * @return Count of queens in attack mode.
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

        //Get a random column and row
        int randCol = (int)(Math.random() * this.data.length);
        int randRow = -1;

        //Make sure the next row is not the one assigned now.
        do{
            randRow = (int)(Math.random() * this.data.length);
        }while(randRow == this.data[randCol]);

        //Clone the data into a new array for the new board.
        int[] arr = this.data.clone();

        //Change the value
        arr[randCol] = randRow;

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