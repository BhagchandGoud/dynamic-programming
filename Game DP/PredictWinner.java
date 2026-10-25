public class PredictWinner {
    int n;
    Integer dp[][][];

    int solveUingRecursion(int nums[], int i, int j, int turn) {
        if (i > j) {
            return 0;
        }

        if (turn == 0) {
            int pick_first = nums[i] + solveUingRecursion(nums, i + 1, j, 1);
            int pick_last = nums[j] + solveUingRecursion(nums, i, j - 1, 1);
            return Math.max(pick_first, pick_last);
        } else {
            int pick_first = solveUingRecursion(nums, i + 1, j, 0);
            int pick_last = solveUingRecursion(nums, i, j - 1, 0);
            return Math.min(pick_first, pick_last);
        }
    }

    int solveUsingMemoization(int nums[] , int i , int j , int turn){
        if(i > j){
            return 0;
        }
        if(dp[i][j][turn] != null){
            return dp[i][j][turn];
        }
        if(turn  == 0){
            int pick_first = nums[i] + solveUsingMemoization(nums , i+1  ,j , 1);
            int pick_last  = nums[j] + solveUsingMemoization(nums , i , j-1 , 1);
            return dp[i][j][turn] = Math.max(pick_first , pick_last);
        }else{
            int pick_first =  solveUsingMemoization(nums , i+1  ,j , 0);
            int pick_last  =  solveUsingMemoization(nums , i , j-1 , 0);
            return dp[i][j][turn] = Math.min(pick_first , pick_last);
        }
    }
    public boolean predictTheWinner(int[] nums) {
        n = nums.length;
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
        }
        int p1 = solveUingRecursion(nums, 0, n - 1, 0);
        int p2 = totalSum - p1;

        return p1 >= p2;
    }
}
