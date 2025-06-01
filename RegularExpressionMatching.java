/**
* Time Complexity: O(m*n)
* Space Complexity: O(m*n)
 */

class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        //Create boolean array with False values
        boolean[][] dp = new boolean[m+1][n+1];
        //'-' will match '-' initially, So set p[0][0] = true;
        dp[0][0] = true;

        //Complete filling Row-0, if we encounter '*' we have nothing to choose, so get the previous solution, 2 units away.
        for(int j=1; j<=n; j++){
            char pChar = p.charAt(j-1);
            if(pChar == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        //Now we fill the rest of the matrix.
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                //Load current pChar
                char pChar = p.charAt(j-1);
                if(pChar == '*'){
                    //if pChar is '*' and incoming s character matches with prev p char OR pChar is '.' then get the condition result.
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){ //Choose case at *, incoming p.charAt(j-2) should match s.charAt(i-1)
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                    //Else just copy 2 unit as we dont have a choose condition.
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
                else{
                    //If it not a '*', check p and s chars and also conditionally check if it is '.' If, true copy the diagonal up value.
                    if(pChar == s.charAt(i-1) || pChar == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = false;
                    }
                }
            }
        }
        //Return the end of 2 d matrix
        return dp[m][n];
    }
}
