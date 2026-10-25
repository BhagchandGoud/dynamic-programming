import java.util.*;


// hacker earth problem accepted
public class BennyAndTheBrokenOdometer {
    
    static int dp[][][];

    static int solve(String s , int i , int tight , int found){
        if(i == s.length()){
            return found == 1 ? 1 : 0;
        }

        if(dp[i][tight][found] != -1){
            return dp[i][tight][found];
        }

        int limit = tight == 1 ? s.charAt(i)-'0' : 9 ;
        int ans = 0;

        for(int digit = 0 ; digit <= limit ; digit++){
            int newFound = found ;
             if(digit == 3){
                newFound = 1;
             }
            int newTight = tight == 1 && digit == limit ? 1 : 0;

            ans += solve(s, i+1, newTight, newFound);
        }
        return dp[i][tight][found] = ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t > 0) {
            int n = sc.nextInt();

            String s = String.valueOf(n);

            dp = new int[s.length()+1][2][2];

            for(int i = 0 ; i < s.length()+1 ; i++){
                for(int j = 0 ; j < 2 ; j++){
                    Arrays.fill(dp[i][j] , -1);
                }
            }


            int Ans = solve(s , 0 ,1, 0);

            System.out.println(n -Ans);

            t-- ;

        }

        sc.close();
    }
}
