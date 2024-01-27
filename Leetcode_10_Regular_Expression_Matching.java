//TC: O(r*c)
//sc: O(r*c)- matrix space
/*Approach
1. If we go brute force, we have to try all the combination, for length p and s it will take max(s,p) ^ max(s,p) = n^n!
2. so we're using DP approach here. 
3. We'll take a boolean DP matrix,  row size = sourcelength ; and col size = pattern length.
4. We'll solve the small problems and move on. 
5. if the character matches, we take the digonal value as ouranswer. 
6. if the character is *; we have 0 case and 1 case. 
7. 0 case : we dnt consier preceding character, and so we rely on col-2 for the answer. 
8. for 1 case, we r=are taking taht charcters frequency, so we aretaking row- answer with the same col.
9. At last the answer lying at last row and last col is the final answer. 
*/
class Solution {
    public boolean isMatch(String s, String p) {
    //base case
        if(s == null || p == null) return false;
        
        //source on row       //pattern on col
        int r = s.length();  int c = p.length();
        
        //dp matrix definition
        boolean[][] dp = new boolean[r+1][c+1]; //extra column and row for empty characters added.
        dp[0][0] = true; //empty character match
        
        char in = ' ';
        for(int col=0; col<c; col++) //filling the 0th row
        {
            char pattern = p.charAt(col);
            if(pattern == '*')
            {
                dp[0][col+1] = dp[0][col-1]; //0 case only
            }
        }
        //by default for empty string on 0th index, the col-0 will always be set to false.
        //travering through the String and Pattern. 
        for(int i = 1; i<=r; i++) //go over string Source; but row
        {
            for(int j=1; j<= c; j++) //go over string pattern; but col c*a*b*
            {
                if(p.charAt(j-1) == '*')
                {
                    dp[i][j] = dp[i][j-2]; //0 case
                    
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') 
                    {
                        dp[i][j] = dp[i][j] || dp[i-1][j]; //1 case
                    }
                }
                else if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[r][c];
    }
}
