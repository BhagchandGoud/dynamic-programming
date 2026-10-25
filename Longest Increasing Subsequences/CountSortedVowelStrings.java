import java.util.Arrays;

public class CountSortedVowelStrings {
    static int n;
    static char vowels[]  = {'a','e','i','o','u'};

    // solve using recursion


    static int solve(int i , char prev){
        if(i >= n){
            return 1;
        }

        int count = 0;

        for(char curr : vowels){
            if(prev == '\0' || prev <= curr){
                count += solve(i+1,curr);
            }
        }
        return count;
    }


    // solve using memoization
    static int dp[][];

    

    static int solveWithMemo(int i, int prev) {
        if (i >= n) {
            return 1;
        }

        if (dp[i][prev + 1] != -1) {
            return dp[i][prev + 1];
        }

        int count = 0;

        for (int curr = 0; curr < 5; curr++) {
            if (prev == -1 || vowels[prev] <= vowels[curr]) {
                count += solveWithMemo(i + 1, curr);
            }
        }

        return dp[i][prev + 1] = count;
    }





    public static void main(String[] args) {
        n = 1;

        dp = new int[n+1][6];
        for(int i = 0 ; i <= n ;i++){
            Arrays.fill(dp[i] , -1);
        }

    int ans = solveWithMemo(0, -1);
    System.out.println(ans);
    }


}