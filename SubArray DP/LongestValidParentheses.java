import java.util.Arrays;

public class LongestValidParentheses{
    static int n;

    static int solve(String s , int prevPick, int stackLen , int i){
        if(i == n){
            return stackLen == 0 && prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if(stackLen < 0){
            return Integer.MIN_VALUE;
        }

        if(prevPick == 1){
           int  take = solve(s, 1, (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1), i+1) ;

           if(take != Integer.MIN_VALUE){
            take += 1;
           }
           int stop = stackLen == 0  ? 0 : Integer.MIN_VALUE;;
           
           return Math.max(take, stop);
        }
        else{
            int takeNewFromCurr = solve(s, 1, (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1), i+1);
            if(takeNewFromCurr != Integer.MIN_VALUE){
            takeNewFromCurr += 1;
           }
            int takeNewFromNext = solve(s, 0, 0, i+1);

            return Math.max(takeNewFromCurr , takeNewFromNext);
        }

    }

    static int dp[][][];

    static int solveWithMemo(String s , int prevPick, int stackLen , int i){
        if(i == n){
            return stackLen == 0 && prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if(stackLen < 0){
            return Integer.MIN_VALUE;
        }

        if(dp[i][prevPick][stackLen] != -1){
            return dp[i][prevPick][stackLen];
        }

        if(prevPick == 1){
           int  take = solve(s, 1, (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1), i+1) ;

           if(take != Integer.MIN_VALUE){
            take += 1;
           }
           int stop = stackLen == 0  ? 0 : Integer.MIN_VALUE;
           
           return dp[i][prevPick][stackLen] =  Math.max(take, stop);
        }
        else{
            int takeNewFromCurr = solve(s, 1, (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1), i+1);
            if(takeNewFromCurr != Integer.MIN_VALUE){
            takeNewFromCurr += 1;
           }
            int takeNewFromNext = solve(s, 0, 0, i+1);

            return dp[i][prevPick][stackLen] = Math.max(takeNewFromCurr , takeNewFromNext);
        }

    }


    static int solveWithBottomUp(String s) {
        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int stackLen = 0; stackLen <= n; stackLen++) {
                dp[n][prevPick][stackLen] = stackLen == 0 ? 0 : Integer.MIN_VALUE;
                ;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int stackLen = 0; stackLen <= n; stackLen++) {
                    if (prevPick == 1) {

                        int newLen = (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1);

                        int take = newLen < 0 || newLen >= n ? Integer.MIN_VALUE : dp[i + 1][1][newLen];

                        if (take != Integer.MIN_VALUE) {
                            take += 1;
                        }
                        int stop = stackLen == 0 ? 0 : Integer.MIN_VALUE;

                        dp[i][prevPick][stackLen] = Math.max(take, stop);
                    } else {
                        int newLen = (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1);
                        int takeNewFromCurr = newLen < 0 || newLen >= n ? Integer.MIN_VALUE : dp[i + 1][1][newLen];
                        if (takeNewFromCurr != Integer.MIN_VALUE) {
                            takeNewFromCurr += 1;
                        }
                        int takeNewFromNext = dp[i + 1][0][0];

                        dp[i][prevPick][stackLen] = Math.max(takeNewFromCurr, takeNewFromNext);
                    }
                }
            }
        }
        return dp[0][0][0];
    }


    static int solveWithSpaceOptimization(String s) {

        int [][]next = new int[2][n+1];
        for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int stackLen = 0; stackLen <= n; stackLen++) {
                next[prevPick][stackLen] = stackLen == 0 ? 0 : Integer.MIN_VALUE;
                ;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int [][]curr = new int[2][n+1];
            for (int prevPick = 0; prevPick < 2; prevPick++) {
                for (int stackLen = 0; stackLen <= n; stackLen++) {
                    if (prevPick == 1) {

                        int newLen = (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1);

                        int take = newLen < 0 || newLen >= n ? Integer.MIN_VALUE : next[1][newLen];

                        if (take != Integer.MIN_VALUE) {
                            take += 1;
                        }
                        int stop = stackLen == 0 ? 0 : Integer.MIN_VALUE;

                        curr[prevPick][stackLen] = Math.max(take, stop);
                    } else {
                        int newLen = (s.charAt(i) == '(' ? stackLen + 1 : stackLen - 1);
                        int takeNewFromCurr = newLen < 0 || newLen >= n ? Integer.MIN_VALUE : next[1][newLen];
                        if (takeNewFromCurr != Integer.MIN_VALUE) {
                            takeNewFromCurr += 1;
                        }
                        int takeNewFromNext = next[0][0];

                        curr[prevPick][stackLen] = Math.max(takeNewFromCurr, takeNewFromNext);
                    }
                }
            }
        }
        return next[0][0];
    }
    public static void main(String[] args) {
        String s = ")()())";

        if (n == 0) {
            System.out.println('0');
            return;
        }

        dp = new int[n + 1][2][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int ans = solveWithBottomUp(s);
        
        System.out.println(Math.max(ans , 0));
    }
}