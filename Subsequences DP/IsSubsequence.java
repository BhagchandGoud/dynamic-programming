import java.util.Arrays;

public class IsSubsequence {
    

    static int solve(String s1 , String s2 , int n , int m){
        if(n <= 0 || m <= 0){
            return 0;
        }

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return 1 + solve(s1, s2 , n-1 , m-1);
        }

        return Math.max(solve(s1, s2, n-1, m) ,  solve(s1, s2, n, m-1));
    }


    static int dp[][];

    static int solveWithMemo(String s1 , String s2 , int n , int m){
        if(n <= 0 || m <= 0){
            return 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = 1 + solveWithMemo(s1, s2 , n-1 , m-1);
        }

        return dp[n][m] = Math.max(solveWithMemo(s1, s2, n-1, m) ,  solveWithMemo(s1, s2, n, m-1));
    }


    static int solveWithBottomUp(String s1 , String s2 , int n , int m){
        
        int dp[][] = new int[n+1][m+1];

        for(int i = 1 ; i<= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] =  1 + dp[i-1][j-1];
                }
                else{
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "ahbgdc";

        int n = s1.length();
        int m = s2.length();

        dp = new int[n+1][m+1];

        for(int i = 0 ; i<= n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        int ans = solveWithBottomUp(s1, s2, n, m);

        System.out.println(ans == n ? true : false);

    }
}
