
// PROBOLEM Says : ->
// In how many ways can I insert one character into string A so that the LCS of A and B increases by exactly 1 ?

// Full problem available on -> HACKERANK -> problem name - > LCS RETURN

public class Both_Prefix_LCS_And_Suffix_LCS_Combination {

    static int[][] PrfixLCS(String s1, String s2, int n, int m) {
        // start from start to end

        // this is exact bottom up of lcs dp

        int[][] prefix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    prefix[i][j] = 1 + prefix[i - 1][j - 1];
                } else {
                    prefix[i][j] = Math.max(prefix[i - 1][j], prefix[i][j - 1]);
                }
            }
        }

        return prefix;
    }

    static int[][] SuffixLCS(String s1, String s2, int n, int m) {
        // start from end to start

        // this is exact bottom up of lcs dp

        int[][] suffix = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    suffix[i][j] = 1 + suffix[i + 1][j + 1];
                } else {
                    suffix[i][j] = Math.max(suffix[i + 1][j], suffix[i][j + 1]);
                }
            }
        }

        return suffix;
    }

    public static void main(String[] args) {
        String s1 = "aa";
        String s2 = "baaa";

        int n = s1.length();
        int m = s2.length();

        int prefix[][] = PrfixLCS(s1, s2, n, m);
        int suffix[][] = SuffixLCS(s1, s2, n, m);

        int lcs = prefix[n][m];

        long ans = 0;

        for (int pos = 0; pos <= n; pos++) {

            boolean isUsed[] = new boolean[26];

            for (int j = 0; j < m; j++) {
                int ch = s2.charAt(j) - 'a';

                if (isUsed[ch]) {
                    continue;
                }

                if (prefix[pos][j] + 1 + suffix[pos][j + 1] == lcs + 1) {
                    ans++;
                    isUsed[ch] = true;
                }
            }
        }

        System.out.println(ans);
    }
}

// types of problems can be solved

// 1 -> Insert one character
// 2 -> Replace one character
// 3 -> Delete one character
// 4 -> Count important characters
// 5 -> Number of ways to obatians LCS
// 6 -> One modification problems
// 7 -> String contribution problems

// General template : ->
// ______________________

// calculate prefix lcs and store in prefix 2d array
// int [][]prefix = PrefixLcs(String s1 , String s2 , int n , int m) // where n
// = s1.length & m = s2.length

// calculate suffix lcs and store in suffix 2d array
// int [][]suffix = SuffixLcs(String s1 , String s2 , int n , int m) // where n
// = s1.length & m = s2.length

// check the formula according to problem to solve
// Insert:
// prefix[i][j] + 1 + suffix[i][j+1]

// Replace:
// prefix[i][j] + 1 + suffix[i+1][j+1]

// Delete:
// prefix[i][j] + suffix[i+1][j]

// If a string DP problem contains:

// LCS + one insertion/deletion/replacement + many queries or many positions

/*
 * # problem 1 : ->
 * 
 * You have typed a message incorrectly and are allowed to insert exactly one
 * character anywhere in the message. You want to know how many different
 * insertions would make your message more similar to the original message by
 * increasing the LCS by exactly one. Computing the LCS from scratch after every
 * possible insertion would be too slow, so you use Prefix LCS to know how much
 * matches before the insertion point and Suffix LCS to know how much matches
 * after it.
 * 
 * # problem 2 :->
 * 
 * You are editing DNA sequences. One DNA strand contains a wrong nucleotide.
 * You are allowed to replace exactly one character and want to know the best
 * possible similarity with another DNA strand. Here the replacement happens in
 * the middle. Prefix LCS gives the similarity before the replaced position, the
 * replacement contributes one matched character, and Suffix LCS gives the
 * similarity after the replacement.
 * 
 * # problem 3 :->
 * 
 * Consider a spell-checking system. A word contains one extra character due to
 * a typing mistake. You want to know whether removing a specific character will
 * improve the similarity with a dictionary word. The character itself is
 * ignored, so the answer becomes the contribution before the character plus the
 * contribution after it. This is a delete-character problem.
 * 
 * # problem 4 :->
 * 
 * Imagine two versions of a document. The company wants to identify which
 * characters are "important" because they belong to at least one optimal common
 * subsequence. For each matching position, you check whether the best answer
 * can pass through that character. Prefix LCS tells how much matching can
 * happen before it, and Suffix LCS tells how much can happen after it.
 * 
 * # problem 5 :->
 * 
 * Two programmers edited the same file. The system wants to know whether adding
 * one missing character would make the files align better. Instead of
 * recomputing LCS for every possible insertion, it combines the matching before
 * and after the insertion position.
 * 
 */
