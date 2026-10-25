// import java.util.*;

public class LongestPalindromeSubstring {
    static int n;

    static boolean isPalindrome(String str) {

        int i = 0;
        int j = str.length() - 1;

        while (i < j) {

            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    // using recursion

    static String solve(String s, int prevPick, int i, String newStr) {
        if (i >= n) {
            return prevPick == 1 && isPalindrome(newStr) ? newStr : "";
        }

        if (prevPick == 1) {
            String take = solve(s, 1, i + 1, newStr + s.charAt(i));
            String stop = isPalindrome(newStr) ? newStr : "";

            return take.length() >= stop.length() ? take : stop;
        } else {
            String takeNewFromCurr = solve(s, 1, i + 1, "" + s.charAt(i));
            String takeNewFromNext = solve(s, 0, i + 1, "");

            return takeNewFromCurr.length() >= takeNewFromNext.length() ? takeNewFromCurr : takeNewFromNext;
        }
    }

    // can not use memoization and bottom up because dp have 3 states are changing but
    // newStr state is string and others are integer

    public static void main(String[] args) {
        String s = "babad";
        n = s.length();

        String ans = solve(s, 0, 0, "");

        System.out.println(ans);
    }
}