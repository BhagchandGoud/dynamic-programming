import java.util.Arrays;

public class LongestPalindromicSubsequence {


    static int lcs(String s1 ,String s2 , int n , int m){
        if(n == 0 || m == 0){
            return 0;
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return 1+lcs(s1,s2 , n-1, m-1);
        }
        return Math.max(lcs(s1,s2 ,n-1, m) , lcs(s1,s2 ,n , m-1));
    }

    static int dp[][];
    static int lcsWthMemo(String s1 ,String s2 , int n , int m){
        if(n == 0 || m == 0){
            return 0;
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] =  1+lcsWthMemo(s1,s2 , n-1, m-1);
        }
        return dp[n][m] =  Math.max(lcsWthMemo(s1,s2 ,n-1, m) , lcsWthMemo(s1,s2 ,n , m-1));
    }

    public static void main(String[] args) {
        String s1 = "bbbab";
        StringBuilder s2=  new StringBuilder(s1);
        dp = new int[s1.length()+1][s2.length()+1];
        for(int i = 0 ; i<=s1.length() ; i++){
            Arrays.fill(dp[i] , -1);
        }

        int ans  =  lcs(s1,s2.reverse().toString() , s1.length() , s2.length());
        System.out.println(ans);
    }
}
