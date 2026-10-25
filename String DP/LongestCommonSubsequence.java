public class LongestCommonSubsequence {
    
    // solve using recursion

    static int solveUsingRecursion(String s1 , String s2 , int n , int m){
        if(m == 0 || n == 0){
            return 0;
        }

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return 1 + solveUsingRecursion(s1, s2, n-1, m-1);
        }
        int moveIn1 = solveUsingRecursion(s1, s2, n-1, m);
        int moveIn2 = solveUsingRecursion(s1, s2, n, m - 1);
        return Math.max(moveIn1 , moveIn2);
    }


    // solve using top up

    static Integer dp[][];
    static int solveUsingMemoization(String s1 , String s2 , int n , int m){
        if(n == 0 || m == 0){
            return 0;
        }
        if(dp[n][m] != null){
            return dp[n][m];
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = 1 + solveUsingMemoization(s1,s2, n-1 , m-1);
        }
        return dp[n][m] = Math.max(solveUsingMemoization(s1,s2,n-1, m) , solveUsingMemoization(s1,s2, n , m-1));
    }


    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        int n = s1.length() , m  = s2.length();

        int ansFromRecurion = solveUsingRecursion(s1, s2, n, m);

        dp = new Integer[n+1][m+1];
        
        int ansFromMemoization = solveUsingMemoization(s1,s2, n , m);
        System.out.println(ansFromRecurion);
        System.out.println(ansFromMemoization);
    }
}
