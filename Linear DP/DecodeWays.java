/**
 * DecodeWays
 */
public class DecodeWays {

    static int n;

    static int solve(String s , int i){
        if(i >= n){
            return 1;
        }
        if(s.charAt(i) == '0'){
            return 0;
        }
        int count = solve(s , i+1);
        if(i+1 < n && ((s.charAt(i) == '1') || (s.charAt(i) >= '2' && s.charAt(i+1) <= '6'))){
            count += solve(s , i+2);
        }
        return count;
    }

    public static void main(String []args){
        String s = "12";
        n = s.length();
        int ans = solve(s , 0);
        System.out.println(ans);
    }
    
}