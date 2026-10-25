import java.util.Arrays;
import java.util.Scanner;


// this question is combination of subArray and subSquences dp

// problem available on hacker rank 

public class TheMaximumSubarray {
    

    static int m;

    // find subarray maximum sum
    static int dp1[][][];
    
    static int subArray(int []nums , int prevPick , int i , int hasPicked){
        if(i >= m){
            return hasPicked == 1 ? 0 : Integer.MIN_VALUE;
        }
        
        if(dp1[i][prevPick][hasPicked] != -1){
            return dp1[i][prevPick][hasPicked];
        }
        if(prevPick == 1){
            int start = nums[i] + subArray(nums, 1, i+1 , 1);
            int stop = 0;
            return dp1[i][prevPick][hasPicked] = Math.max(start , stop);
        }
        else{
            int startNewFromCurr = nums[i] + subArray(nums, 1, i+1 , 1);
            int starteNewFromNext = subArray(nums, 0, i+1 , hasPicked);
            
            return dp1[i][prevPick][hasPicked] = Math.max(startNewFromCurr , starteNewFromNext);
        }
    }

    // maximum subarray sum using bottom up approach

    static long solveWithBottomUp(int nums[] , int n){
		long dp[][] = new long[n+1][2];
 
		
		for(int i = n-1 ; i>=0 ; i--){
			for(int prevPick =  0 ; prevPick < 2 ; prevPick++){
				if(prevPick == 1){
			        long start = nums[i] + dp[i+1][1];
			        long stop = 0;
			        dp[i][1] = Math.max(stop ,start);
		        }
		        else{
			        long startNewFromCurr = nums[i] + dp[i+1][1];
			        long startNewFromNext = dp[i+1][0];
			        dp[i][0] = Math.max(startNewFromCurr , startNewFromNext);
		        }
			}
		}

		return dp[0][0];
	}
    

    // find subsequences with maximum sum

    static int dp2[][];
    
    static int subSequences(int []nums , int i , int hasPicked){
        if(i >= m){
            return hasPicked == 1 ? 0 : Integer.MIN_VALUE;
        }
        if(dp2[i][hasPicked] != -1){
            return dp2[i][hasPicked];
        }
        int take = nums[i] + subSequences(nums, i+1 , 1);
        int skip = subSequences(nums, i+1 , hasPicked);
        
        return dp2[i][hasPicked] = Math.max(take , skip);
    }
    
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
         while(t > 0){
            int n = sc.nextInt();
            m = n;
            
            int arr[] = new int[n];
            
            for(int i = 0 ; i< n ; i++){
                arr[i] = sc.nextInt();
            }
            
            dp1 = new int[n][2][3];
            
            for(int i = 0 ; i <n ; i++){
                for(int j = 0 ; j < 2 ; j++){
                    Arrays.fill(dp1[i][j] , -1);
                }
            }
            
            dp2 = new int[n][3];
            for(int i = 0 ; i < n ; i++){
                Arrays.fill(dp2[i] , -1);
            }
            
            int subArrayAns = subArray(arr, 0, 0 , 0);
            int subSequenceAns = subSequences(arr , 0 , 0);
            
            System.out.println(subArrayAns + " " + subSequenceAns);
            t--;
         }

         sc.close();
    }
}
