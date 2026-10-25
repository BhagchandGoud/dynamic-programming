import java.util.*;

class Pair {
    int start;
    int end;
    int weight;
    int idx;

    Pair(int start, int end, int weight, int idx) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.idx = idx;
    }
}

class Store {
    long score;
    ArrayList<Integer> indexes;

    Store(long score) {
        this.score = score;
        this.indexes = new ArrayList<>();
    }

    Store(long score, ArrayList<Integer> indexes) {
        this.score = score;
        this.indexes = new ArrayList<>(indexes);
    }
}

public class Maximum_Number_of_Non_overlapping_Intervals {

    int[] nextValidIndex;

    int binary_search(ArrayList<Pair> nums, int index) { // O(logn)
        int start = index + 1;
        int end = nums.size() - 1;
        int ans = nums.size();

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums.get(mid).start > nums.get(index).end) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    int n;
    Store[][] dp;

    // O(n) + O(n * 4) + O(nlogn) + O(nlogn) + O(minLen) + O(n) + O(nlogn) +
    // O(nlogn) => O(nlogn)
    Store solve(ArrayList<Pair> intervals, int i, int choose) {
        if (i >= n || choose == 0) {
            return new Store(0);
        }

        if (dp[i][choose] != null) {
            return dp[i][choose];
        }

        Store skip = solve(intervals, i + 1, choose); // (n)
        Store next_state = solve(intervals, nextValidIndex[i], choose - 1); // (O(4*n))
        long score = next_state.score + intervals.get(i).weight;
        Store take = new Store(score, next_state.indexes);
        take.indexes.add(intervals.get(i).idx);

        Store result;

        if (take.score > skip.score) {
            result = take;
        } else if (take.score < skip.score) {
            result = skip;
        } else {
            result = compareLexicographically(take, skip);
        }
        return dp[i][choose] = result;
    }

    // O(nlogn) + O(nlogn) + O(minLen)
    Store compareLexicographically(Store take, Store skip) {
        ArrayList<Integer> tList = new ArrayList<>(take.indexes);
        ArrayList<Integer> sList = new ArrayList<>(skip.indexes);

        Collections.sort(tList); // O(nlogn)
        Collections.sort(sList); // O(nlogn)

        int minLen = Math.min(tList.size(), sList.size());

        for (int i = 0; i < minLen; i++) { // O(minLen)
            if (!tList.get(i).equals(sList.get(i))) {
                return tList.get(i) < sList.get(i) ? take : skip;
            }
        }
        return tList.size() < sList.size() ? take : skip;
    }

    // O(n) + O(nlogn) + O(nlogn)
    public int[] maximumWeight(List<List<Integer>> intervals) {
        ArrayList<Pair> arr = new ArrayList<>();
        n = intervals.size();
        int m = intervals.get(0).size();

        for (int i = 0; i < n; i++) { // O(n)
            int s = intervals.get(i).get(0);
            int e = intervals.get(i).get(1);
            int w = intervals.get(i).get(2);
            arr.add(new Pair(s, e, w, i));
        }

        Collections.sort(arr,
                (a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(a.idx, b.idx)); // O(nlogn)

        // pre -compuration part
        nextValidIndex = new int[n];
        for (int i = 0; i < n; i++) { // O(n)
            nextValidIndex[i] = binary_search(arr, i); // O(logn)
        }

        dp = new Store[n + 1][5];
        Store final_result = solve(arr, 0, 4);

        Collections.sort(final_result.indexes);

        int[] res = new int[final_result.indexes.size()];
        for (int i = 0; i < final_result.indexes.size(); i++) {
            res[i] = final_result.indexes.get(i);
        }
        return res;
    }

}
