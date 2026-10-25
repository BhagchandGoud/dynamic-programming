// import java.util.*;

public class CountTheNumberOfPowerfulIntegers {
        // TLE leetcode

        // it can be pass using bit masks with digit dp

    static boolean isSuffixMatch(String curr, String suffix) {
        return curr.endsWith(suffix);
    }

    static long solve(String s1, String s2, int i, int started, int tight, int limit, String curr) {
        if (i >= s1.length()) {
            if (started == 0)
                return 0;
            return isSuffixMatch(curr, s2) ? 1 : 0;
        }

        long ans = 0;
        int upper = tight == 1 ? s1.charAt(i) - '0' : 9;
        for (int digit = 0; digit <= upper; digit++) {

            if(digit > limit) continue;

            int newTight = tight == 1 && digit == upper ? 1 : 0;

            if (started == 0 && digit == 0) {
                ans += solve(s1, s2, i + 1, 0, newTight, limit, "");
            } else {
                ans += solve(s1, s2, i + 1, 1, newTight, limit, curr + digit);
            }
        }
        return ans;
    }

    static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String low = String.valueOf(start - 1);
        String high = String.valueOf(finish);

        long left = solve(low, s, 0, 0, 1, limit, "");
        long right = solve(high, s, 0, 0, 1, limit, "");

        return right - left;
    }

    public static void main(String[] args) {
        long start = 1121;
        long end = 34453;
        int limit = 5;
        String s = "432";
        long ans = numberOfPowerfulInt(start , end , limit ,s);
        System.out.println(ans);
    }
}
