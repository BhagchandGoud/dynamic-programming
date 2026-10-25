public class LongestAlmostPalindromicSubstring {
    
    static int n;
    static Integer dp[][] , dp2[][][] ;

    // Returns minimum deletions needed to make s[i..j] a palindrome
    static int almostPalindrome(String s, int i, int j) {

        if (i >= j) {
            return 0;
        }

        if(dp[i][j] != null){
            return dp[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = almostPalindrome(s, i + 1, j - 1);
        }

        return dp[i][j] = 1 + Math.min(
                almostPalindrome(s, i, j - 1),
                almostPalindrome(s, i + 1, j));
    }

    // ============================================================
    // Method 1: Recursive solution using currString state
    // Time Complexity: Exponential
    // Space Complexity: O(n)
    // TLE -----------------------------
    // ============================================================

    static int solveUsingCurrString(String s,
            int i,
            int prevPick,
            String currString) {

        if (i >= n) {
            return prevPick == 1
                    && almostPalindrome(
                            currString,
                            0,
                            currString.length() - 1) <= 1
                                    ? currString.length()
                                    : 0;
        }

        if (prevPick == 1) {

            int extend = solveUsingCurrString(
                    s,
                    i + 1,
                    1,
                    currString + s.charAt(i));

            int stop = almostPalindrome(
                    currString,
                    0,
                    currString.length() - 1) <= 1
                            ? currString.length()
                            : 0;

            return Math.max(extend, stop);

        } else {

            int startNew = solveUsingCurrString(
                    s,
                    i + 1,
                    1,
                    String.valueOf(s.charAt(i)));

            int skip = solveUsingCurrString(
                    s,
                    i + 1,
                    0,
                    "");

            return Math.max(startNew, skip);
        }
    }

    // ============================================================
    // Method 2: Recursive solution using start index
    // Time Complexity: Exponential
    // Space Complexity: O(n)
    // accepted ----------------------------
    // ============================================================

    static int solveUsingStartIndex(String s,
            int i,
            int prevPick,
            int start) {

        if (i >= n) {

            return prevPick == 1
                    && almostPalindrome(s, start, i - 1) <= 1
                            ? i - start
                            : 0;
        }

        if (prevPick == 1) {

            int extend = solveUsingStartIndex(
                    s,
                    i + 1,
                    1,
                    start);

            int stop = almostPalindrome(s, start, i - 1) <= 1
                    ? i - start
                    : 0;

            return Math.max(extend, stop);

        } else {

            int startNew = solveUsingStartIndex(
                    s,
                    i + 1,
                    1,
                    i);

            int skip = solveUsingStartIndex(
                    s,
                    i + 1,
                    0,
                    -1);

            return Math.max(startNew, skip);
        }
    }

    // ============================================================
    // Method 3: Iterate over all substrings
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    // acceped ----------leetcode
    // ============================================================

    static int solveUsingSubstringEnumeration(String s) {

        int ans = 0;

        for (int start = 0; start < n; start++) {

            for (int end = start; end < n; end++) {

                if (almostPalindrome(s, start, end) <= 1) {
                    ans = Math.max(ans, end - start + 1);
                }
            }
        }

        return ans;
    }


    // ============================================================
    // Method 4: Recursive solution using dp2 state
    // Time Complexity: 2*[(n+1)*2*(n+1))]+(n*n)2 = 2*[2n^2 + 2n + 2] * 2n^2 = {4n^2 + 4n + 4 }+2n^2 = n^2+n^2 = O(n^2) 
    // Space Complexity: O((n+1)*2*(n+1))) + n*n = n^2 + n^2 = n^2
    // TLE--------------leetcode
    // ============================================================

    static int solveUsingMemo(String s,int i, int prevPick,int start) {

        if (i >= n) {
            return prevPick == 1 && almostPalindrome(s, start, i - 1) <= 1 ? i - start : 0;
        }

        if(dp2[i][prevPick][start+1] != null){
            return dp2[i][prevPick][start+1];
        }

        int ans;
        if (prevPick == 1) {

            int extend = solveUsingMemo(s,i + 1,1,start);
            int stop = almostPalindrome(s, start, i - 1) <= 1? i - start : 0;
            ans = Math.max(extend, stop);

        } else {

            int startNew = solveUsingMemo( s,i + 1,1,i);

            int skip = solveUsingMemo( s,i + 1,0, -1);

            ans = Math.max(startNew, skip);
        }
        return dp2[i][prevPick][start+1] = ans;
    }

    static int almostPalindromic(String s) {

        n = s.length();
        dp = new Integer[n][n];

        // Method 1
        // return solveUsingCurrString(s, 0, 0, "");

        // Method 2
        // return solveUsingStartIndex(s, 0, 0, -1);

        // Method 3
        // return solveUsingSubstringEnumeration(s);

        // Method 4
        dp2 = new Integer[n+1][2][n+1];

        return solveUsingMemo(s, 0, 0, -1);
    }

    public static void main(String[] args) {
        String s = "abca";

        int ans = almostPalindromic(s);
        System.out.println(ans);
    }
}