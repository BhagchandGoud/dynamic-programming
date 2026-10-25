
import java.util.Arrays;

public class LongestStringChain {
    
    static int n;


    static boolean predecessor (String a , String b){
        if(a.length()+1 != b.length()){
            return false;
        }

        int i = 0 ;
        int j = 0;

        while(i < a.length() && j < b.length()){
            if(a.charAt(i) == b.charAt(j)){
                i++;  
            }
            j++;
        }
        return i == a.length() ? true : false; 
    }

    static int solve(String[]words , int prevIdx , int i){
        if(i >= n){
            return 0;
        }

        int take = 0;
        if(prevIdx == -1 || predecessor(words[prevIdx] , words[i])){
            take = 1 + solve(words , i , i+1);
        }
        int skip = solve(words , prevIdx , i+1);

        return Math.max(take , skip);
    }

    static int dp[][];
    static int solveWithMemo(String[]words , int prevIdx , int i){
        if(i >= n){
            return 0;
        }

        if(dp[i][prevIdx+1] != -1){
            return dp[i][prevIdx+1];
        }

        int take = 0;
        if(prevIdx == -1 || predecessor(words[prevIdx] , words[i])){
            take = 1 + solveWithMemo(words , i , i+1);
        }
        int skip = solveWithMemo(words , prevIdx , i+1);

        return dp[i][prevIdx+1] = Math.max(take , skip);
    }

    static int longestStrChain(String[] words) {
        n = words.length;

        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

        dp = new int[n][n+1];

        for(int i = 0 ; i < n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        return solveWithMemo(words , -1 , 0);
    }

    public static void main(String[] args) {
        String []words = {"a","b","ba","bca","bda","bdca"};
        int ans = longestStrChain(words);
        System.out.println(ans);
    }
}
