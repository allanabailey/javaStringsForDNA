package StringsClass1;


/**
 * An extension to findGeneSimple whereby it checks that the gene would be valid - i.e difference between start and stop codon is a multiple of 3.
 * 
 * @author Allana Bailey
 * @version 1
 */
public class findGeneSimpleExtended {
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        while(currIndex != -1) {
            //Check is currIndex - startIndex is a multiple of 3.
            if ((currIndex - startIndex) % 3 == 0) {
                //If so, the text between startIndex and currIndex + 3 is the answer.
                return dna.substring(startIndex, currIndex + 3);
            } else {
                //If not update currIndex to the index of the next "TAA"
                currIndex = dna.indexOf("TAA", currIndex +1);
            }
        }
        return "";
    }
    
    public void testGene() {
        //ATG and TAA present and multiple of 3
        String test1 = "ACGTATGCGCTAAGC";
        System.out.println("DNA String: " + test1);
        String result1 = findGene(test1);
        System.out.println("Gene: " + result1);
        //ATG and TAA present but not multiple of 3
        String test2 = "ACGTATGCGTAAGC";
        System.out.println("DNA String: " + test2);
        String result2 = findGene(test2);
        System.out.println("Gene: " + result2);
        //No ATG present
        String test3 = "AGCTCCGGTAAGCT";
        System.out.println("DNA String: " + test3);
        String result3 = findGene(test3);
        System.out.println("Gene: " + result3);
        //No TAA present
        String test4 = "AGCTTGCCAAATGC";
        System.out.println("DNA String: " + test4);
        String result4 = findGene(test4);
        System.out.println("Gene: " + result4);
    }
}
