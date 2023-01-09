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

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = tryKeyLength(message,4,'e');
        for (int i : key) {
            System.out.println(i);
        }
        VigenereCipher vc = new VigenereCipher(key);
        String answer = vc.decrypt(message);
        System.out.println(answer);
    }
    
}
