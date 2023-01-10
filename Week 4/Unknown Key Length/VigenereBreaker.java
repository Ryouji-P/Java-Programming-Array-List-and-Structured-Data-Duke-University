import java.util.*;
import edu.duke.*;

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
        FileResource englishDictionary = new FileResource("dictionaries/English");
        //int[] key = tryKeyLength(message,5,'e');
        //for (int i : key) {
        //    System.out.println(i);
        //}
        //VigenereCipher vc = new VigenereCipher(key);
        HashSet<String> words = readDictionary(englishDictionary);
        String answer = breakForLanguage(message, words);
        System.out.println("This file contains " + countWords(answer, words) + " valid words");
        System.out.println(answer);
        
        int[] key = tryKeyLength(message, 38, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String answer38 = vc.decrypt(message);
        System.out.println("This file contains " + countWords(answer38, words) + " valid words");

        
        
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
        int total = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
            total++;
        }
        //System.out.println("This file contains " + count + " valid words out of " + total);
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        String answer = "";
        int klength = 0;
        int[] answerKey = new int[100];
        for (int k = 1; k <= 100; k++) {
            int[] key = tryKeyLength(encrypted, k, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int wordCount = countWords(message, dictionary);
            if (wordCount > max) {
                max = wordCount;
                answer = message;
                answerKey = key;
                klength = k;
            }
        }
        //for (int i : answerKey) {
        //    System.out.println(i);
        //}
        System.out.println("Key length is " + klength);
        return answer;
    }
}
