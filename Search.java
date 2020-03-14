/**
 * @author Damian Ugalde
 * @date 2020-03-08
 * @version 1.0
 *
 * Project 2
 * CS 4200 - Artificial Intelligence
 * California State Polytechnic University, Pomona
 * Computer Science Department
 *
 * Instructor: Dominick A. Atanasio
 *
 */
public class Search{

    public static void testMinConflictAlg(Tester tester) throws Exception {

        int iterations = 400;
        int size = 25;
        int maxSteps = 10000000;

        tester.testMinConflictAlg("minConf_size", iterations, size, maxSteps);

    }

    public static void main(String[] args){

        try{
            Tester tester = new Tester();

            testMinConflictAlg(tester);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }

    }

}