import java.util.Arrays;

public class DistinctSubsequences {

    // TLE
    static int solve(String s , String t , int n , int m){

        if(m <= 0){
            return 1;
        }
        if(n <= 0){
            return 0;
        }

        int take = 0;

        if(s.charAt(n-1) == t.charAt(m-1)){
            take = solve(s,t, n-1, m-1);
        }

        int skip = solve(s,t, n -1 , m);

        return take + skip;
    }


    static int dp[][];

    //TLE for large value

    static int solveWithMemo(String s , String t , int n , int m){

        if(m <= 0){
            return 1;
        }
        if(n <= 0){
            return 0;
        }

        if(dp[n][m] != 0){
            return dp[n][m];
        }

        int take = 0;

        if(s.charAt(n-1) == t.charAt(m-1)){
            take = solveWithMemo(s,t, n-1, m-1);
        }

        int skip = solveWithMemo(s,t, n -1 , m);

        return dp[n][m] = take + skip;
    }

    // NO TLE 
    static int solveWithBottomUp(String s , String t, int n , int m){
        int dp[][] =  new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1; j <= m ;j++){
               int take = 0;


        if(s.charAt(i-1) == t.charAt(j-1)){
            take = dp[i -1][j-1];
        }

        int skip = dp[i -1][j];

         dp[i][j] = take + skip;
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {

        String s = "rabbbit";
        String t = "rabbit";

        int n = s.length();
        int m = t.length();

        if(m > n){
            System.out.println('0');
            return;
        }

        dp = new int[n+1][m+1];

        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , 0);
        }

        int ans =  solveWithBottomUp(s,t,n , m);
        System.out.println(ans);
    }
}
