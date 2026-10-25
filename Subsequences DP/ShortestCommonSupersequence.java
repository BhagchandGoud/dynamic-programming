import java.util.Arrays;

public class ShortestCommonSupersequence {

    // can not solve only recursion because we need to store

    static String dp[][];

    static String solve(String s1, String s2, int n, int m) {
        if (m == 0)
            return s1.substring(0, n);
        if (n == 0)
            return s2.substring(0, m);

        if (dp[n][m] != null)
            return dp[n][m];

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = s1.charAt(n - 1) + solve(s1, s2, n - 1, m - 1);
        }
        String a = s1.charAt(n - 1) + solve(s1, s2, n - 1, m);
        String b = s2.charAt(m - 1) + solve(s1, s2, n, m - 1);

        return dp[n][m] = a.length() <= b.length() ? a : b;
    }

    // using bottom approach

    static String solveWithBottomUp(String s1, String s2, int n, int m) {
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }

        int i = n, j = m;

        StringBuilder s = new StringBuilder();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                s.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                s.append(s1.charAt(i - 1));
                i--;
            } else {
                s.append(s2.charAt(j - 1));
                j--;
            }
        }

        // add remaining elements from String s1 to s

        while (i > 0) {
            s.append(s1.charAt(i - 1));
            i--;
        }

        // add remaining elements from String s2 to s

        while (j > 0) {
            s.append(s2.charAt(j - 1));
            j--;
        }

        return s.reverse().toString();
    }

    public static void main(String[] args) {

        String s1 = "aaaaaaaa";
        String s2 = "aaaaaaaa";

        int n = s1.length();
        int m = s2.length();

        System.out.println(solveWithBottomUp(s1, s2, n, m));

        dp = new String[n + 1][m + 1];
        for (int i = 0; i < n; i++) {

            Arrays.fill(dp[i], null);
        }

        String ans = new String(solve(s1, s2, n, m));

        System.out.println(ans);

    }
}
