import java.util.*;


public class CountNumberOfSubsequences {

    static int n;

    static int solve(int nums[] , int i){
        if(i == n){
            return 1;
        }
        int take = solve(nums, i+1);
        int skip = solve(nums, i+1);

        return take + skip;
    }
    
    static int dp[];
    static int solveWithMemo(int nums[] , int i){
        if(i == n){
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int take = solve(nums, i+1);
        int skip = solve(nums, i+1);

        return dp[i] = take + skip;
    }

    // t.c = O(2^N) // s.c =  O(N)
    static int solveWithOutMemoLoop(int []nums , int start){
        if(start == n){
            return 1;
        }
        int count = 1;

        for(int i = start ; i<n;i++){
            int currTake = solve(nums, i+1);
            count += currTake;
        }
        return count;
    }
     
    // t.c =  O(N*N) // s.c = O(2*N)
    static int solveWithMemoLoop(int []nums , int start){
        if(start == n){
            return 1;
        }

        if(dp[start]!= -1){
            return dp[start];
        }
        int count = 1;

        for(int i = start ; i<n;i++){
            int currTake = solve(nums, i+1);
            count += currTake;
        }
        return dp[start] = count;
    }
    public static void main(String[] args) {
        int nums[] = {2,1 ,2,1,3};
        n = nums.length;

        dp = new int[n+1];
        Arrays.fill(dp , -1);

        int ans = solveWithMemo(nums, 0);
        System.out.print(ans);
    }
}
