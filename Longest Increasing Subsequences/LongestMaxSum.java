import java.util.Arrays;

public class LongestMaxSum {
    

    static int n;
    static int solve(int[]nums , int i  , int prevIdx){
        if(i >= n){
            return 0;
        }
        int take = 0;
        if(prevIdx == -1 || nums[prevIdx] < nums[i]){
            take = nums[i] + solve(nums, i+1, i);
        }
        int skip = solve(nums, i+1, prevIdx);

        return Math.max(take , skip);
    }

    // solve using memoization

    static int dp[][];
    static int solveWithMemo(int[]nums , int i  , int prevIdx){
        if(i >= n){
            return 0;
        }
        if(dp[i][prevIdx+1] != Integer.MIN_VALUE){
            return dp[i][prevIdx+1];
        }
        int take = 0;
        if(prevIdx == -1 || nums[prevIdx] < nums[i]){
            take = nums[i] + solveWithMemo(nums, i+1, i);
        }
        int skip = solveWithMemo(nums, i+1, prevIdx);

        return dp[i][prevIdx+1] = Math.max(take , skip);
    }

    public static void main(String[] args) {
        int[]nums = {4, 1, 2, 4};
        n = nums.length;

        int ans1 = solve(nums, 0, -1);

        dp = new int[n+1][n+1];
        
        for(int i = 0 ; i<= n ; i++){
            Arrays.fill(dp[i] , Integer.MIN_VALUE);
        }

        int ans2 = solveWithMemo(nums, 0, -1);

        System.out.println(ans1);
        System.out.println(ans2);
    }
}
