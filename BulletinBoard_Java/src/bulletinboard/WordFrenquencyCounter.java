package bulletinboard;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author marcu
 */
public class WordFrenquencyCounter {
    EventManager eventManager;
    Map<String, Integer> wordFreqs;

    public WordFrenquencyCounter(EventManager eventManager) {
        this.eventManager = eventManager;
        wordFreqs = new HashMap<String, Integer>();
        
        Handler handler1 = new Handler(){public void handle(List<String> event){ incrementCount(event); } };
        this.eventManager.subscribe("valid_word", handler1);
        Handler handler2 = new Handler(){public void handle(List<String> event){ printFreqs(event); } };
        this.eventManager.subscribe("print", handler2);
    }
    
    
    
    
    public void incrementCount(List<String> event){
        String word = event.get(1);
        
        int count = wordFreqs.containsKey(word) ? wordFreqs.get(word) : 0;
        wordFreqs.put(word, count + 1);
        
    }
    public void printFreqs(List<String> event){
        wordFreqs.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
        .limit(25)
        .forEach(System.out::println);
    }
    
}
