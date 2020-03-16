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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MinConflict {

    public Board board;

    //Keeps track of the conflicts
    private HashMap<Integer, HashSet<Integer>> conflictMap;

    //Keeps track of a column has a conflict at all.
    private boolean[] conflicts;

    private int maxSteps;
    private final int n;
    
    /**
     * @param n size of the board
     * @param maxSteps amount of steps allowed before a solution must be given.
     */
    public MinConflict(int n, int maxSteps){
        this.n = n;

        //Initialize the objects
        this.board = new Board(n);
        this.conflictMap = new HashMap<>(n, 1);
        this.maxSteps = maxSteps;
        this.conflicts = new boolean[n];

        //Fill and initialize the conflict map.
        this.fillConflictsMap();
        this.checkAllConflicts();
    }
  
    /**
     * Creates a new set for each element in the conflict map.
     */
    private void fillConflictsMap(){
        //Loop through the conflicts map hashmap and initialize each spot with a hash set.
        for(int i = 0; i < n; i++)
            conflictMap.put(i, new HashSet<Integer>(n-1));
    }

    /**
     * Check all of the conflicts in the board and fill the conflicts map and conflicts array.
     * Used when first initializing the object.
     */
    private void checkAllConflicts(){

        //Loop through all the spots in the board.
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){

                //Get the value for the column in each row.
                int a = board.getData()[i];
                int b = board.getData()[j];

                //If they are in the same row or in the same diagonal
                if(a == b || (j - i) == Math.abs(b - a)){

                    //Add a pointer to each column in the conflict map
                    conflictMap.get(i).add(j);
                    conflictMap.get(j).add(i);

                    //Set that there is a conflict on this column in the conflict array.
                    conflicts[i] = true;
                    conflicts[j] = true;
                }
            }
        }
    }

    /**
     * Uses Minimum Conflict Algorithm to search for a solution for the N-Queens problem.
     * @return Board with the best solution found.
     */
    public Board search(){

        int steps = maxSteps;

        //Keep looping while there are more steps.
        while(steps-- > 0){

            //If you found a solution, return it.
            if(this.board.getAttackingValue() == 0)
                return this.board;
            
            //Otherwise, find a random conflict and minimize it.
            minimizeConflict();
        }

        //If no solution found after you reach the max steps, return what you have so far.
        return this.board;
    }

    /**
     * Minimizes the conflict for a random column.
     */
    private void minimizeConflict(){

        //Get a random column to minimize the conflict for.
        int randCol = this.getRandomConflict();

        //Store the conflicts for the minimum solution found so far.
        HashSet<Integer> best = null;
        //Store the best row found so far.
        int bestRow = -1;

        //Next conflict to check
        HashSet<Integer> next = null;

        //Loop through all the rows available.
        for(int i = 0; i < n; i++){

            //Check this row's conflict value.
            next = this.checkConflict(randCol, i);

            //If this is the first row or is better than the current best value found
            if(best == null || best.size() > next.size()){
                //Set this as the best by keeping track of the conflicts and the row that produced it.
                best = next;
                bestRow = i;
            //If this row position is equally as good as the previously best found
            }else if(best.size() == next.size()){
                //Either copy this row or keep the last one. Choose at random.
                if(Math.random() > .5){
                    best = next;
                    bestRow = i;
                }
            }

        }

        //Update the conflict map with the best row position found.
        this.updateConflicts(randCol, best);

        //Update the row position for the best value found.
        this.board.getData()[randCol] = bestRow;

    }

    /**
     * Get a random column with conflicts.
     * @return
     */
    private int getRandomConflict(){

        //Get a random column.
        int index = -1;
        do{
            index = (int)(Math.random() * n);
        //Keep looking if this column doesn't have any conflicts.
        }while(this.conflicts[index] != true);
        return index;
    }

    /**
     * Gets the conflicts for this column with an updated row.
     * This method doesn't update the values. Just gives a "what-if".
     * @param col column to check
     * @param newRow new row value to check
     * @return HashSet with the conflicts found.
     */
    private HashSet<Integer> checkConflict(int col, int newRow){

        //Create a temp Hash Set.
        HashSet<Integer> temp = new HashSet<>(n-1);

        //Loop through all the columns.
        for(int i = 0; i < n; i++){
            
            //Get the row for this column.
            int j = board.getData()[i];

            //If the rows are equal or attacking diagonally
            if(newRow == j || Math.abs(i - col) == Math.abs(j - newRow))
                //Add it to the temp set.
                temp.add(i);

        }

        return temp;
    }

    /**
     * Updates the conflicts in the conflict map and conflict array.
     * @param col column to be updated
     * @param conf Hash Set with the conflicts found.
     */
    private void updateConflicts(int col, HashSet<Integer> conf){

        //Get the iterator for all the columns in the conflicts map.
        Iterator<Integer> iter = conflictMap.get(col).iterator();

        //Loop through the columns
        while(iter.hasNext()){
            int next = iter.next();

            //Remove the old conflict
            if(next != col)
                conflictMap.get(next).remove(col);

            //If this column doesn't have any more conflicts, set it to false.
            if(conflictMap.get(next).isEmpty())
                conflicts[next] = false;
        }

        //Get the iterator for the new conflicts.
        iter = conf.iterator();

        //Loop through the new conflicts.
        while(iter.hasNext()){
            int next = iter.next();

            //Add the conflict to this column.
            conflictMap.get(next).add(col);

            //Set the conflict flag to true.
            conflicts[next] = true;
        }

        //Replace the old conflicts for this row with the new one.
        conflictMap.put(col, conf);
    }

    /**
     * String representation of this object.
     * Used to print to the console.
     * It will print the configuration of the board,
     * plus the conflict map.
     */
    @Override
    public String toString(){
        
        //Create a StringBuilder for efficiency.
        StringBuilder builder = new StringBuilder();

        //Add the board status
        builder.append(this.board.toString());

        //Get the iterator for the conflicts map.
        Iterator<Integer> iter = conflictMap.keySet().iterator();

        while(iter.hasNext()){

            Integer index = (Integer) iter.next();

            //Append the current col value
            builder.append(index + ": ");

            HashSet<Integer> next = conflictMap.get(index);

            //Append all the conflicts
            for(int i : next){
                builder.append(i+" ");
            }

            //Create a new line
            builder.append("\n");
        }

        return builder.toString();
    }

}