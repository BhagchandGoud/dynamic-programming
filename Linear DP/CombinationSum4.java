public class CombinationSum4 {
    static int n;
    // this is a knapsack approach
    // timeComplexity : => (2*n)^target O(n^target)
    // spaceComplexity : => O(n)
    static int solve(int nums[] , int i , int target){
        if(i >= n){
            return 0;
        }

        if(target == 0){
            return 1;
        }
if(target < 0){
            return 0;
        }
        int take = solve(nums , 0 , target - nums[i]);
        int skip = solve(nums , i+1, target);
        return take + skip;
    }

    // this is linear dp appraoch

    // timeComplexity : => (target * n) + (target + 1) => O(target*n)
    // spaceComplexity : => (target + 1)*n => O(target*n)
    static int solveUsingWithOutMemo(int nums[] , int target){
        if(target == 0){
            return 1;
        }
        if(target < 0){
            return 0;
        }
        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            ans +=  solveUsingWithOutMemo(nums, (target - nums[i]));
        }
        return ans;
    }

    // timeComplexity : => (target * n) + (target + 1) => O(target*n)
    // spaceComplexity : => (target + 1)*n => O(target*n)
    static Integer dp[];
    static int solveUsingWithMemo(int nums[] , int target){
        if(target == 0){
            return 1;
        }
        if(target < 0){
            return 0;
        }
        if(dp[target] != null){
            return dp[target];
        }
        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            ans +=  solveUsingWithMemo(nums, (target - nums[i]));
        }
        return dp[target] = ans;
    }

    // timeComplexity : => (target * n) + (target + 1) => O(target*n)
    // spaceComplexity : => (target + 1)*n => O(target*n)
    static int solveUsingBottomUp(int nums[] , int target){
        int dp[] = new int[target+1];
        dp[0] = 1;

        for(int i = 1 ; i <= target ; i++){
            int ans = 0;
        for(int j = 0 ; j < n ; j++){
            ans += (nums[j] <= i) ? dp[i - nums[j]] : 0;
        }
        dp[i] = ans;
        }
        return dp[target] ;
    }
    static int combinationSum4(int[] nums, int target) {
        n = nums.length;
        return solve(nums , 0, target);
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3};
        int target = 4;

        int ans = combinationSum4(nums, target);
        System.out.println(ans);
    }
}
