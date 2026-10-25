public class StoneGame2 {
    
    static int n;
    static Integer dp[][][];
    static int solve(int person, int i, int M, int piles[]) {
        if (i >= n) {
            return 0;
        }
        if(dp[i][M][person] != null){
            return dp[i][M][person];
        }
        int res = person == 1 ? 0 : Integer.MAX_VALUE;

        int stones = 0;

        for (int x = 1; x <= Math.min(n - i, 2 * M); x++) {

            if (person == 1) {
                stones += piles[i + x - 1];
                int a = solve(0, i + x, Math.max(x, M), piles);
                res = Math.max(stones + a, res);
            } else {
                int a = solve(1, i + x, Math.max(x, M), piles);
                res = Math.min(a, res);
            }
        }

        return dp[i][M][person] = res;
    }

    static int stoneGameII(int[] piles) {
        n = piles.length;
        dp = new Integer[n+1][2*n][2];
        return solve(1, 0, 1, piles);
    }

    public static void main(String[] args) {
        int piles[] = {2,7,9,4,4};

        int x = stoneGameII(piles);
        System.out.println(x);
    }

}
