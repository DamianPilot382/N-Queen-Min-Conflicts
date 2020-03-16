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
import java.io.FileWriter;
import java.io.IOException;

public class Tester {

    private FileWriter writer; //Used to write to a csv file.

    /**
     * Changes the name of the file to be written into.
     * @param name New file name
     * @throws IOException Something went wrong while writing or opening the file.
     */
    public void changeFileName(String name) throws IOException {
        writer = new FileWriter(name+".csv");
    }

    /**
     * Tests the minimum conflicts algorithm and exports to the file specified.
     * @param iterations number of times to run the algorithm
     * @param size Size of the board
     * @param maxSteps maximum steps allowed to take.
     * @throws IOException Something goes wrong writing to the file.
     */
    public void testMinConflictAlg(int iterations, int size, int maxSteps) throws IOException {

        for(int j = 1; j <= iterations; j++){
            //Create a new conflict solver
            MinConflict conflict = new MinConflict(size, maxSteps);

            //Start the timer
            long time = System.currentTimeMillis();

            //Solve the board
            Board b = conflict.search();

            //End the timer
            time = System.currentTimeMillis() - time;

            //Add the results to the file-writing queue.
            writer.append(j+","+size+","+maxSteps+","+b.getAttackingValue()+","+time+",\n");
        }
        //Write the results to the file.
        writer.flush();
    }

    /**
     * Write the headers on this file.
     * @throws IOException Something went wrong when writing to the file.
     */
    public void writeHeader() throws IOException {
        writer.append("iteration,size,maxSteps,attack,time,\n");
        writer.flush();
    }

    /**
     * Closes the file
     * @throws IOException something goes wrong closing the file.
     */
    public void closeFile() throws IOException {
        writer.close();
    }

}