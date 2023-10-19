import java.io.BufferedReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{

        final int dimthreadpool = 5;
        ExecutorService executor = Executors.newFixedThreadPool(dimthreadpool);
            
        FileReader fr = new FileReader("filestoanalyze.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null){
            Runnable task = new CharacterCountTask(line);
            executor.execute(task);
        }

        executor.shutdown();
        while(!executor.isTerminated()){
            Thread.sleep(100);
        }
        
        br.close();
        fr.close();

    }
}