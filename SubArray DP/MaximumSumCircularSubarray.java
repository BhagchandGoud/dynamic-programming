import java.util.*;
public class MaximumSumCircularSubarray {
    static int n;
    static int dp[][][];

    // using recusrion

    static int solve(int nums[] , int prevPick , int i , int findMaxSum){
        if(i == n){
            return prevPick == 1 ? 0 : findMaxSum == 1 ?   Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if(prevPick == 1){
            int take = nums[i] + solve(nums ,1 , i+1 , findMaxSum);
            int stop = 0;
            return findMaxSum == 1 ? Math.max(take , stop) : Math.min(take , stop);
        }else{
            int takeNewFromCurr = nums[i] + solve(nums ,1 , i+1 , findMaxSum);
            int takenewFromNext = solve(nums , 0 , i+1 , findMaxSum);
            return findMaxSum == 1 ? Math.max(takeNewFromCurr , takenewFromNext) : Math.min(takeNewFromCurr , takenewFromNext);
        }
    }


    // top down dp
    static int solveWithMemo(int nums[] , int prevPick , int i , int findMaxSum){
        if(i == n){
            return prevPick == 1 ? 0 : findMaxSum == 1 ?   Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if(dp[i][prevPick][findMaxSum] != -1){
            return dp[i][prevPick][findMaxSum];
        }
        if(prevPick == 1){
            int take = nums[i] + solve(nums ,1 , i+1 , findMaxSum);
            int stop = 0;
            return dp[i][prevPick][findMaxSum] = findMaxSum == 1 ? Math.max(take , stop) : Math.min(take , stop);
        }else{
            int takeNewFromCurr = nums[i] + solve(nums ,1 , i+1 , findMaxSum);
            int takenewFromNext = solve(nums , 0 , i+1 , findMaxSum);
            return dp[i][prevPick][findMaxSum] = findMaxSum == 1 ? Math.max(takeNewFromCurr , takenewFromNext) : Math.min(takeNewFromCurr , takenewFromNext);
        }
    }



    public static void main(String[] args) {
        int nums[] = {5,-3,5};
        n = nums.length;

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        dp = new int[n+1][2][2];

        for(int i = 0 ; i<= n;i++){
            for(int j = 0 ; j<2 ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        int maxSum = solveWithMemo(nums, 0, 0, 1);

        int minSum = solveWithMemo(nums, 0, 0, 0);

        // all negative case
        if (maxSum < 0) {
            System.out.println(maxSum);
            return;
        }

        System.out.println(Math.max(maxSum, total - minSum));

    }
}
