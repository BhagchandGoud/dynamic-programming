import java.util.Arrays;

public class LongestDecreasingSubArrayLength {

    static int n;

    // using recursion
    
    static int SOLVE(int nums[], int i, int prevPick, int prevVal, int symbol) {
        if (i == n) {
            return 0;
        }
        if (prevPick == 1) {

            int take = 0;
            // decreasing
            if (symbol == 1) {

                if (prevVal > nums[i]) {
                    take = 1 + SOLVE(nums, i + 1, 1, nums[i], symbol);
                }

            } else {

                if (prevVal < nums[i]) {
                    take = 1 + SOLVE(nums, i + 1, 1, nums[i], symbol);
                }

            }
            int stop = 0;

            return Math.max(take, stop);
        } else {
            int takeNewFromCurr = 1 + SOLVE(nums, i + 1, 1, nums[i], symbol);

            int takeNewFromNext = SOLVE(nums, i + 1, 0, 0, symbol);

            return Math.max(takeNewFromCurr, takeNewFromNext);
        }
    }

    // using memoization/ top-down dp
    static int dp[][][][];

    static int SOLVE_WITH_MEMO(int nums[], int i, int prevPick, int prevVal, int symbol) {
        if (i == n) {
            return 0;
        }

        if (dp[i][prevPick][symbol][prevVal] != -1) {
            return dp[i][prevPick][symbol][prevVal];
        }
        if (prevPick == 1) {

            int take = 0;
            // decreasing
            if (symbol == 1) {

                if (prevVal > nums[i]) {
                    take = 1 + SOLVE_WITH_MEMO(nums, i + 1, 1, nums[i], symbol);
                }

            } else {

                if (prevVal < nums[i]) {
                    take = 1 + SOLVE_WITH_MEMO(nums, i + 1, 1, nums[i], symbol);
                }

            }
            int stop = 0;

            return dp[i][prevPick][symbol][prevVal] = Math.max(take, stop);
        } else {
            int takeNewFromCurr = 1 + SOLVE_WITH_MEMO(nums, i + 1, 1, nums[i], symbol);

            int takeNewFromNext = SOLVE_WITH_MEMO(nums, i + 1, 0, 0, symbol);

            return dp[i][prevPick][symbol][prevVal] = Math.max(takeNewFromCurr, takeNewFromNext);
        }
    }

    // using bottom up

    static int solveWithBottomUp(int[] nums, int max) {
        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int symbol = 0; symbol < 3; symbol++) {
                for (int prevVal = 0; prevVal <= max; prevVal++) {
                    dp[n][prevPick][symbol][prevVal] = 0;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int symbol = 0; symbol < 3; symbol++) {
                    for (int prevVal = 0; prevVal <= max; prevVal++) {
                        if (prevPick == 1) {

                            int take = 0;
                            // decreasing
                            if (symbol == 1) {

                                if (prevVal > nums[i]) {
                                    take = 1 + dp[i + 1][1][symbol][nums[i]];
                                }

                            } else {

                                if (prevVal < nums[i]) {
                                    take = 1 + dp[i + 1][1][symbol][nums[i]];
                                }

                            }
                            int stop = 0;

                            dp[i][prevPick][symbol][prevVal] = Math.max(take, stop);
                        } else {
                            int takeNewFromCurr = 1 + dp[i + 1][1][symbol][nums[i]];

                            int takeNewFromNext = dp[i + 1][0][symbol][0];
                            ;

                            dp[i][prevPick][symbol][prevVal] = Math.max(takeNewFromCurr, takeNewFromNext);
                        }
                    }
                }
            }
        }

        return Math.max(dp[0][0][1][0],dp[0][0][2][0]);
    }
    public static void main(String[] args) {
        int nums[] = { 4,8,12,16 };
        n = nums.length;

        int max = 0;

        for (int x : nums) {
            max = Math.max(max, x);
        }

        dp = new int[n + 1][2][3][max + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        // int increasing = SOLVE_WITH_MEMO(nums, 0, 0, 0, 1);
        // int decreasing = SOLVE_WITH_MEMO(nums , 0 ,0 , 0 , 2);

        int increasing = solveWithBottomUp(nums, max);
        int decreasing = solveWithBottomUp(nums, max);
        System.out.println(Math.max(increasing, decreasing));
    }
}
