import java.util.*;


// lightOj problem accepted

public class DigitCount {

    static int dp[][];

    static int solve(int[] nums, int i, int prevIdx, int n) {
        if (i == n) {
            return 1;
        }

        if (dp[i][prevIdx + 1] != -1) {
            return dp[i][prevIdx + 1];
        }

        int ans = 0;
        for (int j = 0 ; j < nums.length; j++) {
            if ((prevIdx == -1) || Math.abs(nums[prevIdx] - nums[j]) <= 2) {
                ans += solve(nums, i + 1, j, n);
            }
        }

        return dp[i][prevIdx + 1] = ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int totalCases = t;

        while (t > 0) {
            int size = sc.nextInt();
            int n = sc.nextInt();

            int arr[] = new int[size];

            for (int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }

            dp = new int[n + 1][size + 1];

            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);

            }

            int ans = solve(arr, 0, -1, n);

             System.out.println("Case "+ (totalCases - t+1)+":" + " " + ans);
            t--;
        }

        sc.close();

    }
}
