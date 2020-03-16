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
public class Search{

    /**
     * Method to generate a CSV of results of running the
     * minimum conflicts algorithm.
     * @param args no arguments. Ignored.
     */
    public static void main(String[] args){

        try{

            testMinConflictAlg();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }

    }

    /**
     * Tests the minimum conflict algorithm and exports the results to CSV files.
     * @throws Exception something went wrong when writing to the files.
     */
    public static void testMinConflictAlg() throws Exception {

        //Create a new tester object
        Tester tester = new Tester();

        //Default values for the search
        int iterations = 400;
        int size = 25;
        int maxStep = 10000;

        //Values to change per search.
        int[] sizes = new int[]{8, 16, 25, 50, 60, 100, 125, 150, 175, 200};
        int[] maxSteps = new int[]{100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2500, 5000, 7500, 10000};

        //Test varying sizes
        tester.changeFileName("minConf_size");
        tester.writeHeader();
        for(int i : sizes){
            tester.testMinConflictAlg(iterations, i, maxStep);
        }
        tester.closeFile();

        //Test varying steps
        tester.changeFileName("minConf_step2");
        tester.writeHeader();
        for(int i : maxSteps){
            tester.testMinConflictAlg(iterations, size, i);
        }
        tester.closeFile();

    }

}