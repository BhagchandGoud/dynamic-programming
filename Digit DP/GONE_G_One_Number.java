
import java.util.*;
// spoj problem accepted
public class GONE_G_One_Number {
    static int dp[][][];

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int count = 0;

    static int solve(String s, int i, int tight, int sum) {
        if (i >= s.length()) {
            return isPrime(sum) ? 1 : 0;
        }

        if (dp[i][tight][sum] != -1) {
            return dp[i][tight][sum];
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;

        int ans = 0;

        for (int k = 0; k <= limit; k++) {

            int newTight = tight == 1 && k == limit ? 1 : 0;
            ans += solve(s, i + 1, newTight, sum+k);

        }

        return dp[i][tight][sum] = ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int t = sc.nextInt();

        while (t > 0) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
    
            String s1 = String.valueOf(n1-1);
            String s2 = String.valueOf(n2);
    
            dp = new int[11][2][95];
    
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 2; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
    
            int rightAns = solve(s2, 0, 1, 0);
    
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 2; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
    
            int leftAns = solve(s1, 0, 1, 0);
    
            System.out.println(rightAns - leftAns);
            t--;
        }


        sc.close();
    }
}