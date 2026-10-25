import java.util.Arrays;

class Pair {
    int age;
    int score;

    Pair(int age, int score) {
        this.age = age;
        this.score = score;
    }
}

public class BestTeamWithNoConflicts {
    static int n;
    static int dp[][];

    static int solveWithMemo(Pair[] nums, int i, int prevIdx) {

        if (i >= n) {
            return 0;
        }

        if (dp[i][prevIdx + 1] != -1) {
            return dp[i][prevIdx + 1];
        }

        int take = 0;

        if (prevIdx == -1 || nums[prevIdx].score <= nums[i].score) {
            take = solveWithMemo(nums, i + 1, i) + nums[i].score;
        }

        int skip = solveWithMemo(nums, i + 1, prevIdx);

        return dp[i][prevIdx + 1] = Math.max(take, skip);
    }

    static int bestTeamScore(int[] scores, int[] ages) {
        n = scores.length;

        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(ages[i], scores[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.age == b.age) {
                return a.score - b.score;
            }
            return a.age - b.age;
        });

        dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solveWithMemo(arr, 0, -1);
    }

    public static void main(String[] args) {
        int[] ages = { 1,2,3,4,5 };
        int[] scores = { 1, 3, 5, 10, 15};

        int ans = bestTeamScore(scores, ages);

        System.out.println(ans);
    }
}
