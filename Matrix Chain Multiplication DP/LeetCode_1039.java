
public class LeetCode_1039 {
    
 // solve using recursion 
    static int solve(int nums[] , int i , int j){
        if(j - i +1 < 3){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for(int k = i+1 ; k < j ; k++){
            int score = nums[i] * nums[k] * nums[j] + solve(nums , i , k) + solve(nums, k, j);
            res = Math.min(res , score);
        }
        return res;
    }

    // solve using memoization
    static Integer dp[][];
    static int solveUsingMemo(int nums[] , int i , int j){
        if(j - i +1 < 3){
            return 0;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        for(int k = i+1 ; k < j ; k++){
            int score = nums[i] * nums[k] * nums[j] + solve(nums , i , k) + solve(nums, k, j);
            res = Math.min(res , score);
        }
        return dp[i][j] = res;
    }
    public static void main(String[] args) {
        int nums[] = {1,3,1,4,1,5};
        int n = nums.length;
        dp = new Integer[n+1][n+1];
        int x = solve(nums , 0 , n-1);
        System.out.println(x);
    }
}
