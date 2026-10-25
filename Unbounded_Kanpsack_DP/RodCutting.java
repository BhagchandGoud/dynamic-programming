// package Unbounded_Kanpsack_DP;

import java.util.Arrays;

public class RodCutting {
    static int n;
    
    static int solve(int price[] , int i , int []cost , int rod){
        if(i >= n){
            return 0;
        }
        
        
        
        int take = 0;
        
        if(cost[i] <= rod){
            take = price[i] + solve(price , i , cost , rod-cost[i]);
        }
        
        int skip = solve(price  , i+1 , cost , rod);
        
        return Math.max(take , skip);
    }



    // solve using memoization

    static int dp[][];

    static int solveWithMemo(int price[] , int i , int []cost , int rod){
        if(i >= n){
            return 0;
        }
        
        if(dp[i][rod] != -1){
            return dp[i][rod];
        }
        
        int take = 0;
        
        if(cost[i] <= rod){
            take = price[i] + solveWithMemo(price , i , cost , rod-cost[i]);
        }
        
        int skip = solveWithMemo(price  , i+1 , cost , rod);
        
        return dp[i][rod] = Math.max(take , skip);
    }


    public static void main(String[] args) {

        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};
         n = price.length;
        
        int cost[] = new int[n];
        
        for(int i = 0 ; i< n ; i++){
            cost[i] = i+1;
        }

        // initialize dp

        dp = new int[n+1][n+1];

        for(int i =  0 ; i<= n ; i++){
            Arrays.fill(dp[i], -1);
        }
        
        int ans  = solveWithMemo(price , 0 , cost , n);
        System.out.println(ans);
    }
}
