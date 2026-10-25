public class PalindromicSubstrings {
    
    static boolean isPalindrome(String s){
        int i = 0 , j = s.length()-1;
        
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    static int n;

    static int solve(String s , int prevPick , int i , String curr){
        if(i >= n){
            return prevPick == 1 && isPalindrome(curr) ? 1 : 0;
        }

        if(prevPick == 1){
            int start = solve(s , 1 , i+1 , curr + s.charAt(i));
            int stop = isPalindrome(curr) ? 1 : 0;

            return start + stop;
        }
        else{
            int start = solve(s , 1 , i+1 , "" + s.charAt(i));
            int stop = solve(s , 0 , i+1 , "");

            return start + stop;
        }
    }


    static int countSubstrings(String s) {
        n = s.length();

        return solve(s , 0 ,0 , "");
    }

    public static void main(String[] args) {
        String s = "racecar";

        int ans = countSubstrings(s);
        System.out.println(ans);
    }
}
