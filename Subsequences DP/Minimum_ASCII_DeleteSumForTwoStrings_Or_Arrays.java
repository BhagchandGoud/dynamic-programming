import java.util.Arrays;

public class Minimum_ASCII_DeleteSumForTwoStrings_Or_Arrays {

    static int solve(int[] arr1, int[] arr2, int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }

        if (arr1[n - 1] == arr2[m - 1]) {
            return arr1[n - 1] + solve(arr1, arr2, n - 1, m - 1);
        }

        return Math.max(solve(arr1, arr2, n - 1, m), solve(arr1, arr2, n, m - 1));
    }

    // using memoization approach

    static int dp[][];

    static int solveWithMemo(int[] arr1, int[] arr2, int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (arr1[n - 1] == arr2[m - 1]) {
            return dp[n][m] = arr1[n - 1] + solveWithMemo(arr1, arr2, n - 1, m - 1);
        }

        return dp[n][m] = Math.max(solveWithMemo(arr1, arr2, n - 1, m), solveWithMemo(arr1, arr2, n, m - 1));
    }

    //using bottom up approach

    static int solveWithBottomUp(int[] arr1, int []arr2, int n, int m) {

        int dp[][] = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                     dp[i][j] = arr1[i - 1] + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "sea";
        String s2 = "eat";

        int n = s1.length();
        int m = s2.length();

        int arr1[] = new int[n];
        int arr2[] = new int[m];

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < n; i++) {
            arr1[i] = (int) s1.charAt(i);
            sum1 = sum1 + arr1[i];
        }
        for (int i = 0; i < m; i++) {
            arr2[i] = (int) s2.charAt(i);
            sum2 = sum2 + arr2[i];
        }

        dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = solveWithBottomUp(arr1, arr2, n, m);

        System.out.println(sum1 + sum2 - 2 * ans);

    }
}
