public class MaximumSubArrayProduct {

    static int n;

    static int solve(int[] nums, int i, int prevPick, int prod) {
        if (i >= n) {
            return prevPick == 1 ? prod : Integer.MIN_VALUE;
        }

        if (prevPick == 1) {

            int start = solve(nums, i + 1, 1, prod * nums[i]);
            int stop = prod;
            return Math.max(start, stop);

        } else {
            int startNewFromCurr = solve(nums, i + 1, 1, nums[i]);
            int startNewFromNext = solve(nums, i + 1, 0, 1);
            return Math.max(startNewFromCurr, startNewFromNext);
        }
    }

    static int maxProduct(int[] nums) {
        n = nums.length;
        if(n == 1){
            return nums[0];
        }

         boolean allSame = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 1) {
                allSame = false;
                break;
            }
        }

        if (allSame) {
            return nums[0];
        }
        int ans = solve(nums, 0, 0  ,1);
       return(ans);

    }

    // memoization can not work here reason -> prod
    // but recursion passed test cases 191/191 -> accepted

    public static void main(String[] args) {
        int nums[] = { -3, -1, -1 };
        int ans = maxProduct(nums);
        System.out.println(ans);

    }
}