public class PerfectSumProblem {
    static int n;
    
    static int solve(int nums[] , int i  , int target){
        if(i >= n){
            return 0;
        }

        if(target == 0){
            return 0;
        }

        int take = 0;

        if(nums[i] <= target){
            take = 1 + solve(nums, i+1, target-nums[i]);
        }
        int skip = solve(nums, i+1, target);

        return Math.max(take , skip);
    }

    static int dp[][];
	
	static int solveWithMemo(int nums[], int i, int target) {
		
		if (i == n) {
			return target == 0 ? 1 : 0;
		}
		
		if (dp[i][target] != -1) {
			return dp[i][target];
		}
		
		int take = 0;
		
		if (nums[i] <= target) {
			take = solve(nums, i + 1, target - nums[i]);
		}
		
		int skip = solve(nums, i + 1, target);
		
		return dp[i][target] = take + skip;
	}
	

    public static void main(String[] args) {
        int nums[] = {5, 7, 8};

        n = nums.length;

        int target = 3;

        int ans = solve(nums, 0, target);
        System.out.println(ans);
    }
}
