
import java.util.*;

public class BookShop {
    static int n;

    static int dp[][];

    static int solve(int prices[], int pages[], int i, int x) {
        if (i >= n) {
            return 0;
        }

        int take = 0;

        if (prices[i] <= x) {
            take = pages[i] + solve(prices, pages, i + 1, x - prices[i]);
        }

        int skip = solve(prices, pages, i + 1, x);

        return Math.max(take, skip);

    }

    static int solveWithMemo(int prices[], int pages[], int i, int x) {
        if (i >= n || x == 0) {
            return 0;
        }

        if (dp[i][x] != -1) {
            return dp[i][x];
        }

        int take = 0;

        if (prices[i] <= x) {
            take = pages[i] + solveWithMemo(prices, pages, i + 1, x - prices[i]);
        }

        int skip = solveWithMemo(prices, pages, i + 1, x);

        return dp[i][x] = Math.max(take, skip);

    }

    static int solveWithBottomUp(int prices[], int pages[], int x) {

        int dp[][] = new int[n + 1][x + 1];

        for (int i = 0; i <= x; i++) {
            dp[n][i] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= x; j++) {
                int take = 0;

                if (prices[i] <= j) {
                    take = pages[i] + dp[i + 1][j - prices[i]];
                }

                int skip = dp[i + 1][j];

                dp[i][j] =  Math.max(take , skip);
            }

        }
    return dp[0][x];
    }


    static int solveWithSpcaeOptimization(int prices[], int pages[], int x) {

        int next[] = new int[x + 1];

        for (int i = 0; i <= x; i++) {
            next[i] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            int curr[] = new int[x+1];
            for (int j = 0; j <= x; j++) {
                int take = 0;

                if (prices[i] <= j) {
                    take = pages[i] + next[j - prices[i]];
                }

                int skip = next[j];

                curr[j] =  Math.max(take , skip);
            }
            next = curr;
        }
    return next[x];
    }

    static int solveWithMoreSpaceOptimization(int prices[], int pages[], int x) {

    int dp[] = new int[x + 1];

    for (int i = 0; i < n; i++) {

        for (int j = x; j >= prices[i]; j--) {

            dp[j] = Math.max(
                dp[j],
                pages[i] + dp[j - prices[i]]
            );
        }
    }

    return dp[x];
}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int x = sc.nextInt();
        int prices[] = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        int pages[] = new int[n];
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        dp = new int[n + 1][x + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = solveWithMoreSpaceOptimization(prices, pages, x);
        System.out.println(ans);

        sc.close();
    }
}
