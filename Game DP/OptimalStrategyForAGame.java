public class OptimalStrategyForAGame {
	
	int n;
	Integer dp[][][];
	
	int solve(int []nums, int i, int j, int person) {
		if (j < i) {
			return 0;
		}
		
		if (dp[i][j][person] != null) {
			return dp[i][j][person];
		}
		
		if (person == 0) {
			int take_first = solve(nums, i + 1, j, 1) + nums[i];
			int take_last = solve(nums, i, j - 1, 1) + nums[j];
			return dp[i][j][person] = Math.max(take_first, take_last);
		} else {
			int take_first = solve(nums, i + 1, j, 0);
			int take_last = solve(nums, i, j - 1, 0);
			return dp[i][j][person] = Math.min(take_first, take_last);
		}
	}
	public int maximumAmount(int arr[]) {
		n = arr.length;
		
		dp = new Integer[n+1][n+1][2];
		
		return solve(arr, 0, n - 1, 0);
	}
}
