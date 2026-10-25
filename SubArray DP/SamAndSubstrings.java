public class SamAndSubstrings {
    static int size;
    static int MOD = (int) 1e9 + 7;

    // TLE FOR LARGE STRING LENGTH
    // can not optimize using topdown , bottomup
    static int solve(String s, int prevPick, int i, long num) {
        if (i >= size) {
            return prevPick == 1 ? (int) num % MOD : 0;
        }

        if (prevPick == 1) {

            long newNum = (num * 10 + (s.charAt(i) - '0')) % MOD;
            int take = solve(s, 1, i + 1, newNum);
            int stop = (int) num % MOD;

            return (take + stop) % MOD;
        } else {
            long newNum = (num * 10 + (s.charAt(i) - '0')) % MOD;
            int takeNewFromCurr = solve(s, 1, i + 1, newNum);
            int takeNewFromNext = solve(s, 0, i + 1, 0);

            return (takeNewFromCurr + takeNewFromNext) % MOD;
        }
    }

    // static int MOD = (int)1e9 + 7;


    // optimzation using prev and curr variables

    static long Optimization(String s) {
        long prev = 0;
        long res = 0;

        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';

            long curr = (prev * 10 + (long) digit * (i + 1)) % MOD;

            res = (res + curr) % MOD;
            prev = curr;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "123";
        size = s.length();

        long ans = Optimization(s);
        System.out.println(ans % MOD);
    }
}
