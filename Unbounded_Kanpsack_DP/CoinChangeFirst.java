import java.util.Arrays;

public class CoinChangeFirst {
    static int dp[][];
    static int n;

    //solve using recursion
    static int solve(int []coins , int i , int amount){
        if(amount == 0){
            return 0;
        }
        if(i >= n){
            return (int)1e9;
        }
        
        int skip = solve(coins , i+1 , amount);
        int take =  (int)1e9;;
        if(coins[i] <=  amount){
            take = 1 + solve(coins , i , amount-coins[i]);
        }
        return Math.min(skip , take);
    }


    // solve using memoization
    static int solveWithMemo(int []coins , int i , int amount){
        if(amount == 0){
            return 0;
        }
        if(i >= n){
            return (int)1e9;
        }
        if(dp[i][amount] != -1){
            return dp[i][amount];
        }
        int skip = solveWithMemo(coins , i+1 , amount);
        int take =  (int)1e9;;
        if(coins[i] <=  amount){
            take = 1 + solveWithMemo(coins , i , amount-coins[i]);
        }
        return dp[i][amount] = Math.min(skip , take);
    }


    public static void main(String[] args) {
        int coins[] = {1,2,5};
        int amount = 11;

        n = coins.length;
        dp = new int[n][amount+1];
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        int ans = solve(coins , 0 , amount);
        System.out.println((ans >=  (int)1e9) ? -1 : ans);
    }
}
