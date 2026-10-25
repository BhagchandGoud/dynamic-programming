// package Unbounded_Kanpsack_DP;
import java.util.*;

public class KnapsackWithDuplicateItems {
    
    static int n;
    
    static int solve(int val[] , int wt[] , int capacity , int i){
        if(i >= n){
            return 0;
        }
        int take = 0;
        
        if(wt[i] <= capacity){
            take = solve(val ,wt, capacity-wt[i] , i) + val[i];
        }
        
        int skip = solve(val , wt , capacity , i+1);
        return Math.max(take , skip);
    }


    static int dp[][];
    
    
    static int solveWithMemo(int val[] , int wt[] , int capacity , int i){
        if(i >= n){
            return 0;
        }
        
        if(dp[i][capacity] != -1){
            return dp[i][capacity];
        }
        
        int take = 0;
        
        if(wt[i] <= capacity){
            take = solveWithMemo(val ,wt, capacity-wt[i] , i) + val[i];
        }
        
        int skip = solveWithMemo(val , wt , capacity , i+1);
        return dp[i][capacity] = Math.max(take , skip);
    }

    public static void main(String[] args) {
        int val[] = {1,1};
        int wt[] = {2,1};

        int capacity = 3;

        n = wt.length;

        dp = new int[n][capacity+1];
        
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        
        int ans =  solveWithMemo(val , wt , capacity , 0);
        System.out.println(ans);
    }
}
