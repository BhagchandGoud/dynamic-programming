// Leetcode problem


import java.util.*;


public class NumberOfDigitOne{

    static int dp[][][];

    static int solve(String s , int i , int tight , int count){
        if(i >= s.length()){
            return count;
        }
        if(dp[i][tight][count] != -1){
            return dp[i][tight][count];
        }

        int limit = tight == 1 ? s.charAt(i)-'0' : 9;

        int ans = 0;
        
        for(int k = 0 ; k <= limit ; k++){
            int updateCount = count + ((k == 1) ? 1 : 0);
            int newTight = (tight == 1 && k == limit) ? 1 : 0;
            ans += solve(s, i+1, newTight, updateCount);
        }
        // limit*N*2*N + N+N = (limit * N^2) + 2N -> limit*N^2
        return dp[i][tight][count] = ans;
    }
    static int countDigitOne(int n) {
        String l = String.valueOf(0);
        String r = String.valueOf(n);

        dp = new int[12][2][12];

        for(int i = 0 ; i < 12 ; i++){
            for(int j = 0 ; j < 2 ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        int rightAns = solve(r, 0, 1, 0);

        for(int i = 0 ; i < 12 ; i++){
            for(int j = 0 ; j < 2 ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        int leftAns = solve(l, 0, 1, 0);

        return (rightAns - leftAns);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int ans = countDigitOne(n);
        System.out.println(ans);

        sc.close();

    }
}