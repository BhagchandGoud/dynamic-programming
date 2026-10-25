import java.util.Scanner;

class Pair{
    long count;
    long totalWaveCount;
    Pair(long count , long totalWaveCount){
        this.count = count;
        this.totalWaveCount = totalWaveCount;
    }
}
// time and space complexity of memoization approach 
// // space complexity = > 20*2*2*20*20 + 20*2*2*20*20  = O(1)
// time complexity = >  (20*2*2*20*20 + 20*2*2*20*20) * N *digit = O(N * digit) 


// Total Waviness Of Numbers In Range first also accepted using this code

public class TotalWavinessOfNumbersInRangeSecond {

    static Pair solve(String s, int i, int tight, int started, int prevPrev, int prev) {
        if (i >= s.length()) {
            return started == 1 ? new Pair(1, 0) : new Pair(0, 0);
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && digit == s.charAt(i) - '0' ? 1 : 0;
            if (digit == 0 && started == 0) {
                Pair ans = solve(s, i + 1, newTight, 0, -1, -1);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }
            if (started == 0) {
                Pair ans = solve(s, i + 1, newTight, 1, -1, digit);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }

            if (prevPrev == -1) {
                Pair ans = solve(s, i + 1, newTight, 1, prev, digit);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }

            int extra = 0;

            if ((prevPrev < prev && prev > digit) || (prevPrev > prev && prev < digit)) {
                
                extra = 1;
            }
            Pair ans = solve(s, i + 1, newTight, 1, prev, digit);

            totalCount += ans.count;
            totalWave += extra *ans.count +  ans.totalWaveCount;
        }
        Pair ans = new Pair(totalCount, totalWave);
        return ans;
    }


    // solve using memoization/dp

    static Pair dp[][][][][];
    static boolean[][][][][] vis;
    
    static Pair solveWithMemoPair(String s, int i, int tight, int started, int prevPrev, int prev) {
        if (i >= s.length()) {
            return started == 1 ? new Pair(1, 0) : new Pair(0, 0);
        }

        if(tight == 0 && vis[i][started][tight][prevPrev+1][prev+1]){
            return dp[i][started][tight][prevPrev+1][prev+1];
        }

        int limit = tight == 1 ? s.charAt(i) - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight == 1 && digit == s.charAt(i) - '0' ? 1 : 0;
            if (digit == 0 && started == 0) {

                Pair ans = solveWithMemoPair(s, i + 1, newTight, 0, -1, -1);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }
            if (started == 0) {
                Pair ans = solveWithMemoPair(s, i + 1, newTight, 1, -1, digit);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }

            if (prevPrev == -1) {
                Pair ans = solveWithMemoPair(s, i + 1, newTight, 1, prev, digit);
                totalCount += ans.count;
                totalWave += ans.totalWaveCount;
                continue;
            }

            int extra = 0;

            if ((prevPrev < prev && prev > digit) || (prevPrev > prev && prev < digit)) {
                
                extra = 1;
            }
            Pair ans = solveWithMemoPair(s, i + 1, newTight, 1, prev, digit);

            totalCount += ans.count;
            totalWave += extra *ans.count +  ans.totalWaveCount;
        }
        Pair ans = new Pair(totalCount, totalWave);
        if(tight == 0){
            vis[i][started][tight][prevPrev+1][prev+1] = true;
            dp[i][started][tight][prevPrev+1][prev+1] = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        int prevPrev = -1;
        int prev = -1;

        String l = String.valueOf(num1 - 1);
        String r = String.valueOf(num2);

        

        dp = new Pair[20][2][11][11][1];
        vis = new boolean[20][2][11][11][1];
        



        Pair rightAns = solveWithMemoPair(r, 0, 1, 0, prevPrev, prev);

        dp = new Pair[20][2][11][11][1];
        vis = new boolean[20][2][11][11][1];
        
        Pair leftAns = solveWithMemoPair(l, 0, 1, 0, prevPrev, prev);
        System.out.println(rightAns.totalWaveCount - leftAns.totalWaveCount);
        sc.close();
    }
}