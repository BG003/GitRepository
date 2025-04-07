public class IndexOfFirstOccurrenceInString {
    public int strStr(String haystack, String needle) {
        char charToCompare = needle.charAt(0);
        int subIndex = needle.length();
        int superIndex = haystack.length() - subIndex + 1;
        for(int i=0 ; i < superIndex ; i++) {
            if(haystack.charAt(i) == charToCompare) {
                int returnValue = i;
                int j = 1;
                for(i++ ; j < subIndex && haystack.charAt(i) == needle.charAt(j) ; j++,i++);
                if(j == subIndex)
                    return returnValue;
                else
                    i = returnValue;
            }
        }
        return -1;
    }
}