// Time Complexity : O(m*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class RegularExpressionMatching {
    class Solution {
        public boolean isMatch(String s, String p) {
        /*dp sol
        1) chars don't match false
        2) chars match= topLeft
        3)if * -> 2 stpes back || top(if previous to * is matching chars)
        */
            if(s.equals(p))
                return true;

            int m = s.length(), n = p.length();
            boolean dp[] = new boolean[n+1];
            dp[0] = true;
            //top row
            for(int j = 1; j <= n; j++){
                if(p.charAt(j-1) == '*')
                    dp[j] = dp[j-2];
            }

            for(int i = 1; i <= m; i++){
                boolean topLeft = false;
                if(i == 1){
                    topLeft = true;
                    dp[0] = false;
                }
                for(int j = 1; j <= n; j++){
                    boolean temp = dp[j];
                    if(p.charAt(j-1) != '*'){
                        //normal char nad '.'
                        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                            dp[j] = topLeft;
                        }
                        else{
                            dp[j] = false;
                        }
                    }
                    else{
                        //'*'
                        if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                            dp[j] = dp[j-2] || dp[j];
                        }
                        else{
                            dp[j] = dp[j-2];
                        }
                    }
                    topLeft = temp;
                }
            }
            return dp[n];
        }
    }
}
