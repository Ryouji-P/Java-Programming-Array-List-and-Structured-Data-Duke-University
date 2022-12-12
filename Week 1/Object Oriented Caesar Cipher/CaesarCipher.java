
public class CaesarCipher
{
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }

    public String encrypt(String input)
    {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++){
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(Character.toLowerCase(c));
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
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
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
