import java.util.*;

public class ChooseStudents {
 
    static int solve(int n , int k , int i){
        if(i >= n){

            return k == 0 ? 1 : 0;
        }
        
    
        int take = 0;
        if(k > 0){
            take =  solve(n, k-1, i+1);
        }
        int skip = solve(n, k, i+1);
        return take + skip;
    }

    static int dp[][];
	static int solveWithMemo(int n , int k , int i){
        if(i >= n){

            return k == 0 ? 1 : 0;
        }

        if(dp[i][k] != -1){
            return dp[i][k];
        }
        
        int take = 0;
        if(k > 0){
            take =  solve(n, k-1, i+1);
        }
        int skip = solve(n, k, i+1);
        return dp[i][k] =  take + skip;
    }



    static int solveWithBottomUp(int n , int k){
        int[][] dp = new int[n + 1][k + 1];
          dp[n][0] = 1;
        for(int i = n-1 ; i >= 0 ; i--){
            for(int j = 0 ; j <= k  ; j++){

                 int take = 0;
                if(j > 0){
                    take = dp[i+1][j-1];
                }
                int skip = dp[i+1][j];

                dp[i][j] = take + skip;
            }
            
        }
        
        return dp[0][k];
    }


    static int solveWithSpaceOptimization(int n , int k){
        int[] next = new int[k + 1];
          next[0] = 1;
        for(int i = n-1 ; i >= 0 ; i--){
            int[] curr = new int[k + 1];
            for(int j = 0 ; j <= k  ; j++){
                
                 int take = 0;
                if(j > 0){
                    take = next[j-1];
                }
                int skip = next[j];

                curr[j] = take + skip;
            }
            next = curr;
        }
        return next[k];
    }


    public static void main(String[] args) {
        int n  = 3;
        int k = 2;

        dp = new int[n][k+1];

        for(int i = 0 ; i<n;i++){
            Arrays.fill(dp[i] , -1);
        }
   
        int ans = solveWithSpaceOptimization(n , k );
 
        System.out.println(ans);
        
    }
}
