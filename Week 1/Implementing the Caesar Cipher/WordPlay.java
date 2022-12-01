
public class WordPlay
{

    public boolean isVowel (char ch)
    {
        char currentChar = Character.toLowerCase(ch);
        if (ch == 'a' ||
                ch == 'e' ||
                ch == 'i' ||
                ch == 'o' ||
                ch == 'u') return true;
        return false;
    }
    
    public void testIsVowel()
    {
        char test = 'F';
        System.out.println(isVowel(test));
        test = 'a';
        System.out.println(isVowel(test));
    }
    
    public String replaceVowels (String phrase, char ch)
    {
        StringBuilder phraseStr = new StringBuilder(phrase);
        for (int i = 0; i < phraseStr.length(); i++) {
            char currentChar = phraseStr.charAt(i);
            if (isVowel(currentChar)) {
                phraseStr.setCharAt(i,ch);
            }  
        }
        return phraseStr.toString();
    }
    
    public void testReplaceVowels()
    {
        System.out.println(replaceVowels("Hello World",'*'));
    }
    
    public String emphasize (String phrase, char ch) 
    {
        StringBuilder phraseStr = new StringBuilder(phrase);
        for (int i = 0; i < phraseStr.length(); i++) {
            char currentChar = phraseStr.charAt(i);
            if (Character.toLowerCase(currentChar) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    phraseStr.setCharAt(i,'*');
                } else {
                    phraseStr.setCharAt(i,'+');
                }
            }
        }
        return phraseStr.toString();
    }
    
    public void testEmphasize()
    {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
        
    }
    
}
