// 10.
// time - O(length of pattern * length of string)
// space - O(length of pattern * length of string)
class Solution {
    public boolean isMatch(String s, String p) {
        //edge
        if(p == null)
        {
            //pattern is null, return true if main string is null else false
            return(s == null);
        }
        
        //pattern on the coulmns and main string on the rows
        boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
        
        //base
        result[0][0] = true; //empty pattern matches empty string
        for(int i = 1; i <= p.length(); i++)
        {
            char current = p.charAt(i - 1); //if the current is not a *, place false in the cell
            if(current == '*')
            {
                //if current is 8, we can not choose it, so ignore the prev char and get the result till prev char
                result[0][i] = result[0][i - 2]; 
            }
            //other cells in 0th row are false by default
        }
        //all other cells in 1st col are false by default as empty pattern cant match a string
        
        for(int i = 1; i <= s.length(); i++)
        {
            for(int j = 1; j <= p.length(); j++)
            {
                char pattern = p.charAt(j - 1);
                char match = s.charAt(i - 1);
                if(pattern == match || pattern == '.')
                {
                    //exclude current in both p and s and check for the preceeding part
                    result[i][j] = result[i - 1][j - 1]; 
                }
                else if(pattern == '*')
                {
                    boolean zeroCase = result[i][j - 2]; //ignore the current *
                    if(p.charAt(j - 2) == match || p.charAt(j - 2) == '.')
                    {
                        boolean oneCase = result[i - 1][j]; //add one occurence of char before 8
                        zeroCase = zeroCase | oneCase; //answer is or of both cases
                    }
                    result[i][j] = zeroCase;
                }
            }
        }
        
        return result[result.length - 1][result[0].length - 1];
    }
}
