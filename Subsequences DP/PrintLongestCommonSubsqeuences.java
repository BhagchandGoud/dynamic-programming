import java.util.*;

public class PrintLongestCommonSubsqeuences {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int []arr1 = new int[n];
        int []arr2 = new int[m];

        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }


        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
       ArrayList<Integer>ans = new ArrayList<>();

        int i = n , j = m;

        while(i > 0 && j > 0){
            if(arr1[i-1] == arr2[j-1]){
                ans.add(arr1[i-1]);
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        Collections.reverse(ans);
        
        int x = ans.size();

        System.out.println(x);

        for(int k = 0 ; k< x ; k++){
           System.out.print(ans.get(k) + " ");
        }

        sc.close();
    }
}
