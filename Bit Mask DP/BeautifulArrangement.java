import java.util.Arrays;

public class BeautifulArrangement {
    static int dp[];
    static int n;

    static int solve(int mask) {

        if (mask == (1 << n) - 1) {
            return 1;
        }

        if (dp[mask] != -1) {
            return dp[mask];
        }

        int pos = Integer.bitCount(mask) + 1;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                boolean valid = (i + 1) % pos == 0 || pos % (i + 1) == 0;

                if (valid) {
                    int newMask = mask | (1 << i);
                    ans += solve(newMask);
                }
            }

        }

        return dp[mask] = ans;
    }

    static int countArrangement(int m) {
        n = m;

        dp = new int[1 << n];
        Arrays.fill(dp, -1);

        return solve(0);
    }

    public static void main(String[] args) {
        int m = 4;

        int ans = countArrangement(m);
        System.out.println(ans);
    }
}
