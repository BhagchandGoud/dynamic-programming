import java.util.Arrays;

public class LongestNonDecreasingSubarrayFromTwoArrays {
    static int n, m;

    // using recursion
    static int solve(int[] nums1, int[] nums2, int prevPick, int prevVal, int i, int arrNumber) {
        if (i == n) {
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if (prevPick == 1) {
            int takeFromArray1 = 0;

            if (prevVal <= nums1[i]) {
                takeFromArray1 = 1 + solve(nums1, nums2, 1, nums1[i], i + 1, 1);
            }
            int takeFromArray2 = 0;
            if (prevVal <= nums2[i]) {
                takeFromArray2 = 1 + solve(nums1, nums2, 1, nums2[i], i + 1, 2);
            }
            int stop = 0;

            return Math.max(takeFromArray1, Math.max(takeFromArray2, stop));
        } else {
            int takeNewFromCurr = 0;

            if (arrNumber == 1) {
                if (prevVal <= nums2[i]) {
                    takeNewFromCurr = 1 + solve(nums1, nums2, 1, nums1[i], i + 1, 1);
                }
            } else {
                if (prevVal <= nums2[i]) {
                    takeNewFromCurr = 1 + solve(nums1, nums2, 1, nums2[i], i + 1, 2);
                }
            }

            int takeNewFromNext1 = solve(nums1, nums2, 0, 0, i + 1, 1);
            int takeNewFromNext2 = solve(nums1, nums2, 0, 0, i + 1, 2);

            return Math.max(takeNewFromCurr, Math.max(takeNewFromNext1, takeNewFromNext2));
        }
    }

    // using top down

    static int dp[][][];

    int solveWithMemo(int[] nums1, int[] nums2, int prevPick, int prevVal, int i) {
        if (i == n) {
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if (dp[i][prevPick][prevVal] != -1) {
            return dp[i][prevPick][prevVal];
        }

        if (prevPick == 1) {
            int takeFromArray1 = 0;

            if (prevVal <= nums1[i]) {
                takeFromArray1 = 1 + solveWithMemo(nums1, nums2, 1, nums1[i], i + 1);
            }

            int takeFromArray2 = 0;
            if (prevVal <= nums2[i]) {
                takeFromArray2 = 1 + solveWithMemo(nums1, nums2, 1, nums2[i], i + 1);
            }

            int stop = 0;

            return dp[i][prevPick][prevVal] = Math.max(takeFromArray1, Math.max(takeFromArray2, stop));
        } else {
            int takeNewFromCurr1 = 0;

            if (prevVal <= nums2[i]) {
                takeNewFromCurr1 = 1 + solveWithMemo(nums1, nums2, 1, nums1[i], i + 1);
            }
            int takeNewFromCurr2 = 0;
            if (prevVal <= nums2[i]) {
                takeNewFromCurr2 = 1 + solveWithMemo(nums1, nums2, 1, nums2[i], i + 1);
            }

            int takeNewFromNext1 = solveWithMemo(nums1, nums2, 0, 0, i + 1);
            int takeNewFromNext2 = solveWithMemo(nums1, nums2, 0, 0, i + 1);

            return dp[i][prevPick][prevVal] = Math.max(takeNewFromCurr1,
                    Math.max(takeNewFromCurr2, Math.max(takeNewFromNext1, takeNewFromNext2)));
        }
    }

    // using bottom up

    int solve(int nums1[], int nums2[]) {
        int dp1[][][], dp2[][][];

        dp1 = new int[n + 1][2][100000];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp1[i][j], -1);
            }
        }

        dp2 = new int[m + 1][2][100000];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp2[i][j], -1);
            }
        }

        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int prevVal = 0; prevVal <= n; prevVal++) {
                dp1[n][prevPick][prevVal] = 0;
            }
        }
        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int prevVal = 0; prevVal <= m; prevVal++) {
                dp2[m][prevPick][prevVal] = 0;
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int prevVal = 0; prevVal <= n; prevVal++) {
                    if (prevPick == 1) {
                        int takeFromArray1 = 0;

                        if (prevVal <= nums1[i]) {
                            takeFromArray1 = 1 + dp1[i + 1][1][nums1[i]];
                        }

                        int takeFromArray2 = 0;
                        if (prevVal <= nums2[i]) {
                            takeFromArray2 = 1 + dp2[i + 1][1][nums2[i]];
                        }

                        int stop = 0;

                        dp1[i][prevPick][prevVal] = Math.max(takeFromArray1, stop);
                        dp2[i][prevPick][prevVal] = Math.max(takeFromArray2, stop);

                    } else {
                        int takeNewFromCurr1 = 0;

                        if (prevVal <= nums2[i]) {
                            takeNewFromCurr1 = 1 + dp1[i + 1][1][nums1[i]];
                        }
                        int takeNewFromCurr2 = 0;
                        if (prevVal <= nums2[i]) {
                            takeNewFromCurr2 = 1 + dp2[i + 1][1][nums2[i]];
                        }

                        int takeNewFromNext1 = dp1[i + 1][0][0];
                        int takeNewFromNext2 = dp2[i + 1][0][0];

                        dp1[i][prevPick][prevVal] = Math.max(takeNewFromCurr1, takeNewFromNext1);
                        dp2[i][prevPick][prevVal] = Math.max(takeNewFromCurr2, takeNewFromNext2);
                    }
                }
            }
        }

        return Math.max(dp1[0][0][0], dp2[0][0][0]);
    }

    

    public static void main(String[] args) {
        int nums1[] = { 100, 64, 76, 79, 68, 3, 74, 14, 74, 82, 74, 30, 74, 9, 74, 33, 74 };
        int nums2[] = { 20, 71, 65, 81, 43, 74, 100, 74, 18, 74, 2, 74, 58, 74, 12, 74, 76 };

        n = nums1.length;
        m = nums2.length;

        dp = new int[n + 1][2][100000];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = Math.max(solve(nums1, nums2, 0, 0, 0, 1), solve(nums1, nums2, 0, 0, 0, 2));
        System.out.println(ans);
    }
}
