import java.util.*;

public class PartitionswithGivenDifference {

    static int n;

    static int solve(int nums[] , int i , int target){
        if(i >= n){
            return target == 0 ? 1 : 0;
        }

        int currTake = 0;
        if(nums[i] <= target){
            currTake = solve(nums, i+1, target-nums[i]);
        }
        int currSkip = solve(nums, i+1, target);

        return currSkip + currTake;
    }


    static int dp[][];

    static int solveWithMemo(int nums[] , int i , int target){
        if(i >= n){
            return target == 0 ? 1 : 0;
        }

        if(dp[i][target] != -1){
            return dp[i][target];
        }

        int currTake = 0;
        if(nums[i] <= target){
            currTake = solveWithMemo(nums, i+1, target-nums[i]);
        }
        int currSkip = solveWithMemo(nums, i+1, target);

        return dp[i][target] = currSkip + currTake;
    }
    public static void main(String[] args) {
        int nums[] = {1,1,1,1};

        int diff =  0;

        n = nums.length;

        int sum = 0;

        for(int val : nums){
            sum += val;
        }

        int target = sum/2;

        dp = new int[n][target+1];

        for(int i = 0 ; i<n; i++){
            Arrays.fill(dp[i] , -1);
        }

        
        int res = 0;
        for(int subSet1  = 0 ; subSet1 <= sum /2; subSet1++){
            int subSet2 = sum - subSet1;

            if (Math.abs(subSet1 - subSet2) == diff) {

                res =  res + solveWithMemo(nums, 0, subSet1);
            }
        }
        System.out.println(res);
    }
}
