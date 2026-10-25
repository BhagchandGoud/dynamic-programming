

class Pair{
    int gcd;
    int len;
    Pair(int len , int gcd){
        this.len = len;
        this.gcd = gcd;
    }
}
public class LongestSubArrayWithMaximumGcd {

    static int n;

    static int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    static Pair solve(int nums[], int prevPick, int i, int prevGcd) {
        if (i >= n - 1) {
            return new Pair(0 , prevGcd);
        }
        int currGCD = GCD(nums[i], nums[i + 1]);
        if (prevPick == 1) {
            if (prevGcd != -1 && currGCD <= prevGcd) {
                return new Pair(0, currGCD);
            }
            Pair start = solve(nums, 1, i + 1, currGCD);
            return new Pair(start.len+1 , currGCD);
        } else {
            Pair startNewFromCurr = solve(nums, 1, i + 1, currGCD);
            Pair start = new Pair(startNewFromCurr.len+1 , startNewFromCurr.gcd);
            Pair skip = solve(nums, 0, i + 1, prevGcd);
            return start.len >=  skip.len ? start : skip;
        }
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 2};
        n = nums.length;

        Pair ans = solve(nums, 0, 0, -1);
        System.out.println(ans.len);
    }
}