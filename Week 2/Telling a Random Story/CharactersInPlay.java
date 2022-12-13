import java.util.*;
import edu.duke.*;
public class CharactersInPlay
{
    private ArrayList<String> names;
    private ArrayList<Integer> counts;


    public CharactersInPlay()
    {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update (String person)
    {
        int index = names.indexOf(person);
        if (index != -1) {
            int value = counts.get(index);
            counts.set(index, value + 1);
        } else {
            names.add(person);
            counts.add(1);
        }
    }
    
    public void findAllCharacters()
    {
        names.clear();
        counts.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int periodIndex = line.indexOf(".");
            if ( periodIndex != -1) {
                String name = line.substring(0, periodIndex);
                update(name);
            } 
        }
    }
    
    public int findIndexOfMax()
    {
        int max = 0;
        int index = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) > max) {
                max = counts.get(i);
                index = i;
            }
        }
        return index;
    }
    public void charactersWithNumParts (int num1, int num2)
    {
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + " speaks " + counts.get(i) + " lines between " + num1 + " and " + num2);
            }
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) >= 2) {
                System.out.println(names.get(i) + " speaks " + counts.get(i) + " lines ");
            }
        }
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + counts.get(index) + "\t" + names.get(index));
        charactersWithNumParts(10,15);
    }
}
