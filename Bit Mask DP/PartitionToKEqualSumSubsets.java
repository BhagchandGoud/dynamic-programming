
public class PartitionToKEqualSumSubsets {

    static int n;

    static boolean solve(int nums[], int mask, int target, int currSum, int count, int k) {
        if (count == k - 1) {
            return true;
        }

        

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0 && nums[i] + currSum <= target) {
                boolean ans;
                int newMask = (mask | (1 << i));
                ans = (nums[i] + currSum == target) ? solve(nums, newMask, target, 0, count + 1, k)
                        : solve(nums, newMask, target, currSum + nums[i], count, k);
                        if(ans){
                            return true;
                        }
            }
        }

        return false;
    }

    // memoization is not possible


    static boolean solveWithBoottomUp(int nums[] , int target){
        Integer dp[] = new Integer[1 << n];

        dp[0] = 0;

        for(int mask = 0 ; mask < (1 << n) ; mask++){

            for(int j = 0 ; j < n ; j++){
                if(dp[mask] == null) continue;
                if((mask&(1 << j)) == 0 && nums[j] + dp[mask] <= target){
                    int newMask = (mask | (1 << j));
                    dp[newMask] = (dp[mask]+nums[j])%target;
                }
            }
        }
        return dp[(1 << n) - 1] != null && dp[(1 << n) - 1] == 0;
    }

    public static void main(String[] args) {
        int nums[] = { 1,2,3,4 };
        n = nums.length;
        int k = 3;

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % k != 0) {
            System.out.println("false");
            return;
        }
        int target = sum / k;
        boolean ans = solve(nums, 0, target, 0, 0, k);
        boolean ans2 = solveWithBoottomUp(nums, target);

        System.out.println(ans2);

        System.out.println(ans);
    }
}