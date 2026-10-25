import java.util.Arrays;

public class TargetSum {

    static int n;
    static int offset;
    
    static int solve(int nums[] , int i , int sum , int target){
        if (i >= n) {
            return sum == target ? 1 : 0;
        }

        int add  = solve(nums, i+1, sum + nums[i] , target);
        int sub = solve(nums, i+1, sum - nums[i] , target);

        return add + sub;
    }

    static int dp[][];


     static int solveWithMemo(int nums[], int i, int sum, int target) {

        if (i == n) {
            return sum == target ? 1 : 0;
        }

        if (dp[i][sum + offset] != -1) {
            return dp[i][sum + offset];
        }

        int add = solveWithMemo(nums, i + 1, sum + nums[i], target);

        int sub = solveWithMemo(nums, i + 1, sum - nums[i], target);

        return dp[i][sum + offset] = add + sub;
    }

    public static void main(String[] args) {

        int nums[] = {1,1,1,1,1};
        int target = 3;

        n = nums.length;

        int total = 0;
        for(int x : nums){
            total += x;
        }

        offset = total;

        dp = new int[n][2 * total + 1];

        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }

        int res = solveWithMemo(nums, 0, 0, target);

        System.out.println(res);
}
}