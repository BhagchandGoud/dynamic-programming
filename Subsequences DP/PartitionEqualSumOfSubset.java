public class PartitionEqualSumOfSubset {
    
    static int n;
    static Boolean dp[][];

    // using recursion
    static boolean solve(int[] nums, int i, int target) {

        if (i >= n || target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        
        boolean currTake = false;
        if (nums[i] <= target) {
            currTake = solve(nums, i + 1, target - nums[i]);
        }
        boolean skip = solve(nums, i + 1, target);

        return currTake || skip;
    }

   //using top-down
    static boolean solveWithMemo(int[] nums, int i, int target) {

        if (i >= n || target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        
        if (dp[i][target] != null) {
            return dp[i][target];
        }
        boolean currTake = false;
        if (nums[i] <= target) {
            currTake = solve(nums, i + 1, target - nums[i]);
        }
        boolean skip = solve(nums, i + 1, target);

        return dp[i][target] = currTake || skip;
    }

 
    // bottom up
    static int solveWithBottomUp(int []nums , int target){
        if(target == 0){
            return 0;
        }

        int dp[][] = new int[n+1][target+1];

        dp[n][0] = 1;

        for(int i = n-1; i>= 0 ; i--){
            for(int j = 0; j <= target ; j++){
                int currTake = 0;
        if (nums[i] <= j) {
            currTake = dp[i + 1][j - nums[i]];
        }
        int skip = dp[i + 1][j];

        dp[i][j] = skip == 1 || currTake == 1 ? 1 : 0;
            }
        }
        return dp[0][target];
    }

    //space optimization
    static int solveWithSpaceOptimization(int []nums , int target){
        if(target == 0){
            return 0;
        }

        int next[] = new int[target+1];

        next[0] = 1;

        for(int i = n-1; i>= 0 ; i--){
            int curr[] = new int[target+1];
            for(int j = 0; j <= target ; j++){
                int currTake = 0;
        if (nums[i] <= j) {
            currTake = next[j - nums[i]];
        }
        int skip = next[j];

        curr[j] = skip == 1 || currTake == 1 ? 1 : 0;
            }

            next =  curr;
        }
        
        return next[target];
    }
    public static void main(String[] args) {
        int nums[] = {1,5,11,5};

        n = nums.length;

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            System.out.println("false");
            return;
        }

        int target = sum / 2;

        dp = new Boolean[n][target + 1];
        
        int ans = solveWithSpaceOptimization(nums, target);
        System.out.println(ans == 1  ? "true" : "false");
    }
}
