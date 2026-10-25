
public class CountCommasInRanges {

    static int solve(String s, int started, int i, int tight, int length) {
        if (i >= s.length()) {
            if (started == 0) {
                return 0;
            }
            return (length - 1) / 3;
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;

        int count = 0;

        for (int digit = 0; digit <= limit; digit++) {

            int newTight = tight == 1 && digit == s.charAt(i) - '0' ? 1 : 0;

            if (started == 0 && digit == 0) {
                count += solve(s, 0, i + 1, newTight, 0);
            } else {
                count += solve(s, 1, i + 1, newTight, length + 1);
            }

        }
        return count;
    }

    static int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }

        int left = solve(String.valueOf(999), 0, 0, 1, 0);
        int right = solve(String.valueOf(n), 0, 0, 1, 0);

        return right - left;
    }

    public static void main(String[]args){
        int n = 1002;
        int ans = countCommas(n);
        System.out.print(ans);
    }
}
