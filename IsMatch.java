// Problem 10. Regular Expression Matching
// Time Complexity : O(m*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        // top row
        for(int j = 1; j <= n; j++){
            if(p.charAt(j-1) == '*'){
                dp[j] = dp[j-2];
            }
        }
        for(int i = 1; i <= m; i++){
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j = 1; j <= n; j++){
                // curr char in pattern
                // 0 and 1 case, 1 case is possible
                boolean temp = dp[j];
                if(p.charAt(j-1) == '*'){
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[j] = dp[j-2] || dp[j];
                    }else{
                        dp[j] = dp[j-2];
                    }
                } else {
                    if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[j] = diagUp;
                    }else{
                        dp[j] = false;
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}
