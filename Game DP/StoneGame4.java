import java.util.*;
public class StoneGame4{
    static boolean dp[];
    static boolean solve(int n) {
        if (n == 0) {
            return false;
        }

        if(dp[n] != false){
            return dp[n];
        }
        
       for(int k = 1 ; k*k <= n ; k++){
          if(!solve(n - (k * k))){
            return dp[n] =  true;
          }
       }
        return dp[n] = false;

    }

    static boolean winnerSquareGame(int n) {
        dp = new boolean[n+1];
        Arrays.fill(dp , false);
        return solve(n);
    }

    public static void main(String[] args) {
        int n = 2;

        boolean x = winnerSquareGame(n);
        System.out.println(x);
    }
}