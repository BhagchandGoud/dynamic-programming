import java.util.Arrays;

public class CombinationSum4{
    static int n;

    // TLE
    static int solve(int []nums , int i , int target){
        if(i >= n){
            return 0;
        }

        if(target == 0){
            return 1;
        }
        int take = nums[i] <= target ?  solve(nums , 0 , target-nums[i]) : 0;
        int skip = solve(nums , i+1 , target);

        return take + skip;
    }

    static int dp[][];
    static int solveWithMemo(int []nums , int i , int target){
        if(i >= n){
            return 0;
        }
        if(dp[i][target] != -1){
            return dp[i][target];
        }

        if(target == 0){
            return 1;
        }
        int take = nums[i] <= target ?  solveWithMemo(nums , 0 , target-nums[i]) : 0;
        int skip = solveWithMemo(nums , i+1 , target);

        return dp[i][target] = take + skip;
    }
    static int combinationSum4(int[] nums, int target) {
        n = nums.length;

        dp = new int[n+1][target+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i] , -1);
        }

        return solveWithMemo(nums , 0 , target);
    }

    public static void main(String[] args) {
        int []nums = {4 ,4,5,2,1};
        int target = 3;

        int ans = combinationSum4(nums , target);
        System.out.println(ans);
    }
}