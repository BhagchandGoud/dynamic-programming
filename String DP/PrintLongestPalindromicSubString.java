public class PrintLongestPalindromicSubString {
    static boolean isPalindrome(String s , int i , int j){
        while(i <= j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            else{
                i++;
                j--;
            }
        }
        return true;
    }
    static String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = 0;
        int start = -1 , end = -1;

        for(int i = 0 ; i< n ; i++){
            for(int j = i ; j < n; j++){
                if(isPalindrome(s, i , j)){
                    int windowLength = j - i + 1;
                   
                   if(windowLength > maxLen){
                       maxLen= windowLength;
                       start = i;
                       end = j;
                   }
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for(int i = start ; i <= end ; i++){
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "aasffbbabb";
        String ans  = longestPalindrome(s);
        System.out.println(ans);
    }
}
