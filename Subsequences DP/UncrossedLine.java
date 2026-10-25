
public class UncrossedLine{
     int n , m;

    // solved using recursion

    int solve(int nums1[] , int nums2[] , int i , int j){
        if(i >= n || j >= m){
            return 0;
        }

        if(nums1[i] == nums2[j]){
            return 1 + solve(nums1 , nums2 , i+1 , j+1);
        }
        return Math.max(solve(nums1 , nums2 , i+1 , j) , solve(nums1 , nums2 , i , j+1));
    }

    // solve using memoization

    Integer dp[][];

    int solved_using_memo(int nums1[], int nums2[], int i, int j) {
        if (i >= n || j >= m) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (nums1[i] == nums2[j]) {
            return dp[i][j] = 1 + solved_using_memo(nums1, nums2, i + 1, j + 1);
        }
        return dp[i][j] = Math.max(solved_using_memo(nums1, nums2, i + 1, j), solved_using_memo(nums1, nums2, i, j + 1));
    }


    // solved using bottom up

    int solve(int nums1[], int nums2[]) {
        int dp[][] = new int[n+1][m+1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
                else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        n = nums1.length;
        m = nums2.length;

        dp = new Integer[n + 1][m + 1];
        return solve(nums1 , nums2 , 0 , 0);
    }
}