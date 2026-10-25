import java.math.BigInteger;
import java.util.*;

// this is not usefull method can pass only 0 < 10^6/ 10^7 easily 

public class PalindromicNumbers{

    static long dp[][][];

    static boolean isPalindrome(String s){
       int i = 0 ; 
       int j = s.length()-1;

       while (i <= j) {
         if(s.charAt(i) != s.charAt(j)){
            return false;
         }
         else{
            i++;
            j--;
         }
       }
       return true;
    }

    

    static long solve(String s , int i , int tight ,int started ,  String curr){
        if(i >= s.length()){
            return started == 1 && isPalindrome(curr) ? 1 : 0;
        }

        int limit = tight == 1 ? s.charAt(i)-'0' : 9;
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && digit == (s.charAt(i)-'0')? 1 : 0;

            if(started == 0 && digit == 0){
                ans += solve(s, i+1, newTight, 0, "");
                continue;
            }

            String next = curr + digit;

            ans += solve(s, i+1, newTight, 1, next);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            
            BigInteger n1 = sc.nextBigInteger();
            BigInteger n2 = sc.nextBigInteger();
            if(n1.compareTo(n2) > 0){
                 BigInteger temp = n1;
                 n1= n2;
                 n2 = temp;
            }
            String l = n1.subtract(BigInteger.ONE).toString();
            String r = String.valueOf(n2);

            long left = solve(l, 0, 1, 0, "");

            long right = solve(r, 0, 1, 0 ,"");

            
            System.out.println(right - left);

        }

        sc.close();
        
    }
} 