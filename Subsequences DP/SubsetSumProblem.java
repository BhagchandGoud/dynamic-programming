import java.util.Arrays;

public class SubsetSumProblem {
    
    static int dp[][];
    static int n;

    static int solve(int []arr , int i  , int sum){
        if(sum == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }
        
        int skip = solve(arr , i+1  , sum);
        
        int take = 0;
        if(arr[i] <= sum){
             take =  solve(arr , i+1 , sum-arr[i]);
        }
        return (take == 1 || skip == 1) ? 1 : 0;
    }
    
    static int solveWithMemo(int []arr , int i  , int sum){
        if(sum == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }
        if(dp[i][sum] != -1){
            return dp[i][sum];
        }
        
        int skip = solveWithMemo(arr , i+1  , sum);
        
        int take = 0;
        if(arr[i] <= sum){
             take =  solveWithMemo(arr , i+1 , sum-arr[i]);
        }
        return dp[i][sum] = (take == 1 || skip == 1) ? 1 : 0;
    }


    static int solveWithBottomUp(int []nums , int sum){

    int[][] dp = new int[n + 1][sum + 1];

    dp[n][0] = 1;

    for(int i = n - 1 ; i >= 0 ; i--){

        for(int j = 0 ; j <= sum ; j++){

            int take = 0;

            if(j >= nums[i]){
                take = dp[i + 1][j - nums[i]];
            }

            int skip = dp[i + 1][j];

            dp[i][j] = (take == 1 || skip == 1) ? 1 : 0;
        }
    }

    return dp[0][sum];
}


static int solveWithSpaceOptimization(int []nums , int sum){

    int []next = new int[sum+1];

    next[0]  = 1;

    for(int i = n - 1 ; i >= 0 ; i--){
        int curr[] = new int[sum+1];
        for(int j = 0 ; j <= sum ; j++){

            int take = 0;

            if(j >= nums[i]){
                take = next[j - nums[i]];
            }

            int skip = next[j];

            curr[j] = (take == 1 || skip == 1) ? 1 : 0;
        }

        next = curr;
    }

    return next[sum];
}
    public static void main(String[] args) {
        int arr[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        n = arr.length;
        dp = new int[n+1][sum+1];
        
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        boolean ans =  solveWithSpaceOptimization(arr , sum) == 1;
        System.out.println(ans);
    }
}
