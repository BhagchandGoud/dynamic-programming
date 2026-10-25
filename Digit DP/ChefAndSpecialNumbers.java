import java.util.Scanner;

public class ChefAndSpecialNumbers {

    // all get TLE

    // combination of bitmask and digit dp

    // solve using recursion
    static long DigitDp(String s, int i, int tight, int k, int mask, int rem) {
        if (i >= s.length()) {
            long count = 0;
            for (int digit = 1; digit <= 9; digit++) {
                if (((mask & (1 << digit)) != 0) && rem % digit == 0) {
                    count++;
                }
            }
            return count >= k ? 1 : 0;
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && limit == digit ? 1 : 0;

            int newMask = mask;
            if (digit > 0) {
                newMask = (mask | (1 << digit));
            }
            int newRem = (rem * 10 + digit) % 2520;

            ans += DigitDp(s, i + 1, newTight, k, newMask, newRem);
        }
        return ans;
    }

    static Long dp[][][][];

    // solve using 4d dp
    static long DigitDpWith_four_D_DP(String s, int i, int tight, int k, int mask, int rem) {
        if (i >= s.length()) {
            long count = 0;
            for (int digit = 1; digit <= 9; digit++) {
                if (((mask & (1 << digit)) != 0) && rem % digit == 0) {
                    count++;
                }
            }
            return count >= k ? 1 : 0;
        }

        if (dp[i][tight][rem][mask] != null) {
            return dp[i][tight][rem][mask];
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && limit == digit ? 1 : 0;

            int newMask = mask;
            if (digit > 0) {
                newMask = (mask | (1 << digit));
            }
            int newRem = (rem * 10 + digit) % 2520;

            ans += DigitDpWith_four_D_DP(s, i + 1, newTight, k, newMask, newRem);
        }
        return dp[i][tight][rem][mask] = ans;
    }

    /// solve using 3d dp
    /// 
    /// 

    static Long dp2[][][];

    static long DigitDpWithMemo(String s, int i, int tight, int k, int mask, int rem) {
        if (i >= s.length()) {
            long count = 0;
            for (int digit = 1; digit <= 9; digit++) {
                if (((mask & (1 << digit)) != 0) && rem % digit == 0) {
                    count++;
                }
            }
            return count >= k ? 1 : 0;
        }

        if (tight == 0 && dp[i][rem][mask] != null) {
            return dp2[i][rem][mask];
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && limit == digit ? 1 : 0;

            int newMask = mask;
            if (digit > 0) {
                newMask = (mask | (1 << digit));
            }
            int newRem = (rem * 10 + digit) % 2520;

            ans += DigitDpWithMemo(s, i + 1, newTight, k, newMask, newRem);
        }

        if (tight == 0) {
            dp2[i][rem][mask] = ans;
        }
        return ans;
    }

    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);

        int q = sc.nextInt();

        while (q-- > 0) {
            long n1 = sc.nextLong();
            long n2 = sc.nextLong();
            int k = sc.nextInt();

            String l = String.valueOf(n1 - 1);
            String r = String.valueOf(n2);

            dp = new Long[20][2][2520][1 << 10];
            long leftAns = DigitDpWith_four_D_DP(l, 0, 1, k, 0, 0);
            dp = new Long[20][2][2520][1 << 10];
            long rightAns = DigitDpWith_four_D_DP(r, 0, 1, k, 0, 0);
            System.out.println(rightAns - leftAns);
        }
        sc.close();

    }
}
