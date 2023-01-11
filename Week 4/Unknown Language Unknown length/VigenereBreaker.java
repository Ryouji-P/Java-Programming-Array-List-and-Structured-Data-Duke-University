import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char c = message.charAt(i);
            answer.append(c);
        }
        return answer.toString();
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        int thisKey;
        CaesarCracker cc = new CaesarCracker();
        for (int i = 0; i < klength; i++) {
            thisKey = cc.getKey(sliceString(encrypted, i, klength));
            key[i] = thisKey;
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        DirectoryResource dr = new DirectoryResource();
        
        HashMap<String, HashSet<String>> dictionaryMap = new HashMap<>();
        
        for (File f : dr.selectedFiles()) {
            FileResource frd = new FileResource(f);
            HashSet<String> dictionary = readDictionary(frd);
            dictionaryMap.put(f.getName(), dictionary); 
        }
        
        breakForAllLangs(message, dictionaryMap);
   
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for (String line : fr.lines()) {
            words.add(line.toLowerCase());
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        //int total = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        //    total++;
        }
        //System.out.println("This file contains " + count + " valid words out of " + total);
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        String answer = "";
        int klength = 0;
        //int[] answerKey = new int[100];
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for (int k = 1; k <= 100; k++) {
            int[] key = tryKeyLength(encrypted, k, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int wordCount = countWords(message, dictionary);
            if (wordCount > max) {
                max = wordCount;
                answer = message;
        //        answerKey = key;
                klength = k;
            }
        }
        //for (int i : answerKey) {
        //    System.out.println(i);
        //}
        //System.out.println("The number of valid words is " + max);
        return answer;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> count = new HashMap<>();
        char mostCommonChar = 'a';
        int max = 0;
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (count.containsKey(c)) {
                    count.put(c, count.get(c) + 1);
                } else {
                    count.put(c, 1);
                }
            }
        }
        
        for (Character c : count.keySet()) {
            if (count.get(c) > max) {
                max = count.get(c);
                mostCommonChar = c;
            }
        }
        
        System.out.println("The most common character is " + mostCommonChar);
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String answer = "";
        String langName = "";
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int wordCounts = countWords(encrypted, dictionary);
            System.out.println("This message is in language " + lang);
            System.out.println("This message has valid words of " + wordCounts);
            System.out.println(decrypted);
            //if (wordCounts > max) {
            //    max = wordCounts;
            //    answer = breakForLanguage(encrypted, dictionary);
            //    langName = lang;
            //}
        }
        
        //System.out.println("This message is in language " + langName);
        //System.out.println("This message has valid words of " + max);
        //System.out.println(answer);
    }
}
