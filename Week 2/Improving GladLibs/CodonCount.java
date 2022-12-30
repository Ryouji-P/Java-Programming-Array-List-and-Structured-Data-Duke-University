import java.util.*;
import edu.duke.*;

public class CodonCount
{
    private HashMap<String,Integer> dnaMap;

    public CodonCount()
    {
        dnaMap = new HashMap<String,Integer>();
    }

    public void buildCodonMap(int start, String dna)
    {
        dnaMap.clear();
        int count = 0;
        for (int i = start; i < dna.length() - 3; i += 3){
            String codon = dna.substring(i, i + 3);
            if (dnaMap.containsKey(codon)){
                dnaMap.put(codon, dnaMap.get(codon) + 1);
            } else {
                dnaMap.put(codon, 1);
                count++;
            }
        }
        System.out.println("Reading frame starting with " + start + " results in " 
                            + count + " unique codons");
    }
    
    public String getMostCommonCodon()
    {
        int max = 0;
        String mostCommonCodon = "";
        for (String s : dnaMap.keySet()){
            if (dnaMap.get(s) > max){
                max = dnaMap.get(s);
                mostCommonCodon = s;
            }
        }
        System.out.println("and most common codon is " + mostCommonCodon + " with count " + max);
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end)
    {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String s : dnaMap.keySet()){
            int count = dnaMap.get(s);
            if (count >= start && count <= end){
                System.out.println(s + "\t" + count);
            }
        }
    }
    
    public void testCodonCount()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        for (int i = 0; i < 3; i++){
            buildCodonMap(i, dna);
            getMostCommonCodon();
            printCodonCounts(1,5);
        }
    }
}
