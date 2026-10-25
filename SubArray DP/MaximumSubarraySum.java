public class MaximumSubarraySum {
     static int n;
    static int solve(int[]nums , int i , int prevPick){
        if(i >= n){
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }
        if(prevPick == 1){
            int start = solve(nums , i+1 , 1) + nums[i];
            int stop = 0;
            return Math.max(start , stop);
        }
        else{
            int start = solve(nums , i+1 , 1) + nums[i];
            int stop = solve(nums , i+1 , 0);
            return Math.max(start , stop);
        }
    }

    static Integer dp[][];
    static int solveWithmemo(int[]nums , int i , int prevPick){
        if(i >= n){
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }
        if(dp[i][prevPick] != null){
            return dp[i][prevPick];
        }
        if(prevPick == 1){
            int start = solveWithmemo(nums , i+1 , 1) + nums[i];
            int stop = 0;
            return dp[i][prevPick] = Math.max(start , stop);
        }
        else{
            int start = solveWithmemo(nums , i+1 , 1) + nums[i];
            int stop = solveWithmemo(nums , i+1 , 0);
            return dp[i][prevPick] =Math.max(start , stop);
        }
    }
    static int maxSubarraySum(int[] arr) {
       n = arr.length;

       dp = new Integer[n+1][2];

       
       return solveWithmemo(arr , 0 , 0);
    }

    public static void main(String[] args) {
        int []arr = {2, 3, -8, 7, -1, 2, 3};

        int ans = maxSubarraySum(arr);
        System.out.println(ans);
    }



}
