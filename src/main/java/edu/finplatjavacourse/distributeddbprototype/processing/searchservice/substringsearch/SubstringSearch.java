package edu.finplatjavacourse.distributeddbprototype.processing.searchservice.substringsearch;

public class SubstringSearch {
    private SubstringSearch(){}
    public static Boolean KMPSearch(String findWord, String hotelName) {
        int findWordLenght = findWord.length();
        int hotelNameLenght = hotelName.length();

        int longPrefixSuffix[] = new int[findWordLenght];
        int j = 0;


        computeLongPrefixSuffixArray(findWord, findWordLenght, longPrefixSuffix);

        int i = 0;
        while (i < hotelNameLenght) {
            if (findWord.charAt(j) == hotelName.charAt(i)) {
                j++;
                i++;
            }
            if (j == findWordLenght) {
                return true;
            } else if (i < hotelNameLenght && findWord.charAt(j) != hotelName.charAt(i)) {

                if (j != 0)
                    j = longPrefixSuffix[j - 1];
                else
                    i = i + 1;
            }
        }
        return false;
    }
    private static void computeLongPrefixSuffixArray(String findWord, int findWordLenght, int longPrefixSuffix[]) {

        int len = 0;
        int i = 1;
        longPrefixSuffix[0] = 0;
        while (i < findWordLenght) {
            if (findWord.charAt(i) == findWord.charAt(len)) {
                len++;
                longPrefixSuffix[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = longPrefixSuffix[len - 1];
                } else {
                    longPrefixSuffix[i] = len;
                    i++;
                }
            }
        }
    }
}
