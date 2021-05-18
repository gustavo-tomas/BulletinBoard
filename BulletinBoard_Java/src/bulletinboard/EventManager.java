package bulletinboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 *
 * @author marcu
 */
public class EventManager {
    Map<String, List<Handler>> subscriptions;

    public EventManager() { 
         subscriptions = new HashMap<String, List<Handler>>();
    }
    
    
    
    public void subscribe(String eventType, Handler handler){
        subscriptions.computeIfAbsent(eventType, k -> new ArrayList<Handler>()).add(handler);
       
    }
    
    public void publish(List<String> event){
        String eventType = event.get(0);
        
        
        if(subscriptions.containsKey(eventType)){
            for(Handler handler : subscriptions.get(eventType))
                handler.handle(event);
        }
            
    }
    
}
