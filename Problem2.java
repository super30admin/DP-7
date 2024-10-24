// Time Complexity : O(mxn) 
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : used the same logic Jaspinder explained in the class.


package DP-7;

public class Problem2 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        boolean [] dp = new boolean[n+1];
        dp[0] = true;
        //fill the first row
        for(int j = 1; j <= n; j++){
            if(p.charAt(j-1) == '*'){
                dp[j] = dp[j-2];
            }
        }

        for(int i = 1; i <= m; i++){
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j = 1; j<=n;j++){
                boolean temp = dp[j];
                if(p.charAt(j-1) == '*'){
                    //0 or 1 case
                    // 1 case possible by checking preceeding character to * else 2 steps back
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[j] = dp[j] || dp[j-2];
                    }else{
                        dp[j] = dp[j-2];
                    }
                }else{
                    // ordinary character
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        //diagup
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
