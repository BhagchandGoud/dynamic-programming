public class WildCardMatching {
    static int n, m;
    static Boolean dp[][];
    static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    static boolean solve(String s1, String s2, int i, int j) {
        if (i > n || j > m) {
            return false;
        }
        if (i == n && j == m) {
            return true;
        }
        if (j == m) {
            return false;
        }
        if (i == n) {
            while (j < m && s2.charAt(j) == '*') {
                j++;
            }
            return j == m;
        }

        if(dp[i][j] != null){
            return dp[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j) || s2.charAt(j) == '?') {
            return dp[i][j] = solve(s1, s2, i + 1, j + 1);
        } else {
            if (isLetter(s2.charAt(j))) {
                return dp[i][j] = false;
            } else if (solve(s1, s2, i, j + 1)) {
                return dp[i][j] = true;
            } else {
                boolean moveBoth = solve(s1, s2, i + 1, j + 1);
                boolean move_i = solve(s1, s2, i + 1, j);
                return dp[i][j] = moveBoth || move_i;
            }
        }
    }

    public boolean isMatch(String s, String p) {
        n = s.length();
        m = p.length();
        dp = new Boolean[n+1][m+1];
        return solve(s, p, 0, 0);
    }
}
