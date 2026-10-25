

public class UniqueBinarySearchTree {

    static Integer dp[][];

    static int solve(int low , int high){
        if(low > high){
            return 1;
        }
        if(dp[low][high] != null){
            return dp[low][high];
        }

        int ans = 0;

        for(int i = low ; i <= high ; i++){
            int left = solve(low  , i-1);
            int right = solve(i+1 , high);
            int take = (left * right);
            ans += take;
        }
        return dp[low][high] = ans;
    }
    static int numTrees(int n) {
        dp = new Integer[n+1][n+1];
        return solve(1 ,n);
    }
    public static void main(String[] args) {
        int n = 3;
        int ans = numTrees(n);
        System.out.println(ans);
    }
}