//Time Complexity: o(m*n)
//Space Complexity: o(m*n)
//Expln: If we encounter a normal char in pattern check if the char in pattern matches the string upto previous char in both or
// if the pattern is . in previous char store true or borrow the result from diagonal left up. If its not if its a * in the pattern
// the we compute 0 and 1 case, in 0 case we borrow results from pattern from char at patter -2 so we take the * of the char as 0;
// In 1 case if the preceeding character matches (or its a dot) the char a string borrow the result from up which indicates that to check 
// if the string and pattern match up to previous char in string only if its true. o
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 1; i < n+1; i++)
        {
            if(p.charAt(i-1) == '*')
            {
                dp[0][i] = dp[0][i-2];
            }
        }
        for(int i = 1; i < m+1; i++)
        {
            for(int j =1; j < n+1; j++)
            {
                if(p.charAt(j-1) != '*')
                {
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) =='.')
                    {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else
                {
                    dp[i][j] = dp[i][j-2];
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.')
                    {
                        if(dp[i-1][j])
                        {
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                }
            }
        }
        return dp[m][n];
    }
}