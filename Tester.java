import java.io.FileWriter;
import java.io.IOException;

public class Tester {

    private FileWriter writer;

    public void changeFileName(String name) throws IOException {
        writer = new FileWriter(name+".csv");
    }

    public void testMinConflictAlg(String fileName, int iterations, int size, int maxSteps) throws IOException {
        this.changeFileName(fileName);

        writer.append("iteration,size,maxSteps,attack,time,\n");

        for(int j = 1; j <= iterations; j++){
            MinConflict conflict = new MinConflict(size, maxSteps);
            long time = System.currentTimeMillis();
            Board b = conflict.search();
            time = System.currentTimeMillis() - time;
            writer.append(j+","+size+","+maxSteps+","+b.getAttackingValue()+","+time+",\n");

            writer.flush();
        }
        
        writer.close();
    }

}