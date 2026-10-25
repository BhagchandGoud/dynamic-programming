import java.util.Arrays;

public class MaxSumIncreasingSubsequencOfLength_K {
    static int n;

    // recursion would be pass on gfg

    static int solve(int []nums , int i , int prevIdx , int k){
        if(i >= n){
            return k == 0 ? 0 : Integer.MIN_VALUE;
        }
        
        int take = Integer.MIN_VALUE;

        if(prevIdx == -1 || nums[prevIdx] <= nums[i]){
            take = solve(nums, i+1, i, k-1);
            if(take != Integer.MIN_VALUE){
                take += nums[i];
            }
        }

        int skip = solve(nums, i+1, prevIdx, k);

        return Math.max(take , skip);
    }

    static int dp[][][];

    static int solveWithMemo(int []nums , int i , int prevIdx , int k){
       if(k == 0){
            return 0;
        }
        if(i >= n){
            return Integer.MIN_VALUE;
        }

        if(dp[i][prevIdx+1][k] != -2){
            return dp[i][prevIdx+1][k];
        }
        
        int take = Integer.MIN_VALUE;

        if(prevIdx == -1 || nums[prevIdx] <= nums[i]){
            take = solveWithMemo(nums, i+1, i, k-1);
            if(take != Integer.MIN_VALUE){
                take += nums[i];
            }
        }

        int skip = solveWithMemo(nums, i+1, prevIdx, k);

        return dp[i][prevIdx+1][k] = Math.max(take , skip);
    }

    public static void main(String[] args) {
        int []nums = {10, 5};
        n = nums.length;
        int k = 2;

        dp = new int[n+1][n+1][k+1];

        for(int i = 0 ; i <= n ; i++){
            for(int j = 0 ; j <= n  ; j++){
                Arrays.fill(dp[i][j] , -2);
            }
        }

        int ans1 = solve(nums, 0, -1, k);
        int ans2 = solveWithMemo(nums, 0, -1, k);

        System.out.println(ans1 == Integer.MIN_VALUE ? -1 : ans1);
        System.out.println(ans2 == Integer.MIN_VALUE ? -1 : ans2);
    }
}
