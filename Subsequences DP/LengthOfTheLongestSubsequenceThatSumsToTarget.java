import java.util.*;

public class LengthOfTheLongestSubsequenceThatSumsToTarget {
    static int n;

    static int solve(int[] nums, int i, int target) {
        if (target < 0) {
            return Integer.MIN_VALUE;
        }
        if (i >= n) {
            return target == 0 ? 0 : Integer.MIN_VALUE;
        }
        int currTake = solve(nums, i + 1, target - nums[i]);

        if (currTake != Integer.MIN_VALUE) {
            currTake += 1;
        }
        int currSkip = solve(nums, i + 1, target);

        return Math.max(currTake, currSkip);

    }

    static int dp[][];
    static int solveWithMemo(int[] nums, int i, int target) {
        if (target < 0) {
            return Integer.MIN_VALUE;
        }
        if (i >= n) {
            return target == 0 ? 0 : Integer.MIN_VALUE;
        }

        if(dp[i][target] != -1){
            return dp[i][target];
        }
        int currTake = solveWithMemo(nums, i + 1, target - nums[i]);

        if (currTake != Integer.MIN_VALUE) {
            currTake += 1;
        }
        int currSkip = solveWithMemo(nums, i + 1, target);

        return dp[i][target] = Math.max(currTake, currSkip);

    }

    static int solveWithBottomUp(int nums[] , int target){
        int dp[][] = new int[n+1][target+1];

        for(int i = 0 ; i <= target ; i++){
            dp[n][i] = i == 0 ? 0 : Integer.MIN_VALUE;
        }

        for(int i = n-1 ; i >= 0 ; i--){
            for(int j = 0 ; j <= target ; j++){

                int currTake = Integer.MIN_VALUE;
                if(j >= nums[i]){
                 currTake  = dp[i+1][j-nums[i]];
                if(currTake != Integer.MIN_VALUE){
                    currTake += 1;
                }
            }
                int currSkip = dp[i+1][j];
                dp[i][j] = Math.max(currSkip , currTake);

                
            }
        }

        return dp[0][target] == Integer.MIN_VALUE ? -1 : dp[0][target];
    }


    static int solveWithSpcaeOptimization(int [] nums, int target) {

        int next[] = new int[target + 1];

        for (int i = 0; i <= target; i++) {
            next[i] = i == 0 ? 0 : Integer.MIN_VALUE;
        }

        for (int i = n - 1; i >= 0; i--) {

            int curr[] = new int[target + 1];

            for (int j = 0; j <= target; j++) {
                

                int currTake = Integer.MIN_VALUE;

                if (j >= nums[i]) {

                    currTake = next[j - nums[i]];

                    if (currTake != Integer.MIN_VALUE) {
                        currTake += 1;
                    }

                }
                int currSkip = next[j];

                curr[j] = Math.max(currSkip, currTake);
                
            }

            next = curr;
            
        }

        return next[target] == Integer.MIN_VALUE ? -1 : next[target];
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5};
        n = nums.length;

        int target = 9;

        dp = new int[n][target+1];

        for(int i = 0 ; i <n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        int ans = solveWithSpcaeOptimization(nums  , target);
        System.out.println(ans == Integer.MIN_VALUE ? -1 : ans);
    }
}
