import java.util.Arrays;

public class MinimumSumPartition {

    static int n;
    static int dp[][];


    static int solve(int nums[] , int i , int target){
        
        if(i >= n){
            return target == 0 ? 1 : 0;
        }
        if(target < 0){
            return 0;
        }
            
        int take = 0;
        
        if(nums[i] <= target){
            take = solve(nums , i+1 , target-nums[i]);
        }
        int skip = solve(nums, i+1 , target);
        
        return take == 1 || skip == 1 ? 1 : 0;
        
    }
    
    static int solveWithMemo(int nums[] , int i , int target){
        
        if(i >= n){
            return target == 0 ? 1 : 0;
        }
        if(target < 0){
            return 0;
        }
        if(dp[i][target] != -1){
            return dp[i][target];
        }
        
        
        
        int take = 0;
        
        if(nums[i] <= target){
            take = solveWithMemo(nums , i+1 , target-nums[i]);
        }
        int skip = solveWithMemo(nums, i+1 , target);
        
        return dp[i][target] = take == 1 || skip == 1 ? 1 : 0;
        
    }


    public static void main(String[] args) {
        int arr[] = {1, 6, 11, 5};

          n = arr.length;
       
       int sum = 0;
       
       for(int num : arr){
           sum += num;
       }
       
       dp = new int[n][sum/2 + 1];
       
       for(int i = 0 ; i<n;i++){
           Arrays.fill(dp[i] , -1);
       }
       int result = Integer.MAX_VALUE;
       
       for(int subSet1Sum = 0 ; subSet1Sum <= sum/2; subSet1Sum++){
           
           if(solveWithMemo(arr ,0, subSet1Sum ) == 1){
               int subSet2Sum = sum - subSet1Sum;
               result = Math.min(result , Math.abs(subSet1Sum - subSet2Sum));
           }
       }

       System.out.println(result);
    }
}
