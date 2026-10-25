import java.util.*;

public class CountPalindromicSubstring {
    
    static boolean solve(String s , int i , int j){
        if(i >= j){
            return true;
        }
        if(s.charAt(i) == s.charAt(j)){
            return solve(s, i+1, j-1);
        }
        return false;
    }


    // solve using memoization
    static boolean dp[][];

    static boolean solveWithMemo(String s , int i , int j){
        if(i >= j){
            return true;
        }
        if(dp[i][j] != false){
            return dp[i][j];
        }
        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] =  solveWithMemo(s, i+1, j-1);
        }
        return dp[i][j] = false;
    }

    // solve using bottom up
    static int countSubstrings(String s) {
        int n = s.length();

        int count = 0;

        dp  = new boolean[n+1][n+1];
        for(int i = 0 ; i <= n ;i++){
            Arrays.fill(dp[i] , false);
        }

        for(int i = 0 ; i <n ; i++){
            for(int j = i ; j <n ; j++){
                if(solve(s, i, j)){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String s = "racecar";
        int n = s.length();

        int count = 0;

        for(int i = 0 ; i <n ; i++){
            for(int j = i ; j <n ; j++){
                if(solve(s, i, j)){
                    count++;
                }
            }
        }

        int ansFromMemo = countSubstrings(s);
        System.out.println(ansFromMemo);

        System.out.println(count);
    }
}
