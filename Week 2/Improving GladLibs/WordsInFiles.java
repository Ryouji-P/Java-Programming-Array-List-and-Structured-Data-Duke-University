import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles
{
    
    private HashMap<String, ArrayList> wordMap;

    public WordsInFiles()
    {
        wordMap = new HashMap<String, ArrayList>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if (!wordMap.containsKey(word)){
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                wordMap.put(word, fileNames);
            } else {
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames = wordMap.get(word);
                if (!fileNames.contains(f.getName())) fileNames.add(f.getName());
            }
        }
    }
    
    public void buildWordFileMap()
    {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber()
    {
        int max = 0;
        for (ArrayList s : wordMap.values()){
            if (s.size() > max) max = s.size();
        }
        return max;
    }
    
    public ArrayList wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<String>();
        int count = 0;
        for (String word : wordMap.keySet()){
            int size = wordMap.get(word).size();
            if (size == number){
                words.add(word);
                count++;
            }
        }
        System.out.println("total of words occurred in " + number + " files: " + count);
        return words;
    }
    
    public void printFilesIn(String word)
    {
        System.out.println("\nThe word " + word + " is in the following files: ");
        for (String s : wordMap.keySet()){
            if (s.equals(word)){
                ArrayList fileNames = wordMap.get(s);
                for (int i = 0; i < fileNames.size(); i++){
                    System.out.println(fileNames.get(i));
                }
            }
        }
    }
    
    public void tester()
    {
        buildWordFileMap();
        ArrayList wordsInNumFiles = wordsInNumFiles(5);
        wordsInNumFiles = wordsInNumFiles(4);
        //for (int i=0; i < wordsInNumFiles.size(); i++){
        //   System.out.println(wordsInNumFiles.get(i));
        //}
        System.out.println("\nMaximum number of words in all the files given = " +maxNumber());
        printFilesIn("sad");
        System.out.println("\n");
        printFilesIn("red");
        //for (String s : wordMap.keySet() ){
        //    System.out.println(s + wordMap.get(s) );
        //}
    }
}
