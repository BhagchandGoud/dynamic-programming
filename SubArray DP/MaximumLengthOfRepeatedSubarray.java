import java.util.*;
public class MaximumLengthOfRepeatedSubarray {

    static int n , m;

    static int solve(int []nums1 , int []nums2 , int prevPick , int i , int j){
        if(i == n || j == m){
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if(prevPick == 1){
            int take = (nums1[i] == nums2[j]) ? solve(nums1,nums2 ,1 , i+1 , j+1)+1 : 0;
            int stop = 0;


            return Math.max(take , stop);
        }else{
            int startCurr = nums1[i] == nums2[j] ? solve(nums1,nums2 ,1 , i+1 , j+1)+1 : 0;
            int startNext1 =  solve(nums1,nums2 ,0 , i+1 , j);
            int startNext2 = solve(nums1,nums2 ,0 , i , j+1);

            return Math.max(startCurr , Math.max(startNext1 , startNext2));
        }
    }


    static int dp[][][];

    static int solveWithMemo(int []nums1 , int []nums2 , int prevPick , int i , int j){
        if(i == n || j == m){
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }

        if(dp[i][j][prevPick] != -1){
            return dp[i][j][prevPick];
        }

        if(prevPick == 1){
            int take = (nums1[i] == nums2[j]) ? solveWithMemo(nums1,nums2 ,1 , i+1 , j+1)+1 : 0;
            int stop = 0;


            return dp[i][j][prevPick] =  Math.max(take , stop);
        }else{
            int startCurr = nums1[i] == nums2[j] ? solveWithMemo(nums1,nums2 ,1 , i+1 , j+1)+1 : 0;
            int startNext1 =  solveWithMemo(nums1,nums2 ,0 , i+1 , j);
            int startNext2 = solveWithMemo(nums1,nums2 ,0 , i , j+1);

            return dp[i][j][prevPick] = Math.max(startCurr , Math.max(startNext1 , startNext2));
        }
    }


    static int solveWithBottomUp(int []nums1 , int[] nums2){
       
        for(int j = 0 ; j <= m ; j++){
            for(int prevPick = 0 ; prevPick < 2 ; prevPick++){
                dp[n][j][prevPick] = prevPick == 1 ? 0 : Integer.MIN_VALUE;
            }
        }

        for(int i = n-1 ; i>=0;i--){
           for(int j = m-1 ; j >= 0 ; j--){
            for(int prevPick = 0 ; prevPick < 2 ; prevPick++){
                if(prevPick == 1){
            int take = (nums1[i] == nums2[j]) ? dp[i+1][j+1][1]+1 : 0;
            int stop = 0;


             dp[i][j][prevPick] =  Math.max(take , stop);
        }else{
            int startCurr = (nums1[i] == nums2[j]) ? dp[i+1][j+1][1]+1 : 0;
            int startNext1 =  dp[i+1][j][0];
            int startNext2 = dp[i][j+1][0];

             dp[i][j][prevPick] = Math.max(startCurr , Math.max(startNext1 , startNext2));
        }
            }
        } 
        }

        return dp[0][0][0];
    }

    public static void main(String[] args) {
        int nums1[] = {1,2,3,2,1};
        int nums2[] = {3,2,1,4,7};

        n = nums1.length;
        m = nums2.length;

        dp = new int[n+1][m+1][2];

        for(int i = 0 ; i<=n;i++){
            for(int j = 0 ; j <=m ; j++){
                Arrays.fill(dp[i][j] , -1);
            }
        }

        int ans = solveWithBottomUp(nums1 , nums2);
        System.out.println(Math.max(ans, 0));
    }
}
