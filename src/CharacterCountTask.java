import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class CharacterCountTask implements Runnable{
    private String filePath;

    public CharacterCountTask(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        ConcurrentHashMap<Character,Integer> charCountMap = new ConcurrentHashMap<>();

        File file = new File(filePath);
        if(file.exists() && file.isFile()){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                int c;
                int spacecounter = 0;
                int charactercounter = 0;
                while((c = reader.read()) != -1){

                    char character = (char) c;
                    charactercounter++;
                    if(Character.isWhitespace(character)){
                        spacecounter++;
                        continue;
                    }
                    if(!Character.toString(character).matches("[a-zA-Z]+")){
                        continue;
                    }
                    if(charCountMap.containsKey(character)){
                        charCountMap.put(character,charCountMap.get(character)+1);
                    }else{
                        charCountMap.put(character,1);
                    }
                }
                try{
                    
                    int index = filePath.lastIndexOf(".");
                    FileWriter writer = new FileWriter(filePath.substring(0, index) + "_HashMap.txt");
                    writer.write("Character count for " + file.getName() + "\n");
                    writer.write("==========================================================================================\n");
                    writer.write("Total Characters: " + charactercounter + "\n");
                    writer.write("Total Spaces: " + spacecounter + "\n");
                    writer.write("==========================================================================================\n");

                    for(Character character : charCountMap.keySet()){
                        writer.write(character + ": " + charCountMap.get(character) + "\n");
                    }
                    writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException( file.getName() + " doesn't exist or is not a file");
        }
    }
}
