package bulletinboard;

import java.util.Arrays;
/**
 *
 * @author marcu
 */
public class BulletinBoard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        EventManager eventManager = new EventManager();
        DataStorage dataStorage = new DataStorage(eventManager);
        StopWordFilter stopWordFilter = new StopWordFilter(eventManager);
        WordFrenquencyCounter wordFrenquencyCounter = new WordFrenquencyCounter(eventManager);
        WordFrequencyApplication wordFrenquencyApplication = new WordFrequencyApplication(eventManager);
        
        
        
        eventManager.publish(Arrays.asList("run","C:\\Users\\andre\\Desktop\\UnB\\4_SEMESTRE\\TP2\\exercises_programming_style\\bulletin_board\\nosso\\v3\\BulletinBoard_v3\\src\\main\\scala\\bigtext.txt"));
     
        
       
    }
    
}
