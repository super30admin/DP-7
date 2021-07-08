"""
// Time Complexity : O(len(s)*len(p))
// Space Complexity : O(len(s)*len(p))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
Algorithm Explanation
Given below - DP
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
        Main idea is we can break down each ch* into two decision making mode - 0 char or 1 char, since we can expand the string on * at each recursive level, we can get overlapping subproblems with result string/intermediate string acts as subproblem ->DP
        Tabulation method
        - Create a dp array, dp[s.length+1][p.length+1] consisting of False values
        - dp[0][0] = T (blank matches blank)
        - Fill the first row of dp
            If the last element of p == '*'
                dp[0][j] = dp[0][j-2] // go 2 places back such that string before start of * is covered eg _c*a* _c* (for a*) would have been computed before
        - For row = 1 to dp.length
            For col = 1 to dp[0].length
                If current char is not a star 
                - s[row-1] == p[col-1] 
                    dp[row][col] = dp[row-1][col-1]
                else:
                    dp[row][col] = dp[row][col-2] #fetching the value corresponding to zero for *
                    if s[row-1] == p[col-2] or p[col-2] == '.': # case for considering 1
                        dp[row][col] = dp[row][col] or dp[row-1][col]
            
        - return dp[dp.length-1][dp[0].length-1]    
        """
        dp = [[False for _ in range(len(p)+1)] for _ in range(len(s)+1)]
        dp[0][0] = True
        for j in range(1,len(dp[0])):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                #current char in pattern not a star
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2] # getting the value for 0 case
                    
                    #check for case 1 for *
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[i][j] |= dp[i-1][j]
        return dp[len(dp)-1][len(dp[0])-1]