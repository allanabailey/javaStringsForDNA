package StringsClass1;


/**
 * A simple method to identify genes with a starting codon of "ATG" and a
 * stop codon of "TAA" in a long DNA string.
 * 
 * @author Allana Bailey
 * @version 1
 */
public class findGeneSimple {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if(stopIndex == -1) {
            return "";
        }
        String result = dna.substring(startIndex, stopIndex+3);
        if(result.length() % 3 == 0) {
            return result;
        } else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        //ATG and TAA present and multiple of 3
        String test1 = "ACGTATGCGCTAAGC";
        System.out.println("DNA String: " + test1);
        String result1 = findSimpleGene(test1);
        System.out.println("Gene: " + result1);
        //ATG and TAA present but not multiple of 3
        String test2 = "ACGTATGCGTAAGC";
        System.out.println("DNA String: " + test2);
        String result2 = findSimpleGene(test2);
        System.out.println("Gene: " + result2);
        //No ATG present
        String test3 = "AGCTCCGGTAAGCT";
        System.out.println("DNA String: " + test3);
        String result3 = findSimpleGene(test3);
        System.out.println("Gene: " + result3);
        //No TAA present
        String test4 = "AGCTTGCCAAATGC";
        System.out.println("DNA String: " + test4);
        String result4 = findSimpleGene(test4);
        System.out.println("Gene: " + result4);
        
    }
}
