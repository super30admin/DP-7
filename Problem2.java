// 10. Regular Expression Matching
// Time - O(mn)
// space - O(mn)
class Solution {
    public boolean isMatch(String s, String regex) {
        
        int m = s.length();
        int n = regex.length();
        
        boolean[][] dp = new boolean [m + 1][n + 1];
        
        dp[0][0] = true;
        
        // initialize first row
        
        for(int j = 1; j < n + 1; j++){
            
            char c = regex.charAt(j - 1);
            if(c == '*'){
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for(int i = 1; i < m + 1; i++){
            
            for(int j = 1; j < n + 1; j++){
                
                char regexChar = regex.charAt(j - 1);
                char sChar = s.charAt(i - 1);
                
                if(regexChar == sChar || regexChar == '.'){
                    dp[i][j] = dp[i - 1][j - 1]; 
                    
                }else if(regexChar == '*'){
                    
                    boolean notTaken = dp[i][j - 2];
                    boolean taken = false;
                    
                    char newRegexChar = regex.charAt(j - 2);
                    // char newStringChar = s.charAt(j - 2);
                    
                    if((newRegexChar == '.' || newRegexChar == sChar) && dp[i - 1][j]){
                        taken = true;
                    }
                    dp[i][j] = taken || notTaken;
                    
                }
            }
        }
        
        return dp[m][n];
    }
}