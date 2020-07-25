package StringsThirdAssignment;
//https://www.dukelearntoprogram.com//course2/files.php
import edu.duke.StorageResource;
import edu.duke.FileResource;
//https://www.dukelearntoprogram.com/course2/doc/

/**
 * The full code that will allow someone to take a DNA string, identify valid genes within it, process them,
 * collect them into a StorageResource and provide the user with the ability to identify and manipulate genes.
 * 
 * @author Allana Bailey
 * @version 1
 */
public class finalDNACode {
    
    //find location of the first stop codon after the start codon.
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    }
    
    //print out the location of the first stop codon based on the 3 valid options.
    public void testFindStopCodon() {
        String test1 = "ATGXGTTTGTACTGATAACG";
        System.out.println("Index of first stop codon is: " + findStopCodon(test1, 0, "TAA"));
        String test2 = "ATGXGTTTGTACTGATGGACG";
        System.out.println("Index of first stop codon is: " + findStopCodon(test2, 0, "TGA"));
        String test3 = "ATGXGTTTGTACTAGTGGACG";
        System.out.println("Index of first stop codon is: " + findStopCodon(test3, 0, "TAG"));
    }
    
    //return the valid gene found in a dna string.
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        int minIndex = 0;
        if(taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if(minIndex == -1 || tgaIndex != -1 && tagIndex < minIndex) {
            minIndex = tagIndex;
        }
        if(minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene() {
        //no ATG
        String test1 = "AGTGCTGATAAGTC";
        System.out.println("Test 1");
        if(findGene(test1, 0).isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(test1);
        }
        //ATG and one valid stop codon (TAA)
        String test2 = "ATGCCGGTTTAATAG";
        System.out.println("Test 2");
        if(findGene(test2, 0).isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(test2);
        }
        //ATG and multiple stop codons
        String test3 = "ATGGCCCGTTGACCGTGATAG";
        System.out.println("Test 3");
        if(findGene(test3, 0).isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(test3);
        }
        //ATG with no stop codon
        String test4 = "ATGCGCTGCCTTGTTGAC";
        System.out.println("Test 4"); 
        if(findGene(test4, 0).isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(test4);
        }
    }
    
    //print all of the genes found in a string of dna.
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex+3) + currGene.length();
        }
    }
    
    //collect all genes found within a dna string into a StorageResource
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true) {
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length(); 
        }
        return geneList;
    }
    
    public void testGetAll(String dna) {
        System.out.println("Testing getAllGenes on ");
        StorageResource genes = getAllGenes(dna);
        for(String g : genes.data()) {
            System.out.println(g);
        }
    }
    
    //calculate the C-G-ratio in a dna string.
    public double cgRatio(String dna) {
        dna = dna.toUpperCase();
        int cgCount = 0;
        int index = 0;
        double cgRatio = 0.0;
        while(index < dna.length()) {
            char letter = dna.charAt(index);
            char c = 'C';
            char g = 'G';
            int cCheck = Character.compare(letter, c);
            int gCheck = Character.compare(letter, g);
            if(cCheck == 0 || gCheck == 0) {
                cgCount += 1;
            }
            index += 1;
        }
        cgRatio = (double)cgCount/dna.length();
        return cgRatio;
    }
    
    public void testcg() {
        String test1 = "ATGCCATAG";
        double testcg = cgRatio("ATGCCATAG");
        System.out.println(testcg);
    }
    
    //count how many times "CTG" appears in a string of dna.
    public int countCTG(String dna) {
        int index = dna.indexOf("CTG");
        int count = 0;
        while (true) {
            if (index == -1) {
                break;
            }
            if(index >= dna.length() - 3) {
                break;
            } else {
                String found = dna.substring(index+1, index+4);
                index = dna.indexOf("CTG", index+3);
                count ++;
            }
        }
        return count;
    }
    
    public void testCTGCount() {
        String test1 = "GCTGAAACTGGTGTACTGATCTG";
        System.out.println("There are " + countCTG(test1) + " occurrences of CTG in the dna.");
    }
    
    //for a collection of strings, collect, process and record genes within the dna.
    public void processGenes(StorageResource sr) {
        int longerThanSixty = 0;
        int highCGRatio = 0;
        int maxLength = 0;
        int totalCount = 0;
        for(String g : sr.data()) {
            StorageResource allGenes = getAllGenes(g);
            for(String gene : allGenes.data()) {
                int currLength = gene.length();
                System.out.println("Gene found: " + gene);
                totalCount ++;
                if(gene.length() > 60) {
                    System.out.println("This gene is longer than 60 characters.");
                    longerThanSixty ++;
                }
                if(countCTG(gene) > 0.35) {
                    System.out.println("This gene has a C-G-ratio higher than 0.35.");
                    highCGRatio ++;
                }
                if(currLength > maxLength) {
                    maxLength = currLength;
                } 
            }
            System.out.println("The number of strings longer than 60 characters is: " + longerThanSixty);
            System.out.println("The number of strings with a C-G-ratio over 0.35 is: " + highCGRatio);
            System.out.println("The length of the longest gene is: " + maxLength);
            System.out.println("Total number of genes is: " + totalCount);
        }
    }
    
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        String upperDna = dna.toUpperCase();
        sr.add(upperDna);
        processGenes(sr);
        System.out.println("Test: " + countCTG(fr.asString().toUpperCase()));
        /*String test1 = "ATGCTGGTTTTAA";
        String test2 = "GCTTC";
        String test3 = "ATGCTGCTGCTGATCTAA";
        String test4 = "ATGCTGACACTCTCTAA";
        String test5 = "ATGCTGCTGTAAGTCCCTGTAA";
        sr.add(test1);
        sr.add(test2);
        sr.add(test3);
        sr.add(test4);
        sr.add(test5);
        processGenes(sr);
        */
    }
}
