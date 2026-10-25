public class MaximumLengthOfRepeatedSubarray {

    static int n , m;

    static int solve(int nums1[] , int nums2[] , int i , int j , int prevPick){
        if(i >= n || j >= m){
            return prevPick == 1 ?  0 : Integer.MIN_VALUE;
        }

        if(prevPick == 1){
            int start = nums1[i] == nums2[j] ? 1 + solve(nums1, nums2, i+1, j+1, 1) : 0;
            int stop = 0;
            return Math.max(start, stop);
        }else{
            int startNewFromCurr = nums1[i] == nums2[j] ? 1 + solve(nums1, nums2, i+1, j+1, 1) : 0;
            int startNewFromNext1 = solve(nums1, nums2, i+1, j, 0);
            int startNewFromNext2 = solve(nums1, nums2, i, j+1, 0);
            return Math.max(startNewFromCurr, Math.max(startNewFromNext2, startNewFromNext1));
        }
    }

    static Integer dp[][][];
    static int solveWithMemo(int nums1[] , int nums2[] , int i , int j , int prevPick){
        if(i >= n || j >= m){
            return prevPick == 1 ?  0 : Integer.MIN_VALUE;
        }
        if(dp[i][j][prevPick] != null){
            return dp[i][j][prevPick];
        }

        if(prevPick == 1){
            int start = nums1[i] == nums2[j] ? 1 + solveWithMemo(nums1, nums2, i+1, j+1, 1) : 0;
            int stop = 0;
            return dp[i][j][prevPick] = Math.max(start, stop);
        }else{
            int startNewFromCurr = nums1[i] == nums2[j] ? 1 + solveWithMemo(nums1, nums2, i+1, j+1, 1) : 0;
            int startNewFromNext1 = solveWithMemo(nums1, nums2, i+1, j, 0);
            int startNewFromNext2 = solveWithMemo(nums1, nums2, i, j+1, 0);
            return dp[i][j][prevPick] = Math.max(startNewFromCurr, Math.max(startNewFromNext2, startNewFromNext1));
        }
    }


    
    static int findLength(int[] nums1, int[] nums2) {
        n = nums1.length;
        m =nums2.length;

        dp = new Integer[n+1][m+1][2];

        int ans1 = solve(nums1, nums2, 0, 0, 0);
        int ans2 = solveWithMemo(nums1, nums2, 0, 0, 0);
        return ans2;
    }

    public static void main(String[] args) {
        int nums1[] = {0,0,0,0,0};
        int nums2[] = {0,0,0,0,0};

        int ans = findLength(nums1, nums2);
        System.out.println(ans != Integer.MIN_VALUE ? ans : 0);
    }
}