import java.util.Arrays;

public class LongestSubsequenceWithDecreasingAdjacentDifference {
    int n;

    // using recursion - > TLE
    int solve(int[] nums, int prevIdx, int i, int prevDiff) {
        if (i >= n) {
            return 0;
        }

        int take = 0;

        if (prevIdx == -1) {
            take = 1 + solve(nums, i, i + 1, Integer.MAX_VALUE);
        } else {
            int diff = Math.abs(nums[i] - nums[prevIdx]);
            if (diff <= prevDiff) {
                take  = 1 + solve(nums, i, i + 1,  diff);
            }
        }

        int skip = solve(nums, prevIdx, i + 1, prevDiff);
        return Math.max(take , skip);
    }


    //using memoization - > MLE

    int max;

    int dp[][][];

    int solveWithMemo(int[] nums, int prevIdx, int i, int prevDiff) {
        if (i >= n) {
            return 0;
        }

        if(dp[i][prevIdx+1][prevDiff] != -1){
            return dp[i][prevIdx+1][prevDiff];
        }

        int take = 0;

        if (prevIdx == -1) {
            take = 1 + solveWithMemo(nums, i, i + 1, max);
        } else {
            int diff = Math.abs(nums[i] - nums[prevIdx]);
            if (diff <= prevDiff) {
                take  = 1 + solveWithMemo(nums, i, i + 1,  diff);
            }
        }

        int skip = solveWithMemo(nums, prevIdx, i + 1, prevDiff);
        return dp[i][prevIdx+1][prevDiff] =  Math.max(take , skip);
    }

    public int longestSubsequence(int[] nums) {
        n = nums.length;

        max = 0;

        for(int val : nums){
            max = Math.max(val , max);
        }

        dp = new int[n+1][n+1][max+1];

        for(int i = 0 ; i <= n ; i++){
            for(int j = 0 ; j <= n ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        return solveWithMemo(nums, -1, 0, Integer.MAX_VALUE);
    }
}
