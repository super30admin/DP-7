TC:O(m*n) -m,n are lengths of the strings
SC(O(m*n) - DP array

class Solution {
    public boolean isMatch(String s, String p) { //create a dp array
        int pl = p.length();
        int sl = s.length();
        boolean [][] dp = new boolean[sl+1][pl+1];
        dp[0][0] = true;
        for(int j=1;j<=pl;j++){
            if(p.charAt(j-1)=='*')  dp[0][j] = dp[0][j-2];
           
        }
        for(int i=1;i<=sl;i++){ //fix the top row and first column in the dp array
            dp[i][0] = false;
        }
        for(int i=1;i<=sl;i++){  //traverse through the dp array by choosing the things in multiple possibe ways
            for(int j=1;j<=pl;j++){
                if(p.charAt(j-1)!='*')
                {
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') 
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    //0 case and 1 case
                    
                    dp[i][j] = dp[i][j-2];
                    
                    //if the preceeding char matches with the other char or if its a dot
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') 
                    dp[i][j] = dp[i][j] || dp[i-1][j];
                }
            }
            
        }
        return dp[sl][pl];
    }
}