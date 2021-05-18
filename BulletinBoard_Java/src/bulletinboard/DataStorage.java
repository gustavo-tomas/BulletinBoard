package bulletinboard;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.util.Arrays;
 

public class DataStorage {
    EventManager eventManager;
    String[] data;
    
    public DataStorage(){
        eventManager = null;
    }

    public DataStorage(EventManager eventManager) {
        this.eventManager = eventManager;
        
        Handler handler1 = new Handler(){public void handle(List<String> event){ load(event); } };
        this.eventManager.subscribe("load", handler1);
        Handler handler2 = new Handler(){public void handle(List<String> event){ produceWords(event); } };
        this.eventManager.subscribe("start", handler2);
        
    }
    
    public void load(List<String> event){
        
        try{
            String pathToFile = event.get(1);
            String fileData = new String(Files.readAllBytes(Paths.get(pathToFile)));
            data = fileData.toLowerCase().trim().replaceAll("[\\W_]+(\\b\\w{1,4}\\b)","").split(" ");
                        
          }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
        
      
    }
    
    public void produceWords(List<String> event){
        
        for(String w : data)
            this.eventManager.publish(Arrays.asList("word", w));
            
        this.eventManager.publish(Arrays.asList("eof"));
        
    }
    
    
}
