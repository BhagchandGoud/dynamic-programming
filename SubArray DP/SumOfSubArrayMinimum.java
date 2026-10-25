import java.util.*;


// find minimum in every subarray and calculate sum
public class SumOfSubArrayMinimum {

    static int n;

    // recursion

    // time complexity = O(2^N)
    // space Complexity = O(N)

    static int solve(int nums[], int prevPick, int subArrayMin, int i) {
        if (i == n) {
            return prevPick == 1 ? subArrayMin : 0;
        }

        if (prevPick == 1) {
            int take = solve(nums, 1, Math.min(subArrayMin, nums[i]), i + 1);

            int stop = subArrayMin;

            return take + stop;
        } else {
            int takeNewFromCurr = solve(nums, 1, nums[i], i + 1);
            int takeNewFromNext = solve(nums, 0, 0, i + 1);
            return takeNewFromNext + takeNewFromCurr;
        }
    }

    // top down / memoization

    // time complexity = O(N*subArrayMin)
    // space Complexity = O(N*2*subArrayMin)

    static int dp[][][];
    static int MOD = (int) 1e9 + 7;

    static int solveWithMemo(int nums[], int prevPick, int subArrayMin, int i) {
        if (i == n) {
            return prevPick == 1 ? subArrayMin : 0;
        }

        if (dp[i][prevPick][subArrayMin] != -1) {
            return dp[i][prevPick][subArrayMin];
        }

        if (prevPick == 1) {
            int take = solveWithMemo(nums, 1, Math.min(subArrayMin, nums[i]), i + 1);

            int stop = subArrayMin;

            return dp[i][prevPick][subArrayMin] = (take + stop) % MOD;
        } else {
            int takeNewFromCurr = solveWithMemo(nums, 1, nums[i], i + 1);
            int takeNewFromNext = solveWithMemo(nums, 0, 0, i + 1);
            return dp[i][prevPick][subArrayMin] = (takeNewFromNext + takeNewFromCurr) % MOD;
        }
    }

    // bottom up

    // time complexity = O(subArrayMin*2 + N*2*SubArrayMin) =  O(N*SubArrayMin)
    // space Complexity = O(N*subArrayMin)

    static int solveWithBottomUp(int[] nums) {

        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int subArrayMin = 0; subArrayMin < 30006; subArrayMin++) {
                dp[n][prevPick][subArrayMin] = prevPick == 1 ? subArrayMin : 0;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int subArrayMin = 0; subArrayMin < 30006; subArrayMin++) {
                    if (prevPick == 1) {
                        int take = dp[i+1][1][Math.min(subArrayMin ,nums[i])];

                        int stop = subArrayMin;

                         dp[i][prevPick][subArrayMin] = (take + stop) % MOD;
                    } else {
                        int takeNewFromCurr = dp[i+1][1][nums[i]];
                        int takeNewFromNext = dp[i+1][0][0];
                         dp[i][prevPick][subArrayMin] = (takeNewFromNext + takeNewFromCurr) % MOD;
                    }
                }
            }
        }
      return dp[0][0][0];
    }


    // space optimization

    static int solveWithSpaceOptimization(int[] nums) {

        int[][] next = new  int[2][30006]; // i+1 th table

        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int subArrayMin = 0; subArrayMin < 30006; subArrayMin++) {
                next[prevPick][subArrayMin] = prevPick == 1 ? subArrayMin : 0;
            }
        }

        for (int i = n-1; i >= 0; i--) {

            int curr[][] = new int[2][30006]; // i th table

            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int subArrayMin = 0; subArrayMin < 30006; subArrayMin++) {
                    if (prevPick == 1) {
                        int take = next[1][Math.min(subArrayMin ,nums[i])];

                        int stop = subArrayMin;

                         curr[prevPick][subArrayMin] = (take + stop) % MOD;
                    } else {
                        int takeNewFromCurr = next[1][nums[i]];
                        int takeNewFromNext = next[0][0];
                         curr[prevPick][subArrayMin] = (takeNewFromNext + takeNewFromCurr) % MOD;
                    }
                }
            }
            next = curr;
        }
      return next[0][0];
    }
    public static void main(String[] args) {
        int nums[] = { 11, 81, 94, 43, 3 };

        n = nums.length;

        dp = new int[n + 1][2][300001];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // int ans = solveWithMemo(nums, 0, 0, 0);

        int ans = solveWithSpaceOptimization(nums);
        System.out.println(ans % MOD);
    }
}
