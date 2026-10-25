public class MakeStringASubsequenceUsingCyclicIncrements {
    static int n , m;

    static char nextChar(char ch){
        return (char)((ch - 'a' + 1) % 26 + 'a');
    }

    static boolean solve(String s1 , String s2 , int i , int j){

        if(j >= m) {
            return true; 
        } 
        if(i >= n){
            return false;
        }
        
         boolean take = false;
        if(s1.charAt(i) == s2.charAt(j) || s2.charAt(j) == nextChar(s1.charAt(i))){
            take =   solve(s1,s2,i+1 , j+1);
        }
        boolean skip = solve(s1,s2, i+1 , j);

        return take || skip;
    }


    // using two pointer approach

    static  boolean canMakeSubsequence(String str1, String str2) {

        int j = 0;

        for (int i = 0; i < str1.length() && j < str2.length(); i++) {

            char a = str1.charAt(i);
            char b = str2.charAt(j);

            if (a == b || nextChar(a) == b) {
                j++;
            }
        }

        return j == str2.length();
    }


    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "ad";

        boolean ans = canMakeSubsequence(s1,s2);

        System.out.println(ans);
    }

}
