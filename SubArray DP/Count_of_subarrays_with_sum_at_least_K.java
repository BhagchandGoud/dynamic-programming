public class Count_of_subarrays_with_sum_at_least_K {
    
    static int n;

    // gfg problem 
    // for leetcode sum == k but get tle

    static int solve(int nums[] , int i , int prevPick , int k , int sum){
        if(i >= n){
            return prevPick == 1 && sum >= k ? 1 : 0;
        }

        if(prevPick == 1){
            int start = solve(nums , i+1 , 1 , k , sum + nums[i]);
            int stop = sum >= k ? 1 : 0;
            return start + stop;
        }
        else{
            int startNewFromCurr = solve(nums , i+1 , 1 , k , sum + nums[i]);
            int startNewFromNext = solve(nums , i+1 , 0 , k , 0);
            return startNewFromCurr + startNewFromNext;
        }
    }

    public static void main(String[] args) {
        int nums[] = {6, 1, 2, 7};
        n = nums.length;

        int ans = solve(nums, 0, 0, 10, 0);
        System.out.println(ans);
    }
}
