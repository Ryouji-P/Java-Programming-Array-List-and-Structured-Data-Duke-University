import java.util.*;
import edu.duke.*;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>(); 
    }

    public void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int max = 0;
        int index = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }
    
    public void tester()
    {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        //for (int k = 0; k < myWords.size(); k++) {
        //    System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        //}
        
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myFreqs.get(index) + "\t" + myWords.get(index));
    }
}
