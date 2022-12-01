
public class CaesarCipher
{
    
    public String encrypt (String input, int key)
    {
        StringBuilder encrypt = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        
        for (int i = 0; i < encrypt.length(); i++) {
            char currentChar = encrypt.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currentChar));
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (Character.isLowerCase(currentChar)) newChar = Character.toLowerCase(newChar);
                if (Character.isUpperCase(currentChar)) newChar = Character.toUpperCase(newChar);
                encrypt.setCharAt(i, newChar);
            }
        }
        return encrypt.toString();
    }
    
    public void testEncrypt()
    {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!",23));
        System.out.println(encrypt("First Legion",23));
        System.out.println(encrypt("First Legion",17));
    }
    
    public String encryptTwoKeys (String input, int key1, int key2) 
    {
        StringBuilder encrypt = new StringBuilder(input);
        String encrypt1 = encrypt(input, key1);
        String encrypt2 = encrypt(input, key2);
        
        for (int i = 0; i < encrypt.length(); i++) {
            
            if (i % 2 == 0) encrypt.setCharAt(i, encrypt1.charAt(i));
            else encrypt.setCharAt(i, encrypt2.charAt(i));
        }
        return encrypt.toString();
    }
    
    public void testEncryptTwoKeys() 
    {
        System.out.println(encryptTwoKeys("First Legion",23,17));
    }
}
