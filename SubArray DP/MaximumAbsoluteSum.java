import java.util.Arrays;

public class MaximumAbsoluteSum {
     static int n;

     static int maxDp[][] , minDp[][];

    static int maxSum(int[] nums, int prevPick, int i) {
        if (i >= n) {
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if(maxDp[i][prevPick] != -1){
            return maxDp[i][prevPick];
        }
        if (prevPick == 1) {
            int start = nums[i] + maxSum(nums, 1, i + 1);
            int stop = 0;


            return  maxDp[i][prevPick] =  Math.max(start, stop);
        } else {
            int startNewFromCurr = nums[i] + maxSum(nums, 1, i + 1);
            int startNewFromNext = maxSum(nums, 0, i + 1);

            return maxDp[i][prevPick] = Math.max(startNewFromCurr, startNewFromNext);
        }
    }

    static int minSum(int[] nums, int prevPick, int i) {
        if (i >= n) {
            return prevPick == 1 ? 0 : Integer.MAX_VALUE;
        }

        if(minDp[i][prevPick] != -1){
            return minDp[i][prevPick];
        }
        if (prevPick == 1) {
            int start = nums[i] + minSum(nums, 1, i + 1);
            int stop = 0;


            return  minDp[i][prevPick] = Math.min(start, stop);
        } else {
            int startNewFromCurr = nums[i] + minSum(nums, 1, i + 1);
            int startNewFromNext = minSum(nums, 0, i + 1);

            return minDp[i][prevPick] =  Math.min(startNewFromCurr, startNewFromNext);
        }
    }
    static int maxAbsoluteSum(int[] nums) {
        n = nums.length;

        maxDp = new int[n+1][2];
        minDp = new int[n+1][2];

        for(int i = 0 ; i <= n ; i++){
            Arrays.fill(maxDp[i] , -1);
            Arrays.fill(minDp[i] , -1);
        }
        

        return Math.max(maxSum(nums , 0 , 0) , Math.abs(minSum(nums , 0 , 0)));
    }

    public static void main(String[] args) {
        int[]nums = {1,-3,2,3,-4};

        n = nums.length;

        int ans = maxAbsoluteSum(nums);
        System.out.println(ans);
    }
}
