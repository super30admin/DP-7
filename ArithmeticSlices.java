package DynamicProgramming8;

public class ArithmeticSlices {
    /* Created by palak on 8/16/2021 */

    /**
     Top Down space O(1)
     Time Complexity: O(n)
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 0) return 0;
        int n = nums.length;
        int sum = 0;
        // int [] dp = new int[n];

        int curr = 0;
        int prev = 0;

        for(int i = 2 ; i < n ; i++) {
            if(nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                curr = prev + 1;
                sum += curr;
                prev = curr;
            } else {
                prev = 0;
            }
        }

        return sum;
    }

    public static void main(String[] args) {

    }
}
