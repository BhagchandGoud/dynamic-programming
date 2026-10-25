public class CountCommasInRange2 {

    static Long dp[][][][];
    // 2*20*2*length * (length + 1) * 10 => O(length^2)
    static long solve(String s, int started, int i, int tight, int length) {
        if (i >= s.length()) {
            if (started == 0) {
                return 0;
            }
            return (length - 1) / 3;
        }

        if(dp[started][i][tight][length] != null){
            return dp[started][i][tight][length];
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;

        long count = 0;

        for (int digit = 0; digit <= limit; digit++) {

            int newTight = tight == 1 && digit == s.charAt(i)-'0' ? 1 : 0;

            if (started == 0 && digit == 0) {
                count += solve(s, 0, i + 1, newTight, 0);
            } else {
                count += solve(s, 1, i + 1, newTight, length + 1);
            }

        }
        return dp[started][i][tight][length] = count;
    }

    static long countCommas(long n) {
        if(n  < 1000){
            return 0;
        }
        dp = new Long[2][20][2][String.valueOf(999).length()+1];
        long left = solve(String.valueOf(999) ,0, 0 , 1 , 0);
        dp = new Long[2][20][2][String.valueOf(n).length()+1];
        long right = solve(String.valueOf(n) ,0, 0 , 1 , 0);

        return right - left;
    }

    public static void main(String[]args){
        long n = 1002;
        long ans = countCommas(n);
        System.out.print(ans);
    }

}
