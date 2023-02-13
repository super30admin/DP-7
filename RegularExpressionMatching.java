//Time Complexity: O(mn)
//Space Complexity: O(mn)

/*
 * make a dp array of all all characters. if the element is not *, then
 * if they are same or a dot the get the diagnol value. else it is a *,
 * for 0 case we go two steps back and for 1 case if the previious value
 * is same of a dot then OR operation of current and previous value. 
 */

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i = 1; i <= n; i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    //0 case
                    dp[i][j] = dp[i][j-2];
                    //1 case
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}