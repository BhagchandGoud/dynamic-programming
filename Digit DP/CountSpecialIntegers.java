import java.util.*;


public class CountSpecialIntegers {

    static int dp[][][];

    static int solve(String s , int i , int tight ,int started , boolean []isUsed){
        if(i >= s.length()){
            return started == 1 ? 1 : 0;
        }

        int ans = 0;

        int limit = tight == 1 ? s.charAt(i)-'0' : 9;

        for(int j = 0 ; j <= limit ; j++){
            int newTight = tight == 1 && j == (s.charAt(i)-'0') ? 1 : 0;

            if(started == 0 && j == 0){
                ans += solve(s , i+1 , newTight , 0 , isUsed);
                continue;
            }
            if(isUsed[j]){
                continue;
            }
                isUsed[j] = true;
                ans += solve(s , i+1 , newTight , 1 , isUsed);
                isUsed[j] = false;
        }
        return ans;
    }
    static int countSpecialNumbers(int n) {
        String s = String.valueOf(n);

     
     return solve(s , 0 ,1,0, new boolean[10]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ans = countSpecialNumbers(n);
        System.out.println(ans);
        sc.close();

    }
}