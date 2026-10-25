import java.util.Arrays;

public class NumberOfSubarraysWithOddSum {
    static int n;
    // recursion will give you TLE
    static int solve(int []arr , int prevPick , int i , int sum){
        if(i >= n){
            return prevPick == 1 && sum%2 != 0 ? 1 : 0;
        }

        if(prevPick == 1){
            int start = solve(arr, 1 , i+1 , sum + arr[i]);
            int stop = sum%2 != 0 ? 1 : 0;

            return stop + start;
        }
        else{
            int startNewFromCurr = solve(arr , 1 , i+1 , sum + arr[i]);
            int startNewFromNext = solve(arr , 0 , i+1 , sum);
            return startNewFromCurr + startNewFromNext;
        }
    }


    // 
    static int dp[][][];
    static int MOD = (int)1e9+7;

    // solve using parity check all test cases will be pass
    // Parity means whether a number is even or odd.

    static int solveWithMemo(int []arr , int prevPick , int i , int parity){
        if(i >= n){
            return prevPick == 1 && parity == 1  ? 1 : 0;
        }

        if(dp[i][prevPick][parity] != -1){
            return dp[i][prevPick][parity];
        }

        if(prevPick == 1){
            int start = solveWithMemo(arr, 1 , i+1 ,(parity + arr[i])%2);
            int stop = parity == 1 ? 1 : 0;

            return dp[i][prevPick][parity] = (stop + start)%MOD;
        }
        else{
            int startNewFromCurr = solveWithMemo(arr , 1 , i+1 , arr[i]%2);
            int startNewFromNext = solveWithMemo(arr , 0 , i+1 , 0);
            return dp[i][prevPick][parity] = (startNewFromCurr + startNewFromNext)%MOD;
        }
    }
    static public int numOfSubarrays(int[] arr) {
        n = arr.length;

        dp = new int[n+1][2][2];
        for (int i = 0; i <= n; i++) {
            for (int j  = 0 ; j < 2 ; j++) {
                Arrays.fill(dp[i][j] , -1);
            }
        }
        return solveWithMemo(arr , 0 , 0 , 0);
    }

    public static void main(String[] args) {
        int arr[] = {1,3,5};
        int ans = numOfSubarrays(arr);
        System.out.println(ans);
    }
}
