// Time Complexity : The time complexity is O(m*n) where m is the length of word1 and n is the length of word2
// Space Complexity : The space complexity is O(n) where n is the length of word2
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n+1];

        for(int i=0;i<=n;i++){
            dp[i] = i;
        }

        int temp1 = 0;
        int temp2 = 0;
        for(int i=1;i<=m;i++){
            temp1 = dp[0];
            dp[0] = i;
            for(int j=1;j<=n;j++){
                temp2 = dp[j];
                //when letters match no operation
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[j] = temp1;
                }
                //min between 3 operations(update, delete and replace)
                else{
                    dp[j] = 1 + Math.min(Math.min(dp[j],dp[j-1]), temp1);
                }
                temp1 = temp2;
            }
        }

        return dp[n];
    }
}