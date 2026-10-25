public class PrintLongestCommonSubstring {

    static int n , m;

    static int solve(String s1, String s2, int i, int j) {
        if (n <= i || m <=  j) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + solve(s1, s2, i+1, j+1);
        }
        return 0;
    }

    public static void main(String[] args) {
        String s1 = "abcdgh";
        String s2 = "acdghr";

         n = s1.length();
         m = s2.length();

        int max = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int len = solve(s1, s2, i, j);

                if (len > max) {
                    max = len;
                    start = i;
                }
            }
        }

        String ans = s1.substring(start , max+start);
        System.out.println(ans);
    }
}
