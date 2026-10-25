import java.util.HashMap;
import java.util.Map;

public class NumbersWithRepeatedDigits {
    // TLE 
    // it can be pass using bit masks with digit dp without storing repeated digit in hashmap
    // tle reason -> hashmap traversing taking time
    static boolean isRepeated(int num) {

        Map<Integer, Integer> map = new HashMap<>();

        while (num > 0) {
            int digit = num % 10;

            map.put(digit, map.getOrDefault(digit, 0) + 1);

            num /= 10;
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > 1) {
                return true;
            }
        }

        return false;
    }

    static int solve(String s , int i , int tight ,int num){
        if(i >= s.length()){
            return isRepeated(num) ? 1 : 0;
        }

        int limit = tight == 1 ? s.charAt(i)-'0' : 9;

        int ans = 0;

        for(int digit = 0 ; digit <= limit ; digit++){
            int newTight = tight == 1 && digit == s.charAt(i)-'0' ? 1 : 0;
            int newNum = num *10+digit;;

            ans += solve(s, i+1, newTight , newNum);
        }
        return ans;
    }
    

    static int numDupDigitsAtMostN(int n) {
        String s = String.valueOf(n);
    return solve(s , 0 , 1 , 0);
    }

    public static void main(String[] args) {
        int n = 20;
        int ans = numDupDigitsAtMostN(n);
        System.out.println(ans);
    }
}
