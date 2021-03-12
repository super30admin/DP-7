// Time Complexity : The time complexity is O(m*n) where m is the length of s and n is the length of p
// Space Complexity : The space complexity is O(m*n) where m is the length of s and n is the length of p
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {

        int sl = s.length();
        int pl = p.length();

        boolean[][] dp = new boolean[sl+1][pl+1];
        dp[0][0] = true;

        for(int i=1;i<=pl;i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i=1;i<=sl;i++){
            for(int j=1;j<=pl;j++){
                //character is . or a letter
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                //character is *
                else{
                    //zero case
                    dp[i][j] = dp[i][j-2];

                    //one case
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }

        return dp[sl][pl];
    }
}