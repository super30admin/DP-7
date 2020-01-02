/*

Did it run on leetcode: Yes
Time Complexity: 0(n*m)
Space Complexity: 0(n*m)

Algorithm:
- If a star is present then it is always present at index=1 and we have to consider two cases, either
to include it or ignore it. If we get a match from the future then its a match, because current state is matching.


*/


class Solution {
    public boolean isMatch(String s, String p) {
        
        int n = s.length()+1;
        int m = p.length()+1;
        
        boolean[][] dp = new boolean[n][m];
        
        // comparison for blank characters
        dp[0][0] = true;
        
        // first rows
        for(int j=1;j<m;++j){
            if(p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i=1;i<n;++i){
            for(int j=1;j<m;++j){
                
                // cases of matching for dot or same character
                
                if(p.charAt(j-1)=='.' || p.charAt(j-1)==s.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1)=='*'){
                     /*
                if the char is a star, then we need to check two things additionally
                dp[i][j] becomes value two steps back, because we are not considering character and the star associated with it.
                
                */
                    
                    dp[i][j] = dp[i][j-2];
                    
                    if(p.charAt(j-2)=='.' || p.charAt(j-2)==s.charAt(i-1) ){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                }
                
                
               
                
            }
        }
        
        return dp[n-1][m-1];
        
        
    }
}