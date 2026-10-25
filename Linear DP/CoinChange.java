
public class CoinChange{
    static int n;
    // this is knapsack dp solution
    static int solve(int nums[] , int i , int amount){
        if(amount == 0){
            return 0;
        }
        if(amount < 0 || i >= n){
            return (int)1e9;
        }

        int take = 1 + solve(nums , 0, amount - nums[i]);
        int skip = solve(nums , i+1 , amount);
        return Math.min(take , skip);
    }

    // this is linear dp solution
   
    int solveUsingWithoutMemo(int nums[] , int amount){
        if(amount == 0){
            return 0;
        }
        if(amount < 0){
            return (int)1e9;
        }
        int minCoins = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
         int nextCoins = solveUsingWithoutMemo(nums ,  amount - nums[i]);
         if(nextCoins != Integer.MAX_VALUE){
           int currentCoins =  nextCoins + 1;
           minCoins = Math.min(currentCoins , minCoins);
         }
        }
        return minCoins;
    }

    Integer dp[];
    int solveUsingWithMemo(int nums[] , int amount){
        if(amount == 0){
            return 0;
        }
        if(amount < 0){
            return Integer.MAX_VALUE;
        }
        if(dp[amount] != null){
            return dp[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
         int nextCoins = solveUsingWithMemo(nums ,  amount - nums[i]);
         if(nextCoins != Integer.MAX_VALUE){
           int currentCoins =  nextCoins + 1;
           minCoins = Math.min(currentCoins , minCoins);
         }
        }
        return dp[amount] =  minCoins;
    }
    
    static int coinChange(int[] coins, int amount) {
        n = coins.length;
        int ans = solve(coins , 0, amount);
        return ans  == (int)1e9 ? -1 : ans;
    }
}