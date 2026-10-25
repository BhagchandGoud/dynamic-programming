import java.util.*;


public class Kanpsack{
    
    static int size;
    
    static int solve(int []nums , int i ,  int target){
        if(i >= size){
            return 0;
        }
        
        if(target == 0){
            return 0;
        }
        
        int take = 0;
        
        if(nums[i] <= target){
            take = nums[i] + solve(nums , i ,  target-nums[i]);
        }
        int skip = solve(nums, i+1, target);
        return Math.max(take , skip);
    }
    
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        
        while(t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            size = n;
            int arr[] = new int[n];
            
            for(int i = 0 ; i < n ; i++){
                arr[i] = sc.nextInt();
            }
            
            int ans = solve(arr, 0 , k);
            System.out.println(ans);
            t--;
            
        }
        sc.close();
    }
}