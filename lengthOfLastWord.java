class Solution {
   public int lengthOfLastWord(String str) {
      int i = str.length()-1;
      int lastIndex;
      while(str.charAt(i) == ' ')
         i--;
      lastIndex = i;
      while(i > -1 && str.charAt(i) != ' ')
         i--;
      return lastIndex - i;
   }
}





