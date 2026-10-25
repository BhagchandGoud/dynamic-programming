import java.util.Arrays;

public class  Knapsack_0_1_Problem {
    static int n;
    
    static int solve(int W , int val[] , int wt[] , int i){
        if(i >= n){
            return 0;
        }
        
        int take = 0;
        
        if(wt[i] <= W){
            take = val[i] + solve(W-wt[i] , val , wt , i+1);
        }
        int skip = solve(W , val , wt , i+1);
        
        return Math.max(take , skip);
    }

    static int dp[][];

    static int solveWithMemo(int W , int val[] , int wt[] , int i){
        if(i >= n || W == 0){
            return 0;
        }

        if(dp[i][W] != -1){
            return dp[i][W];
        }
        
        int take = 0;
        
        if(wt[i] <= W){
            take = val[i] + solveWithMemo(W-wt[i] , val , wt , i+1);
        }
        int skip = solveWithMemo(W , val , wt , i+1);
        
        return dp[i][W] =  Math.max(take , skip);
    }

    public static void main(String[] args) {
        int W = 4, val[] = {1, 2, 3}, wt[] = {4, 5, 1};
        n = wt.length;

        dp = new int[n+1][W+1];
        for(int i = 0 ; i <= n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        int ans =  solveWithMemo(W , val , wt , 0);
        System.out.println(ans);
    }
}
