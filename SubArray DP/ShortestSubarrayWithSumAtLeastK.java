
public class ShortestSubarrayWithSumAtLeastK{
    static int n;

    // dp not usefull reason currSum and len currSum and len canbe very large if use than can get MLE or TLE

    static int solve(int []nums , int i , int prevPick , int k , int currSum , int len){
        if(i >= n){
            return prevPick == 1 && currSum >= k ? len : Integer.MAX_VALUE;
        }

        if(prevPick == 1){
            int start = solve(nums , i+1 ,1 , k , nums[i]+currSum , len+1);
            int stop = currSum >= k ? len : Integer.MAX_VALUE;;
            return Math.min(start , stop);
        }else{
            int startNewFromCurr = solve(nums , i+1 ,1 ,k , nums[i] , 1);
            int startNewFromNext = solve(nums , i+1 , 0 , k , 0 , 0);

            return Math.min(startNewFromCurr ,startNewFromNext);
        }
    }


    static int shortestSubarray(int[] nums, int k) {
        n = nums.length;

        int ans = solve(nums , 0, 0, k, 0 , 0);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public static void main(String[]args){
        int nums[] = {2,-1,2};
        int k = 3;

        int ans = shortestSubarray(nums , k);
        System.out.println(ans);
    }
}