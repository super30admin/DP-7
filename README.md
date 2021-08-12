# DP-7

## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

//Time Complexity = O(m*n)
//Space complexity = O(m*n)

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(); 
        int n = word2.length(); 
        int[][] dp = new int[n+1][m+1];
        //first row
        for(int j = 0; j < dp[0].length; j++){
            dp[0][j] = j;
        }
        
        //first column
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = i; 
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(word1.charAt(j - 1) == word2.charAt(i - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[n][m]; 
    }
}
## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

//Time Complexity = O(N*M)
//Space Complexity = O(N*M)

// 1. If the characters are matching, the diagonals has the answer
// 2. If the 1st Row has a *, 2 steps back the answer
// 3. If *
// 0 case : 2 steps back
// 1 case : preceeding characters is equal to the current character, 1 case is directly up in that case.
// If not *, if the current char is equal to the current s char, the diagonal has the answer

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length(); 
        boolean[][] dp = new boolean[sl + 1][pl + 1]; 
        dp[0][0] = true;
        
        //The 1st row case
        for(int j = 1; j < dp[0].length; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; 
            }
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(p.charAt(j-1) == '*'){
                   dp[i][j] = dp[i][j-2];
                   if((p.charAt(j - 2) == s.charAt(i - 1)) || (p.charAt(j - 2) == '.')){
                       dp[i][j] = dp[i][j] || dp[i-1][j]; 
                   }
                }else{
                    if((p.charAt(j - 1) == s.charAt(i - 1)) || (p.charAt(j - 1) == '.')){
                        dp[i][j] = dp[i-1][j-1]; 
                    }
                }
            }
        }
    
        return dp[sl][pl]; 
    }
}
