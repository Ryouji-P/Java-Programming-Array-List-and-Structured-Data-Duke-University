
public class CaesarCipherTwo
{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input)
    {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(Character.toLowerCase(c));
            if (idx != -1 && i % 2 == 0) {
                char newChar = shiftedAlphabet1.charAt(idx);
                if (Character.isLowerCase(c)) newChar = Character.toLowerCase(newChar);
                if (Character.isUpperCase(c)) newChar = Character.toUpperCase(newChar);
                sb.setCharAt(i, newChar);
            } else if (idx != -1 && i % 2 != 0){
                char newChar = shiftedAlphabet2.charAt(idx);
                if (Character.isLowerCase(c)) newChar = Character.toLowerCase(newChar);
                if (Character.isUpperCase(c)) newChar = Character.toUpperCase(newChar);
                sb.setCharAt(i, newChar);
            }
        }
        String ans = sb.toString();
        System.out.println(ans);
        return ans;
    }
    
    public String decrypt(String input)
    {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String ans = cc.encrypt(input);
        System.out.println(ans);
        return ans;
    }
}
