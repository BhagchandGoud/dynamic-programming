import java.util.Arrays;
import java.util.Scanner;

public class DiceCombination {
    static int n;

    static int MOD = (int)1e9+7;
    static long dp[][];

    static long solve(int[] nums, int i, int k) {
        if (k == 0) {
            return 1;
        }
        if ( k < 0 || i >= n) {
            return 0;
        }

        if(dp[i][k] != -1){
            return dp[i][k];
        }

        long take = solve(nums, 0 , k - nums[i]); // why take i = 0  -> After every dice throw, you should again be allowed to throw any face (1–6). Resetting i to 0 gives you all six choices again.
        /*
          for example : -> [1,2,3];
          n = 3
          combiantion are : [1+1+1], [1 + 2] ,[2 + 1] , [3]
          we are reseeting to make choice again make 6 choice to get [2 + 1]  , because this is also possible 

          If after choosing one item, you are allowed to choose any item again (including previous ones), 
          reset; if future choices are restricted to avoid duplicate orders, don't reset.

          Even shorter: - > Order matters → reset. Order doesn't matter → don't reset.
        */

          /*
            problem says : Your task is to count the number of ways to construct sum n by
             throwing a dice one or more times. 
            Each throw produces an outcome between 1 and  6.
            

            problems says  : can throw dice one or more times 
          */
        long skip = solve(nums, i + 1, k);

        return dp[i][k] =  (take + skip)%MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = { 1, 2, 3, 4, 5, 6 };

        n = nums.length;
        int k = sc.nextInt();


        dp = new long[n][k+1];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        long ans = solve(nums, 0, k);
        System.out.println(ans%MOD);
        sc.close();
    }
}
