import java.util.Arrays;

public class OnesAndZeros {
    static int size;



    static int solve(String[] strs, int i, int m, int n, int ones, int zeros) {

        if (ones > n || zeros > m) {
            return Integer.MIN_VALUE;
        }

        if (i >= size) {
            return ones <= n && zeros <= m ? 0 : Integer.MIN_VALUE;
        }

        int countOnes = 0;
        int countZeros = 0;

        for (char ch : strs[i].toCharArray()) {
            if (ch == '1') {
                countOnes++;
            } else {
                countZeros++;
            }
        }

        int take = solve(strs, i + 1, m, n, countOnes + ones, countZeros + zeros);

        if (take != Integer.MIN_VALUE) {
            take += 1;
        }
        int skip = solve(strs, i + 1, m, n, ones, zeros);

        return Math.max(take, skip);
    }


    static int dp[][][];
    static int solveWithMemo(String[] strs, int i, int m, int n, int ones, int zeros) {

        if (ones > n || zeros > m) {
            return Integer.MIN_VALUE;
        }


        if (i >= size) {
            return ones <= n && zeros <= m ? 0 : Integer.MIN_VALUE;
        }

        if(dp[i][ones][zeros] != -1){
            return dp[i][ones][zeros];
        }

        int countOnes = 0;
        int countZeros = 0;

        for (char ch : strs[i].toCharArray()) {
            if (ch == '1') {
                countOnes++;
            } else {
                countZeros++;
            }
        }

        int take = solveWithMemo(strs, i + 1, m, n, countOnes + ones, countZeros + zeros);
        if (take != Integer.MIN_VALUE) {
            take += 1;
        }
        int skip = solveWithMemo(strs, i + 1, m, n, ones, zeros);

        return dp[i][ones][zeros] = Math.max(take, skip);
    }


    public static void main(String[] args) {
        String strs[] = {"10","0001","111001","1","0"};
        int n = 3 , m = 5;
        size = strs.length;

        dp = new int[size+1][n+1][m+1];

        for(int i = 0 ; i<=size ; i++){
            for(int j = 0 ; j <= n ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        int ans =  solveWithMemo(strs, 0, m, n, 0, 0);
        System.out.println(ans);
    }
}
