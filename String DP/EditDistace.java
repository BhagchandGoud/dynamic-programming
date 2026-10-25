
import java.util.Scanner;

public class EditDistace {

    static int n, m;

    static int solve(String s1, String s2, int i, int j) {

        /*
    Base Case Explanation :

    We have two strings

    s1 = "horse"
    s2 = "ros"

    i -> pointer on s1
    j -> pointer on s2

    ------------------------------------------------------------
    Case 1 :
    if (i == n)

    This means we have reached the end of string s1, but there may
    still be some characters remaining in s2.

    Example :

        s1 = "abc"
        s2 = "abcdef"

                 i
        s1 = abc|
                 ^
                 end of s1

                    j
        s2 = abc|def
                 ^

    Here, "def" is still left in s2.

    To convert s1 into s2, we have to INSERT all the remaining
    characters of s2 into s1.

    Number of insert operations required
        = remaining characters in s2
        = m - j

    Therefore,

        if(i == n)
            return m - j;

    ------------------------------------------------------------
    Case 2 :
    if (j == m)

    This means we have reached the end of string s2, but there are
    still some characters remaining in s1.

    Example :

        s1 = "abcdef"
        s2 = "abc"

                    i
        s1 = abc|def
                 ^

                 j
        s2 = abc|
                 ^
                 end of s2

    Here, "def" is still left in s1.

    To make s1 equal to s2, we have to DELETE all the remaining
    characters from s1.

    Number of delete operations required
        = remaining characters in s1
        = n - i

    Therefore,

        if(j == m)
            return n - i;
         */
        if (i == n) {
            return m - j;
        }
        if (j == m) {
            return n - i;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return solve(s1, s2, i + 1, j + 1);
        } else {
            int insert = solve(s1, s2, i, j+1) + 1;
            int delete = solve(s1, s2, i+1, j) + 1;
            int replace = solve(s1, s2, i + 1, j + 1)+1;
            return Math.min(insert, Math.min(delete, replace));
        }
    }

    static Integer dp[][];
    static int solveUsingMemo(String s1, String s2, int i, int j) {
        if (i == n) {
            return m - j;
        }
        if (j == m) {
            return n - i;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solveUsingMemo(s1, s2, i + 1, j + 1);
        } else {
            int insert = solveUsingMemo(s1, s2, i, j+1) + 1;
            int delete = solveUsingMemo(s1, s2, i+1, j) + 1;
            int replace = solveUsingMemo(s1, s2, i + 1, j + 1)+1;
            return dp[i][j] = Math.min(insert, Math.min(delete, replace));
        }
    }

    
    static int solveUsingBottomUp(String s1, String s2) {
         n = s1.length();
         m = s2.length();

        int dp1[][] = new int[n + 1][m + 1];

        for (int j = 0; j <= m; j++) {
            dp1[n][j] = m - j;
        }
        for (int i = 0; i <= n; i++) {
            dp1[i][m] = n - i;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp1[i][j] = dp1[i + 1][j + 1];
                } else {
                    int insert = dp1[i][j + 1] + 1;
                    int delete = dp1[i + 1][j] + 1;
                    int replace = dp1[i + 1][j + 1] + 1;
                    dp1[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp1[0][0];
    }


    static int solveUsingTwoArrays(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int nextRow[] = new int[m + 1];
        int currRow[] = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            nextRow[j] = m - j;
        }

        currRow[m] = n - n; // beacuse loop is running 0 to n-1 but we need
        // i ==  n
        // so  currRow[m] = n - i ,=> n - n
 

        for (int i = n - 1; i >= 0; i--) {
            currRow[m] = n - i;
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    currRow[j] = nextRow[j + 1];
                } else {
                    int insert = currRow[j + 1] + 1;
                    int delete = nextRow[j] + 1;
                    int replace = nextRow[j + 1] + 1;
                    currRow[j] = Math.min(insert, Math.min(delete, replace));
                }
            }
            // Swap rows
            int temp[] = nextRow;
            nextRow = currRow;
            currRow = temp;
        }
        return nextRow[0];
    }

    static int minDistance(String word1, String word2) {
        n = word1.length();
        m = word2.length();

        /* Intatlize dp */
        dp = new Integer[n+1][m+1];

        int ans = solveUsingMemo(word1 , word2 , 0 , 0);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        n = s1.length();
        m = s2.length();

        int ansFromRecursion = solve(s1, s2, 0, 0);
        int ansFromMemoization = minDistance(s1, s2);
        int ansFromBottomUp = solveUsingBottomUp(s1, s2);
        int ansFromSolveUsingTwoArrays = solveUsingTwoArrays(s1,s2);
        System.out.println(ansFromRecursion);
        System.out.println(ansFromMemoization);
        System.out.println(ansFromBottomUp);
        System.out.println(ansFromSolveUsingTwoArrays);
        sc.close();
    }
}
