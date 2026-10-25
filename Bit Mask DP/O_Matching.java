import java.util.Arrays;
import java.util.Scanner;

// AtCoder problem -> accedpted only dp part not recursion part

//recursion TLE 
public class O_Matching {
    static int n;
    static int MOD = (int)1e9+7;
    
    static long solve(int mask ,int arr[][]){
        if(mask == (1 << n)-1){
            return 1;
        }

        int man = Integer.bitCount(mask);

        long ans = 0;
        for(int woman = 0 ; woman < n ; woman++){
            if(((mask & (1 << woman)) == 0) && arr[man][woman] == 1){
               
                int newMask = mask | (1 << woman);
                ans = (ans + solve(newMask , arr))%MOD; 
            }
        }
        return ans%MOD;
    }

    static long dp[];
    static long solveWithMemo(int mask ,int arr[][]){
        if(mask == (1 << n)-1){
            return 1;
        }

        if(dp[mask] != -1){
            return dp[mask];
        }
        int man = Integer.bitCount(mask);

        long ans = 0;
        for(int woman = 0 ; woman < n ; woman++){
            if(((mask & (1 << woman)) == 0) && arr[man][woman] == 1){
               
                int newMask = mask | (1 << woman);
                ans = (ans + solveWithMemo(newMask , arr))%MOD; 
            }
        }
        return dp[mask] = ans%MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int [][]arr = new int[n][n];
       
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        dp = new long[1 << n];
        Arrays.fill(dp , -1);

        long ans  = solveWithMemo(0, arr);

        System.out.println(ans%MOD);
        sc.close();

    }

}
