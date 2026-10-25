import java.math.BigInteger;
import java.util.*;

public class CountSteppingNumbersInRange {
    static long dp[][][][];
    static int MOD = (int)1e9+7;

    // time complexity = O(105 * 2 * 2 * 11 * 2*N) + N+N = > 3N = > O(N)

    static long solve(String s, int i, int tight, int prev, int started) {
        if (i >= s.length()) {
            return 1;
        }

        if (dp[i][tight][started][prev+1] != -1) {
            return dp[i][tight][started][prev+1];
        }

        int limit = tight == 1 ? (s.charAt(i) - '0') : 9;
        
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            
            int newTight = tight == 1 && digit == (s.charAt(i)-'0') ? 1 : 0;

            if (started == 0 && digit == 0) {
                ans = (ans%MOD + solve(s, i + 1, newTight, -1, 0))%MOD;
            }
            else{
            if(prev != -1 && Math.abs(digit - prev) !=  1)
            {
                continue;
            } else{
            ans = (ans%MOD + solve(s, i + 1, newTight, digit, 1))%MOD;
            }
            }
        }
        return dp[i][tight][started][prev+1] = ans%MOD;
    }

    static int countSteppingNumbers(String low, String high) {

        String LOW = new BigInteger(low).subtract(BigInteger.ONE).toString();

        dp = new long[106][2][2][11];
        
        for (int i = 0; i <= 105; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long leftAns = solve(LOW, 0, 1, -1, 0);

        for (int i = 0; i <= 105; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long rightAns = solve(high, 0, 1, -1, 0);

        return (int)(rightAns - leftAns + MOD)%MOD;
    }


    public static void main(String[] args) {
        String low = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        String high = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";

        int ans = countSteppingNumbers(low , high);
        System.out.println(ans);
    }
}