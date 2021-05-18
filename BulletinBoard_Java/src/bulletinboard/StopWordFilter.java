package bulletinboard;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author marcu
 */
public class StopWordFilter {
    EventManager eventManager;
    List<String> stopWords;

    public StopWordFilter(EventManager eventManager) {
        this.eventManager = eventManager;
        stopWords =  new ArrayList<String>();
        
        Handler handler1 = new Handler(){public void handle(List<String> event){ load(event); } };
        this.eventManager.subscribe("load", handler1);
        Handler handler2 = new Handler(){public void handle(List<String> event){ isStopWord(event); } };
        this.eventManager.subscribe("word", handler2);   
    }
    
    public void load(List<String> event){
        try{     
            InputStream inputStream = getClass().getResourceAsStream("/resources/stopwords.txt");
            String fileData = new String(inputStream.readAllBytes());
            stopWords = Arrays.asList(fileData.toLowerCase().trim().replace("[\\W_]+","").split(", "));
                        
          }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }   
    }
    
    public void isStopWord(List<String> event){
        String word = event.get(1);
        if(!stopWords.contains(word)){
            eventManager.publish(Arrays.asList("valid_word", word));
        }
    }
}
