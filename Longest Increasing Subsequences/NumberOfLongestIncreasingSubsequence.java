class Pair {
    int count;
    int length;

    Pair(int count, int length) {
        this.count = count;
        this.length = length;
    }
}

public class NumberOfLongestIncreasingSubsequence {
    static int n;

    // TLE
    static Pair solve(int[] nums, int prevIdx, int i) {
        if (i >= n) {
            return new Pair(1, 0);
        }
        Pair take = new Pair(0, Integer.MIN_VALUE);
        ;
        if (prevIdx == -1 || nums[prevIdx] < nums[i]) {

            Pair next = solve(nums, i, i + 1);

            take = new Pair(next.count, next.length + 1);
        }

        Pair skip = solve(nums, prevIdx, i + 1);

        if (take.length > skip.length) {
            return take;
        }

        if (skip.length > take.length) {
            return skip;
        }

        return new Pair(take.count + skip.count, take.length);
    }

    // TLE
    static Pair dp[][];

    static Pair solveWithMomoPair(int[] nums, int prevIdx, int i) {
        if (i >= n) {
            return new Pair(1, 0);
        }
        Pair take = new Pair(0, Integer.MIN_VALUE);
        ;
        if (prevIdx == -1 || nums[prevIdx] < nums[i]) {

            Pair next = solveWithMomoPair(nums, i, i + 1);

            take = new Pair(next.count, next.length + 1);
        }

        Pair skip = solveWithMomoPair(nums, prevIdx, i + 1);

        Pair ans;

        if (take.length > skip.length) {
            ans = take;
        } else if (skip.length > take.length) {
            ans = skip;
        } else {
            // means both take and skip have the same longest length, so add their counts
            // and keep that common length.
            ans = new Pair(
                    take.count + skip.count,
                    take.length);
        }

        return dp[i][prevIdx + 1] = ans;
    }

    static int findNumberOfLIS(int[] nums) {
        n = nums.length;

        dp = new Pair[n][n + 1];

        Pair ans = solveWithMomoPair(nums, -1, 0);

        return ans.count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 2, 2, 2, 2 };

        int ans = findNumberOfLIS(nums);
        System.out.println(ans);
    }
}
