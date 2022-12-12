import edu.duke.*;
public class TestCaesarCipher
{
    
    private int[] countLetters(String message) 
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] values) {
        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public String breakCaesarCipher(String input)
    {
        int[] counts = countLetters(input);
        int maxIdx = maxIndex(counts);
        int dkey = maxIdx - 4;
        if (maxIdx < 4) {
            dkey = 26 - (4 - maxIdx);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    
    public void simpleTests()
    {
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String message = fr.asString();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
        String decryptedMessage = breakCaesarCipher(encryptedMessage);
        System.out.println(decryptedMessage);
    }
    
    
}
