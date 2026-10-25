import java.util.Arrays;

public class Longest_Ideal_Subsequence {
    static int n;

    static int solve(String s, int k, int prevIdx, int i) {
        if (i >= n) {
            return 0;
        }

        int take = 0;
        if (prevIdx == -1 || Math.abs(s.charAt(prevIdx) - s.charAt(i)) <= k) {
            take = 1 + solve(s, k, i, i + 1);
        }

        int skip = solve(s, k, prevIdx, i + 1);

        return Math.max(take, skip);
    }

    
    // MLE (memory limit exits)
    static int solveWithMemo(int [][]dp ,String s, int k, int prevIdx, int i) {
        if (i >= n) {
            return 0;
        }

        if (dp[i][prevIdx + 1] != -1) {
            return dp[i][prevIdx + 1];
        }

        int take = 0;

        if (prevIdx == -1 || Math.abs(s.charAt(prevIdx) - s.charAt(i)) <= k) {
            take = 1 + solveWithMemo(dp ,s, k, i, i + 1);
        }
        int skip = solveWithMemo(dp ,s, k, prevIdx, i + 1);

        return dp[i][prevIdx + 1] = Math.max(take, skip);
    }

    // MLE

    static int solveWithBottomUp(String s, int k) {
        int dp[][] = new int[n+1][n + 1];

        for (int i = n-1 ; i >= 0; i--) {
            for (int prevIdx = n; prevIdx >= 0; prevIdx--) {

                int take = 0;
               if (prevIdx == n || Math.abs(s.charAt(prevIdx) - s.charAt(i)) <= k) {
                    take = 1 + dp[i+1][i];
                }
                int skip = dp[i+1][prevIdx];

                dp[i][prevIdx] = Math.max(take ,skip);
            }
        }

        return dp[0][n];
    }

    // TLE
    static int solveWithSpaceOptimization(String s, int k) {
        int next[] = new int[n + 1];

        for (int i = n-1 ; i >= 0; i--) {
            int curr[] = new int[n+1];
            for (int prevIdx = n; prevIdx >= 0; prevIdx--) {

                int take = 0;
               if (prevIdx == n || Math.abs(s.charAt(prevIdx) - s.charAt(i)) <= k) {
                    take = 1 + next[i];
                }
                int skip = next[prevIdx];

                curr[prevIdx] = Math.max(take ,skip);
            }
            next = curr;
        }

        return next[n];
    }


    // ALL test cases will be pass / NO TLE AND MLE

    static int solveWithMemo_2(int dp[][] , String s, int k, int prevChar, int i) {
        if (i >= n) {
            return 0;
        }

        if (dp[i][prevChar + 1] != -1) {
            return dp[i][prevChar + 1];
        }

        int currChar = s.charAt(i)-'a';
        int take = 0;

        if (prevChar == -1 || Math.abs(prevChar - currChar) <= k) {
            take = 1 + solveWithMemo_2(dp ,s, k, currChar, i + 1);
        }
        int skip = solveWithMemo_2(dp , s, k, prevChar, i + 1);

        return dp[i][prevChar + 1] = Math.max(take, skip);
    }


    


    

    public static void main(String[] args) {
        String s = "abcd";
        int k = 3;
        n = s.length();


        int ans1 = solve(s, k, 0, 0);

        int dp[][] = new int[n][26];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        int ans2 = solveWithMemo(dp, s, k, -1, 0);
        int ans3 = solveWithMemo_2(dp, s, k, -1, 0);
        int ans4 = solveWithBottomUp(s, k);
        int ans5 = solveWithSpaceOptimization(s, k);

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
        System.out.println(ans5);
    }
}
