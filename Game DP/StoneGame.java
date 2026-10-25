public class StoneGame {
    // using recursion
    static int solve_Using_recursion(int []nums , int i , int j , int isPlayer){
        if(i > j){
            return 0;
        }

        if(isPlayer == 1){
            int take1 = nums[i] + solve_Using_recursion(nums , i+1 , j , 0);
            int take2 = nums[j] + solve_Using_recursion(nums , i , j-1 , 0);
            return Math.max(take1 , take2);
        }else{
            int take1 =  solve_Using_recursion(nums , i+1 , j , 1);
            int take2 =  solve_Using_recursion(nums , i , j-1 , 1);
            return Math.min(take1 , take2);
        }
    }

    Integer dp[][][];
    int solve_Using_memo(int []nums , int i , int j , int player){
        if(i > j){
            return 0;
        }

        if(dp[i][j][player] != null){
            return dp[i][j][player];
        }

        if(player == 0){
            int take_from_start = nums[i] + solve_Using_memo(nums , i+1 , j , 1);
            int take_from_end = nums[j] + solve_Using_memo(nums , i , j-1 , 1);
            return dp[i][j][player] = Math.max(take_from_start , take_from_end);
        }else{
            int take_from_start = solve_Using_memo(nums , i+1 , j , 0);
            int take_from_end =   solve_Using_memo(nums , i , j-1 , 0);
            return dp[i][j][player] = Math.min(take_from_start , take_from_end);
        }
    }
    public boolean stoneGame(int[] piles) {
        dp = new Integer[piles.length + 1][piles.length + 1][2];
        int alice =  solve_Using_memo(piles , 0 ,piles.length-1 , 0);
        int sum = 0;

        for(int i = 0 ; i < piles.length ; i++){
            sum += piles[i];
        }

        int bob = sum - alice;
        return alice >= bob;
    }
}
