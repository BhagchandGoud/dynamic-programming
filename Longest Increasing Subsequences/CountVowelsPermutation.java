import java.util.Arrays;

public class CountVowelsPermutation {
    static int n;
    static char vowels[] = { 'a', 'e', 'i', 'o', 'u' };
    static int dp[][];
    static int MOD = (int)1e9+7;

    static int solveWithMemo(int i, int prev) {
        if (i >= n) {
            return 1;
        }

        if (dp[i][prev + 1] != -1) {
            return dp[i][prev + 1];
        }

        int count = 0;

        for (int curr = 0; curr < 5; curr++) {
            if (prev == -1 || (vowels[prev] == 'a' && vowels[curr] == 'e')
                    || (vowels[prev] == 'e' && (vowels[curr] == 'a' || vowels[curr] == 'i'))
                    || (vowels[prev] == 'i' && vowels[curr] != 'i')
                    || (vowels[prev] == 'o' && (vowels[curr] == 'i' || vowels[curr] == 'u'))
                    || (vowels[prev] == 'u' && vowels[curr] == 'a')) {
                count = (count + solveWithMemo(i + 1, curr))%MOD;
            }

        }

        return dp[i][prev + 1] = count%MOD;
    }

    public static void main(String[] args) {
        n = 2;

        dp = new int[n + 1][6];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = solveWithMemo(0, -1);
        System.out.println(ans);
    }
}
