import java.util.Arrays;

public class CountNumberOfTeams {
    static int n;
    static int dp1[][][] , dp2[][][];

    // find increasing subsequences

    static int Increasing(int []nums , int i , int prevIdx , int k){
        if(k == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }
        if(dp1[i][prevIdx+1][k] != -1){
            return dp1[i][prevIdx+1][k];
        }

        int take = 0;

        if(prevIdx == -1 || nums[prevIdx] < nums[i]){
            take = Increasing(nums, i+1, i, k-1);
        }

        int skip = Increasing(nums, i+1, prevIdx, k);

        return dp1[i][prevIdx+1][k] =  take + skip;
    }

    // find decreasing subsequences

    static int Decreasing(int []nums , int i , int prevIdx , int k){
        if(k == 0){
            return 1;
        }
        
        if(i >= n){
            return  0;
        }

        if(dp2[i][prevIdx+1][k] != -1){
            return dp2[i][prevIdx+1][k];
        }
        
        int take = 0;

        if(prevIdx == -1 || nums[prevIdx] > nums[i]){
            take = Decreasing(nums, i+1, i, k-1);
        }

        int skip = Decreasing(nums, i+1, prevIdx, k);

        return dp2[i][prevIdx+1][k] = take + skip;
    }
    static int numTeams(int[] rating) {
        n = rating.length;

        dp1 = new int[n+1][n+1][4];
        dp2 = new int[n+1][n+1][4];

        for(int i = 0 ; i <= n ; i++){
            for(int j = 0 ; j <= n  ; j++){
                Arrays.fill(dp1[i][j] , -1);
                Arrays.fill(dp2[i][j] , -1);
            }
        }

        return Increasing(rating , 0 , -1 , 3) + Decreasing(rating , 0 , -1 , 3);
    }

    public static void main(String[] args) {
        int[] rating = {2,5,3,4,1};

        int ans = numTeams(rating);

        System.out.println(ans);
    }
}
