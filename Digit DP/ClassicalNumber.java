
// classy Numbers -> codeforces problems
// accepted by codeforces

import java.util.*;

public class ClassicalNumber {

    static long dp[][][];

    static long solve(String s, int i, int tight, int count) {
        if (i >= s.length()) {
            return 1;
        }
        if (dp[i][tight][count] != -1) {
            return dp[i][tight][count];
        }

        long limit = tight == 1 ? s.charAt(i) - '0' : 9;

        long ans = 0;

        for (int k = 0; k <= limit; k++) {
            int updateCount = count + ((k != 0) ? 1 : 0);
            if (updateCount <= 3) {
                int newTight = (tight == 1 && k == limit) ? 1 : 0;
                ans += solve(s, i + 1, newTight, updateCount);
            }
        }
        // limit*N*2*N + N+N = (limit * N^2) + 2N -> limit*N^2
        return dp[i][tight][count] = ans;
    }

    static long countDigitOne(long n1 , long n2) {
        String l = String.valueOf(n1-1); // n1-1  means if l than start from n1+1 but we also need to include n1  so starts from n1-1
        String r = String.valueOf(n2);

        dp = new long[20][2][4];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long rightAns = solve(r, 0, 1, 0);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long leftAns = solve(l, 0, 1, 0);

        return (rightAns - leftAns);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while(t > 0){
        long n1 = sc.nextLong();
        long n2 = sc.nextLong();

        long ans = countDigitOne(n1 , n2);
        System.out.println(ans);
        t--;
        }


        sc.close();

    }
}