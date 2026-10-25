public class SplitAnArrayIntoTwoEqualSumSubarrays {
    static int n;
    static int originalTarget;

    static int solve(int[] nums, int prevPick, int i, int target, int parts) {
        if (i >= n) {
            return prevPick == 1 && target == 0 && parts == 1 ? 1 : 0;
        }

        if (prevPick == 1) {
            int newTarget = target - nums[i];
            if (newTarget < 0) {
                return 0;
            }
            if (newTarget == 0) {
                if (parts == 0) {
                    return solve(nums, 0, i + 1, originalTarget, 1);
                }
                return 1;
            }
            return solve(nums, 1, i + 1, newTarget, parts);
        } else {
            int ways = 0;
            int nextTarget = target - nums[i];
            if (nextTarget >= 0) {
                if (nextTarget == 0) {
                    if (parts == 0) {
                        ways += solve(nums, 0, i + 1, originalTarget, 1);
                    } else {
                        ways += 1;
                    }
                } else {
                    ways += solve(nums, 1, i + 1, nextTarget, parts);
                }
            }
            ways += solve(nums, 0, i + 1, target, parts);
            return ways;
        }
    }

    static Integer dp[][][][];
    static int solveWithMemo(int[] nums, int prevPick, int i, int target, int parts) {
        if (i >= n) {
            return prevPick == 1 && target == 0 && parts == 1 ? 1 : 0;
        }
        if(dp[i][prevPick][target][parts] != null){
            return dp[i][prevPick][target][parts];
        }

        if (prevPick == 1) {
            int newTarget = target - nums[i];
            if (newTarget < 0) {
                return 0;
            }
            if (newTarget == 0) {
                if (parts == 0) {
                    return dp[i][prevPick][target][parts] = solve(nums, 0, i + 1, originalTarget, 1);
                }
                return dp[i][prevPick][target][parts] = 1;
            }
            return dp[i][prevPick][target][parts] = solve(nums, 1, i + 1, newTarget, parts);
        } else {
            int ways = 0;
            int nextTarget = target - nums[i];
            if (nextTarget >= 0) {
                if (nextTarget == 0) {
                    if (parts == 0) {
                        ways += solve(nums, 0, i + 1, originalTarget, 1);
                    } else {
                        ways += 1;
                    }
                } else {
                    ways += solve(nums, 1, i + 1, nextTarget, parts);
                }
            }
            ways += solve(nums, 0, i + 1, target, parts);
            return dp[i][prevPick][target][parts] = ways;
        }
    }

    static boolean canSplit(int arr[]){
        n = arr.length;

        int sum = 0;

        for (int num : arr) {
            sum += num;
        }
        if(sum%2 != 0){
            return false;
        }
        int target = sum / 2;
        originalTarget = target;

        dp = new Integer[n+1][2][target+1][2];

        int subarray = solve(arr, 0, 0, target, 0);
        return subarray > 0;
    }


    public static void main(String[] args) {
        int arr[] = {2, 6, 8};

        boolean ans = canSplit(arr);
        System.out.println(ans);
    }
}