import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordUsed;
    private ArrayList<String> existedList;
    private int countOfUsed = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        myMap = new HashMap<String, ArrayList<String>>();
        wordUsed = new ArrayList<String>();
        existedList = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        myMap = new HashMap<String, ArrayList<String>>();
        wordUsed = new ArrayList<String>();
        existedList = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", 
                            "adjective", "name", "color",
                            "timeframe", "verb", "fruit"};
        for (String s : labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        addExistedList(label);
        return randomFrom(myMap.get(label));
    }
    
    private void addExistedList(String label) {
       if (existedList.indexOf(label) == -1){
           existedList.add(label);
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        
        
        while(true){
            sub = getSubstitute(w.substring(first+1,last));
            //int wordIdx = wordUsed.indexOf(sub);
            
            if (!wordUsed.contains(sub)){
                wordUsed.add(sub);
                countOfUsed++;
                break;
            }
        }
        
        return prefix+sub+suffix;
    }
    
    private int totalWordsInMap() {
       int sum = 0;      
       for (String category : myMap.keySet()) {
           ArrayList<String> words = myMap.get(category);
           System.out.println("Category:" +category+ "\tTotal words in category:"
            + words.size());
           sum += words.size();
        }
       System.out.println("Lists size: "+sum);
       return sum;
       
    }
    
    private int totalWordsConsidered() {
        ArrayList<String> content = new ArrayList<String>();
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < existedList.size(); index++) {
            String category = existedList.get(index);
            content = myMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            sum += content.size();
        }
        System.out.println("sum of possible words: " + sum);
        return sum;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        System.out.println("Total numbers of words replaced "+ wordUsed);
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        existedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        countOfUsed = 0;
        System.out.println("\n");
        System.out.println(totalWordsInMap());
        System.out.println("\n"+totalWordsConsidered());
    }
    


}
