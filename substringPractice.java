package StringsClass1;


/**
 * Practicing methods of finding strings in strings (substrings).
 * 
 * @author Allana Bailey 
 * @version 1
 */
public class substringPractice {
    //does string a occur two or more times in string b?
    public boolean twoOccurrences(String stringa, String stringb) {
        int count = 0;
        int fromIndex = 0;
        while((fromIndex = stringb.indexOf(stringa, fromIndex)) != -1) {
            count ++;
            fromIndex++;
        }
        if(count >= 2) {
            return true;
        }
        return false;
    }
    public void testing() {
        String stringa1 = "by";
        String stringb1 = "A story by Abby Long";
        String stringa2 = "a";
        String stringb2 = "banana";
        String stringa3 = "atg";
        String stringb3 = "ctgtatgta";
        twoOccurrences(stringa1, stringb1);
        System.out.println("Does " + stringa1 + " appear in " + stringb1 + " at least twice?");
        System.out.println(twoOccurrences(stringa1, stringb1));
        twoOccurrences(stringa2, stringb2);
        System.out.println("Does " + stringa2 + " appear in " + stringb2 + " at least twice?");
        System.out.println(twoOccurrences(stringa2, stringb2));
        twoOccurrences(stringa3, stringb3);
        System.out.println("Does " + stringa3 + " appear in " + stringb3 + " at least twice?");
        System.out.println(twoOccurrences(stringa3, stringb3));
    }
    //print the part of the string following a given substring.
    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if(index == -1) {
            System.out.println(stringa + " does not appear in " + stringb);
            return stringb;
        } else {
            System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + stringb.substring(index + stringa.length())); 
            return stringb.substring(index + stringa.length());
        }
    }
}
