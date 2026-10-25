public class StoneGame3 {
    static int n;
    static Integer dp[][];
    static int solve(int[] nums, int i, int isPlayer) {
        if (i >= n) {
            return 0;
        }
        if(dp[i][isPlayer] != null){
            return dp[i][isPlayer];
        }
        if (isPlayer == 1) {
            int take1 = nums[i] + solve(nums, i + 1 ,0);
            int take2 = Integer.MIN_VALUE;
            if (i + 1 < n) {
                take2 = nums[i] + nums[i + 1] + solve(nums, i + 2 , 0);
            }
            int take3 = Integer.MIN_VALUE;
            if (i + 2 < n) {
                take3 = nums[i] + nums[i + 1] + nums[i + 2] + solve(nums, i + 3 , 0);
            }
            return dp[i][isPlayer] = Math.max(Math.max(take1, take2), take3);
        } else {
            int take1 = solve(nums, i + 1 , 1);
            int take2 = Integer.MAX_VALUE;
            if (i + 1 < n) {
                take2 = solve(nums, i + 2 , 1);
            }
            int take3 = Integer.MAX_VALUE;
            if (i + 2 < n) {
                take3 = solve(nums, i + 3 ,1);
            }
            return dp[i][isPlayer] = Math.min(Math.min(take1, take2), take3);
        }
    }

    static String stoneGameIII(int[] piles) {
        n = piles.length;
        dp = new Integer[n+1][2];
        int alice = solve(piles, 0 , 1);
        int total = 0;

        for (int s : piles) {
            total += s;
        }

        int bob = total - alice;

        if (alice > bob) {
            return "Alice";
        } else if (alice < bob) {
            return "Bob";
        }
        return "Tie";
    }
    public static void main(String[] args) {
        int piles[] = {1,2,3,7};

        String ans = stoneGameIII(piles);
        System.out.println(ans);
    }
}