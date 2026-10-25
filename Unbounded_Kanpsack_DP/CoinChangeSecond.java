// package Unbounded_Kanpsack_DP;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChangeSecond {
    static int n;
    static final int MOD = 1000000007;

    static long solve(int amount , int []coins, int i){
        if(amount == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }

        long take = 0;

        if(coins[i] <= amount){
            take = solve(amount-coins[i] , coins , i);
        }

        long skip = solve(amount ,coins , i+1);

        return take + skip;
    }

    static long dp[][];

    static long solveWithMemo(int amount , int []coins, int i){
        if(amount == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }

        if(dp[i][amount] != -1){
            return dp[i][amount];
        }

        long take = 0;

        if(coins[i] <= amount){
            take = solveWithMemo(amount-coins[i] , coins , i);
        }

        long skip = solveWithMemo(amount ,coins , i+1);

        return dp[i][amount] = (take + skip)%MOD;
    }

    static long solveWithOptimization(int amount, int[] coins) {

    long dp[] = new long[amount + 1];

    dp[0] = 1;

    for (int coin : coins) {
        for (int currAmount = coin; currAmount <= amount; currAmount++) {
            dp[currAmount] += dp[currAmount - coin]%MOD;
        }
    }

    return dp[amount];
}


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int amount = sc.nextInt();

        int coins[] = new int[n];

        for(int i = 0 ; i< n; i++){
            coins[i] = sc.nextInt();
        }
    
        //comment the dp initialization when solve cses problem
        dp = new long[n+1][amount+1];

        for(int i = 0 ; i <= n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        long ans = solveWithMemo(amount, coins , 0 )%MOD;
        System.out.println(ans);

        sc.close();
    }
}
