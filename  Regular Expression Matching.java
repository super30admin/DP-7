//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :yes
// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //use a 2D boolean array to keep track of the matchings of characters
        boolean[][] dp = new boolean[m+1][n+1];
        //extra col and row is for the empty character which is used as a base case
        //the oth row and col will have empty charater(_)
        dp[0][0] = true;//empty charcter with empty char will always match
        //fill the first row
        for(int i=1;i<n+1;i++){
            //if the current character in pattern in * then get its value 2 cols back
            if(p.charAt(i-1)=='*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        //fill the matrix
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                //if the char matches or it the char in string is a '.', then get its value from the diagonal        
                if(p.charAt(j-1)!='*'){
                    if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.'){
                        //ex: p =_a and s = _c*a, here the a gets cancelled in s and p, now p=_ and s =_c*, the answer to this is present at the diagonal from the current cell.
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                    //otherwise its  *
                else{
                    //not choosing the char, then get its value 2 cols back
                    dp[i][j] = dp[i][j-2];
                    //choosing the char, the values lies just above one row and in the same col
                    //ex : p =_a s = _c*a*, now after choosing a in s, s = _c*a, since the a in p and s matches,the value of this lies just one row above the current row and same col
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
                        //if above row is true, then choose it
                        if(dp[i-1][j]){
                            dp[i][j] = dp[i-1][j]; 
                        }
                    }
                }
            }
        }
        //the last cell in the matrix will have the final result
        return dp[m][n];
        /* at the end of computation, the dp array will be as below
        _| |0|1|2|3|4|5
           |-|c|*|a|*|b
        0|-|T|F|T|F|T|F
        1|a|F|F|F|T|T|F
        2|a|F|F|F|F|T|F
        3|b|F|F|F|F|F|T
        
        */
    }
}