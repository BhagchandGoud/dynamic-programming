import java.util.Arrays;

public class LongestBitonicSubsequence {
 
    
// longest bitoinc subsquence problem available of gfg
// leetcode -> minimum removal to make mountain  -> find length longest bitoinc subsquence and subtract from (n - length of longest bitoinc subsquence) final answer
public static void main(String[] args) {
    int[]nums = {5 ,7, 9};
    int n = nums.length;

    int[] LIS = new int[n]; // Longest increasing subseuence length store 
    int[] LDS = new int[n]; // Longest decreasing subseuence length store
    Arrays.fill(LIS , 1);
    Arrays.fill(LDS , 1);

    for(int i = 0 ; i <n ; i++){
        for(int prev = 0 ; prev < i ; prev++ ){
            if(nums[prev] < nums[i]){
                LIS[i] = Math.max(LIS[i] , LIS[prev]+1);
            }
        }
    }

    // longest decreasing subsequece -> start from end of nums 
    for(int i = n-1 ; i >= 0 ; i--){
        for(int prev = n-1 ; prev >= i ; prev-- ){
            if(nums[prev] < nums[i]){
                LDS[i] = Math.max(LDS[i] , LDS[prev]+1);
            }
        }
    }
 
    int res = 0;

    for(int i  = 0  ; i<n ; i++){
        int lenOfBitonic  = LIS[i] + LDS[i] - 1;
        res = Math.max(lenOfBitonic , res);
    }
    System.out.println(res);
}

}