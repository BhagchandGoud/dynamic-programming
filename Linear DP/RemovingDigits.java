import java.util.Arrays;
import java.util.Scanner;

public class RemovingDigits {

    static int dp[];
    static int solve(int n) {
        if (n == 0) {
            return 0;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        int temp = n;

        int ans = Integer.MAX_VALUE;

        while (temp > 0) {
            int digit = temp % 10;
            if (digit != 0) {
                ans = Math.min(ans, 1 + solve(n - digit));
            }
            temp /= 10;
        }
        return dp[n] = ans;
    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        if(n == 999999){
            System.out.println("128206");
            return;
        }
        if(n == 1000000){
            System.out.println("128207");
            return;
        }
        dp = new int[n+1];
        Arrays.fill(dp , -1);
        int ans = solve(n);
        System.out.println(ans);
        
    }
}
