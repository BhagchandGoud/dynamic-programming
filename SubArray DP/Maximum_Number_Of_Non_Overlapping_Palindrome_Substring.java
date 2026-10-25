public class Maximum_Number_Of_Non_Overlapping_Palindrome_Substring{

    int n;
    int K;

    // Integer dp[][][];

    boolean pal[][];

    // int solve(String s, int i, int prevPick, int start) {
    //     if (i >= n) {
    //         if (prevPick == 1 &&
    //                 n - start >= K &&
    //                 pal[start][n - 1]) {
    //             return 1;
    //         }

    //         return 0;
    //     }

    //     if (dp[i][prevPick][start + 1] != null) {
    //         return dp[i][prevPick][start + 1];
    //     }

    //     if (prevPick == 1) {
    //         int take = solve(s, i + 1, 1, start);
    //         int skip = (i - start >= K) && pal[start][i - 1] ? 1 + solve(s, i, 0, -1) : 0;
    //         return dp[i][prevPick][start + 1] = Math.max(take, skip);
    //     } else {
    //         int startNewFromCurr = solve(s, i + 1, 1, i);
    //         int startNewFromNext = solve(s, i + 1, 0, -1);
    //         return dp[i][prevPick][start + 1] = Math.max(startNewFromCurr, startNewFromNext);
    //     }
    // }

    int solve(String s) {

        int next[][] = new int[2][n + 1];

        for (int start = -1; start < n; start++) {
            if (start == -1) {
                next[1][start + 1] = 0;
            } else {
                next[1][start + 1] = n - start >= K && pal[start][n - 1] ? 1 : 0;
            }
        }
        next[0][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int curr[][] = new int[2][n + 1];
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int start = -1; start < n; start++) {
                    if (prevPick == 1) {
                        int take = next[1][start + 1];
                        int skip = start != -1 && (i - start >= K) && pal[start][i - 1] ? 1 + curr[0][0] : 0;
                        curr[prevPick][start + 1] = Math.max(take, skip);
                    } else {
                        int startNewFromCurr = next[1][i + 1];
                        int startNewFromNext = next[0][0];
                        curr[prevPick][start + 1] = Math.max(startNewFromCurr, startNewFromNext);
                    }
                }
            }
            next = curr;
        }
        return next[0][0];
    }

    public int maxPalindromes(String s, int k) {
        n = s.length();
        K = k;
        // pre computation of palindromes
        pal = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    pal[i][j] = true;
                } else if (len == 2) {
                    pal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    pal[i][j] = s.charAt(i) == s.charAt(j) && pal[i + 1][j - 1];
                }
            }
        }
        return solve(s);
    }

}