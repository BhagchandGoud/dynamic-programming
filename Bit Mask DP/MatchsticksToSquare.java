public class MatchsticksToSquare {
    static int n;

    // got TLE error
    static boolean solve(int mask, int nums[], int currSum, int count , int target) {
        if (count == 4) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0 && currSum + nums[i] <= target) {
                boolean ans;
                int newMask = (mask | (1 << i));
                ans = (currSum + nums[i]) == target ? solve(newMask, nums, 0, count + 1 , target)
                        : solve(newMask, nums, nums[i] + currSum, count , target);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }


    // all test cases pass using bottom 

    static boolean solveWithBoottomUp(int nums[] , int target){
        Integer dp[] = new Integer[1 << n];

        dp[0] = 0;

        for(int mask = 0 ; mask < (1 << n) ; mask++){
            for (int i = 0; i < n; i++) {
                if(dp[mask] == null)continue;

                 if ((mask & (1 << i)) == 0 && dp[mask] + nums[i] <= target) {
                int newMask = (mask | (1 << i));
                dp[newMask] = (dp[mask] + nums[i])%target ;
            }
            }
        }
        return dp[(1 << n)-1] != null && dp[(1 << n)-1] == 0 ;
    }

    static boolean makesquare(int[] matchsticks) {
        n = matchsticks.length;

        int sum = 0;
        for (int x : matchsticks)
            sum += x;

        if (sum % 4 != 0)
            return false;

        boolean ans = solveWithBoottomUp( matchsticks,  sum/4);
        return ans;
    }

    public static void main(String[] args) {
        int nums[] = {1,1,2,2,2};

        boolean ans = makesquare(nums);
        System.out.println(ans);
    }
}
