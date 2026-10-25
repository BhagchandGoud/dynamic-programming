import java.util.*;

// sum of all odd length sub array 

class SumOfAllOddLengthSubArrays {
   static int n;

   // using recursion

   static int solve(int[] nums, int i, int prevPick, int subArrayLen, int subArraySum) {
      if (i == n) {
         return prevPick == 1 && (subArrayLen % 2 != 0) ? subArraySum : 0;
      }

      if (prevPick == 1) {
         int take = solve(nums, i + 1, 1, subArrayLen + 1, nums[i] + subArraySum);

         int stop = (subArrayLen % 2 != 0) ? subArraySum : 0;
         return (take + stop);
      } else {
         int takeNewFromCurr = solve(nums, i + 1, 1, subArrayLen + 1, subArraySum + nums[i]);
         int takeNewFromNext = solve(nums, i + 1, 0, 0, 0);
         return (takeNewFromCurr + takeNewFromNext);
      }
   }

   // using memozation

   // time complexity = O(N*subArrayLen*subArraySum)
   // space complexity = O(N*subArrayLen*subArraySum)
   static int dp[][][][];

   static int solveWithMemo(int[] nums, int i, int prevPick, int subArrayLen, int subArraySum) {
      if (i == n) {
         return prevPick == 1 && (subArrayLen % 2 != 0) ? subArraySum : 0;
      }

      if (dp[i][prevPick][subArrayLen][subArraySum] != -1) {
         return dp[i][prevPick][subArrayLen][subArraySum];
      }

      if (prevPick == 1) {
         int take = solve(nums, i + 1, 1, subArrayLen + 1, nums[i] + subArraySum);

         int stop = (subArrayLen % 2 != 0) ? subArraySum : 0;
         return dp[i][prevPick][subArrayLen][subArraySum] = (take + stop);
      } else {
         int takeNewFromCurr = solve(nums, i + 1, 1, subArrayLen + 1, subArraySum + nums[i]);
         int takeNewFromNext = solve(nums, i + 1, 0, 0, 0);
         return dp[i][prevPick][subArrayLen][subArraySum] = (takeNewFromCurr + takeNewFromNext);
      }
   }

   // bottom up

   // time complexity = O(N*subArraySum + N*subArrayLen*subArraySum)
   // space complexity = O(N*subArrayLen*subArraySum)

   static int solveWithBottomUp(int[] nums) {

      for (int prevPick = 0; prevPick < 2; prevPick++) {
         for (int subArrayLen = 0; subArrayLen <= n; subArrayLen++) {
            for (int subArraySum = 0; subArraySum <= 100; subArraySum++) {
               dp[n][prevPick][subArrayLen][subArraySum] = (prevPick == 1 && subArrayLen%2 != 0 )? subArraySum : 0;
            }
         }
      }

      for (int i = n-1; i >= 0; i--) {
         for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int subArrayLen = 0; subArrayLen <= n; subArrayLen++) {
               for (int subArraySum = 0; subArraySum <= 100; subArraySum++) {
                  if (prevPick == 1) {

                     int newLen = subArrayLen+1;
                     int newSum = subArraySum+nums[i];

                     int take = (newLen <= n  && newSum <= 1000 ) ? dp[i+1][1][newLen][newSum] : 0;

                     int stop = (subArrayLen % 2 != 0) ? subArraySum : 0;
                      dp[i][prevPick][subArrayLen][subArraySum] = (take + stop);
                  } else {
                     int takeNewFromCurr = dp[i+1][1][1][nums[i]];
                     int takeNewFromNext = dp[i+1][0][0][0];
                      dp[i][prevPick][subArrayLen][subArraySum] = (takeNewFromCurr + takeNewFromNext);
                  }
               }
            }
         }
      }
   return dp[0][0][0][0];
   }

      //Space Optimization

      // time complexity = O(N*subArraySum + N*subArrayLen*subArraySum)
     // space complexity = O(2*subArrayLen*subArraySum)


      static int solveWithSpaceOptimization(int[] nums) {

         int next[][][] = new int[2][n+1][1000]; // i+1 th table

         for(int i = 0 ; i<2 ; i++){
            for(int j = 0 ; j<=n;j++){
               Arrays.fill(next[i][j], -1);
            }
         }

      for (int prevPick = 0; prevPick < 2; prevPick++) {
         for (int subArrayLen = 0; subArrayLen <= n; subArrayLen++) {
            for (int subArraySum = 0; subArraySum <= 100; subArraySum++) {
               next[prevPick][subArrayLen][subArraySum] = (prevPick == 1 && subArrayLen%2 != 0 )? subArraySum : 0;
            }
         }
      }

      for (int i = n-1; i >= 0; i--) {

         int curr[][][] = new int[2][n+1][1000]; // ith table

         for(int j = 0 ; j<2 ; j++){
            for(int k = 0 ; k<=n;k++){
               Arrays.fill(curr[j][k], -1);
            }
         }

         for (int prevPick = 0; prevPick < 2; prevPick++) {
            for (int subArrayLen = 0; subArrayLen <= n; subArrayLen++) {
               for (int subArraySum = 0; subArraySum <= 100; subArraySum++) {
                  if (prevPick == 1) {

                     int newLen = subArrayLen+1;
                     int newSum = subArraySum+nums[i];

                     int take = (newLen <= n  && newSum <= 1000 ) ? next[1][newLen][newSum] : 0;

                     int stop = (subArrayLen % 2 != 0) ? subArraySum : 0;
                      curr[prevPick][subArrayLen][subArraySum] = (take + stop);
                  } else {
                     int takeNewFromCurr = next[1][1][nums[i]];
                     int takeNewFromNext = next[0][0][0];
                      curr[prevPick][subArrayLen][subArraySum] = (takeNewFromCurr + takeNewFromNext);
                  }
               }
            }
         }
         next = curr;
      }
   return next[0][0][0];
   }

   public static void main(String[] args) {
      int nums[] = { 1, 2 };

      n = nums.length;

      dp = new int[n + 1][2][n + 1][10000];

      for (int i = 0; i <= n; i++) {
         for (int j = 0; j < 2; j++) {
            for (int k = 0; k <= n; k++) {
               Arrays.fill(dp[i][j][k], -1);
            }
         }
      }

      int ans = solveWithSpaceOptimization(nums);
      System.out.print(ans);
   }
}
