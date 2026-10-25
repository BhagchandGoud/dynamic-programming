import java.util.*;


// lis with binary search is called  Patience Sorting

public class LIS_With_Binary_Search {

    static int lowerBound1(ArrayList<Integer> order, int target){

        int start = 0;
        int end = order.size() - 1;

        int ans = order.size();

        while(start <= end){

            int mid = start + (end - start)/2;

            if(order.get(mid) >= target){
                ans = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        return ans;
    }

   //-------------------------  approach one with 1D space -------------------------
    static int LisWith1DSpace(int []nums){
        ArrayList<Integer> order = new ArrayList<>();

        for(int x : nums){

            if(order.size() == 0 ||
               order.get(order.size()-1) < x){

                order.add(x);
            }
            else{

                int pos = lowerBound1(order, x);

                order.set(pos, x);
            }
        }
        return order.size();
    }


    //-------------------------  approach one with constant space -------------------------

    static int lowerBound(int nums[], int len ,int target){

        int start = 0;
        int end = len-1;

        int ans = 0;

        while(start <= end){

            int mid = start + (end - start)/2;

            if(nums[mid] >= target){
                ans = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        return ans;
    }
    static int LisWithConstantSpace(int []nums){

        int len = 0;
        for(int x : nums){

            if(len == 0 ||
               nums[len-1] < x){
                nums[len] = x;
               len++;
            }
            else{

                int pos = lowerBound(nums,len, x);
                nums[pos] =  x;
            }
        }
        return len;
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }

        
        //LIS from approach 1
        int ans1 = LisWith1DSpace(nums);
        System.out.println(ans1);

        //LIS from approach 2
        int ans2 = LisWithConstantSpace(nums);
        System.out.println(ans2);
 
        sc.close();
    }
}