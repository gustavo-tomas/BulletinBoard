package bulletinboard;

import java.util.List;
import java.util.Arrays;

/**
 *
 * @author marcu
 */
public class WordFrequencyApplication  {
    EventManager eventManager;

    public WordFrequencyApplication(EventManager eventManager) {
        this.eventManager = eventManager;
        
        Handler handler1 = new Handler(){public void handle(List<String> event){ run(event); } };
        this.eventManager.subscribe("run", handler1);
        Handler handler2 = new Handler(){public void handle(List<String> event){ stop(event); } };
        this.eventManager.subscribe("eof", handler2);
    }
    
    
    
    
    public void run(List<String> event){
        String pathToFile = event.get(1);
        
        eventManager.publish(Arrays.asList("load", pathToFile));
        eventManager.publish(Arrays.asList("start"));
    }
    
    public void stop(List<String> event){
        eventManager.publish(Arrays.asList("print"));
    }
}
