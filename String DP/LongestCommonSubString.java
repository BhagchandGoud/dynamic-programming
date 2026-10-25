public class LongestCommonSubString {
    static Integer dp[][][];
    static int n , m;

    // solve using subaarry template code accepted gfg without tle
    
    static int solveWithMemo(String s1 , String s2 , int i , int j , int prevPick){
        if(i >= n || j >= m){
            return prevPick == 1 ?  0 : Integer.MIN_VALUE;
        }
        if(dp[i][j][prevPick] != null){
            return dp[i][j][prevPick];
        }

        if(prevPick == 1){
            int start = (s1.charAt(i) == s2.charAt(j)) ? 1 + solveWithMemo(s1, s2, i+1, j+1, 1) : 0;
            int stop = 0;
            return dp[i][j][prevPick] = Math.max(start, stop);
        }else{
            int startNewFromCurr = (s1.charAt(i) == s2.charAt(j))  ? 1 + solveWithMemo(s1, s2, i+1, j+1, 1) : 0;
            int startNewFromNext1 = solveWithMemo(s1, s2, i+1, j, 0);
            int startNewFromNext2 = solveWithMemo(s1, s2, i, j+1, 0);
            return dp[i][j][prevPick] = Math.max(startNewFromCurr, Math.max(startNewFromNext2, startNewFromNext1));
        }
    }


    // solve using another method accepted gfg without tle

    static int getMatLen(String s1, String s2, int i, int j) {
		int len = 0;
		
		while ((i < n && j < m)) {
			if (s1.charAt(i) == s2.charAt(j)) {
				
				len++;
				i++;
				j++;
			}else{
			    break;
			}
		}
		return len;
	}





    
    static int longCommSubstr(String s1, String s2) {
        n = s1.length();
        m = s2.length();

        // using dp
        dp = new Integer[n+1][m+1][2];
        int ans2 = solveWithMemo(s1, s2, 0, 0, 0);

        // using loops

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              res = Math.max(res , getMatLen(s1, s2, i, j));  
            }
            
        }
        return res;
    }
    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";

        int ans = longCommSubstr(s1,s2);
        System.out.println(ans);
    }
}
