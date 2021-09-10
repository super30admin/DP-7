//top down Approach
//T.C:O(N)
//S.C:O(N)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        int[] subarray = new int[nums.length];
        int sum=0;

        for(int i=2;i<subarray.length;i++) {

            if(nums[i]-nums[i-1] ==  nums[i-1]-nums[i-2]) {
                subarray[i]=1+subarray[i-1];
            } else {
                subarray[i]=0;
            }
            sum += subarray[i];
        }
        return sum;
    }
}

//Bottom up Approach
//T.C:O(N)
//S.C:O(N)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        int[] subarray = new int[nums.length];
        int sum=0;

        for(int i=nums.length-3;i>=0;i--) {

            if(nums[i+2]-nums[i+1] ==  nums[i+1]-nums[i]) {
                subarray[i]=1+subarray[i+1];
            } else {
                subarray[i]=0;
            }
            sum += subarray[i];
        }
        return sum;
    }
}

//Constant Spcae Top Down Approach
//T.C:O(N)
//S.C:O(1)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3) {
            return 0;
        }

        int sum=0;
        int curr =0;int prev=0;
        for(int i=2;i<nums.length;i++) {

            if(nums[i]-nums[i-1] ==  nums[i-1]-nums[i-2]) {
                curr = 1+prev;
            } else {
                curr = 0;
            }

            sum += curr;
            prev = curr;
        }
        return sum;
    }
}