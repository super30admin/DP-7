## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)
In this we are given an a string and a pattern we want to show if the pattern matches the string
The pattern contains 
. = any valid character
* = 0 or more occurence of the preceeding characters

// Bruteforce approach is being exhaustive like at * we can make a decision to keep 0 occurence or 1 occurence. But it causes repeated subproblem
// So we can think off the DP based solution. In this we will take a 2-D matrix. Where we will keep pattern at column side and string at row side
// We will have a "" at the start of the row and column which will help us for further calculations and because of it we do not need to write 
// the different conditions for the edge causes
// First we will fill 0th row for that we will see if there is a character or . then it will not match with the "" so we will put it false as 
// we are storing True or False in matrix and matrix will be bydefault initialized to False. So if * comes we will have 2 choices 0 or 1 i.e to 
// consider it or ignore it. For the 0th row it will not make sense to have 1 case. so we will directly see the 0th case and it's answer lies
// in the same row 2 steps back

Time Complexity : O(m*n)
Space Complexity : O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        //It will always be true as "" matches ""
        dp[0][0]=true;
        //Now we will fill the 0th row and we will just process if it is a * and for that also a 0 case and it's answer lies 2 steps behind
        for(int i=1;i<=p.length();i++){
            if(p.charAt(i-1)=='*'){
                dp[0][i]=dp[0][i-2];
            }
        }
        //Now we will start filling remaining cells
        // So at s there can be a-z any char and at p there can be .,*,a-z
        // if char at s == char at p or . (because it is a valid char) then it will be true else false
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)=='.'){
                    dp[i][j]=dp[i-1][j-1];
                }
                //if it is a * then always there will be a 0 case and 1 case will only be there if the character preceedingthe * matches and if so we will take || of both
                else if(p.charAt(j-1)=='*'){
                   dp[i][j]=dp[i][j-2]; 
                   if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
                    dp[i][j]=dp[i][j]||dp[i-1][j];
                   }
                }
            }
        }
        //At the end we will return the last element of the matrix
        return dp[s.length()][p.length()];
    }
}