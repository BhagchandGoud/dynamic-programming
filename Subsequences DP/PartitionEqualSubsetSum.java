public class PartitionEqualSubsetSum {
    
    static int n;

    static boolean solve(int []nums , int i , int target){
        if(i >= n){
            return false;
        }
        if(target == 0){
            return true;
        }
        boolean currTake = solve(nums, i+1, target-nums[i]);
        boolean skip = solve(nums, i+1, target);

        return currTake ||  skip;
    }


    // memoization

    static Boolean dp[][];

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

    public static void main(String[] args) {
        int nums[] = {1, 5, 11, 5};

        n = nums.length;

        int sum = 0;

        for(int num : nums){
            sum += num;
        }

        if(sum%2 != 0){
            System.out.println("sum odd");
            return;
        }

        int target = sum/2;

        dp = new Boolean[n][target + 1];

        boolean ans = solveWithMemo(nums , 0 , target);
        System.out.println(ans);
    }
}
