# Optimized DP (Space)
# Time complexity : O(n*n)
# Space complexity : O(n) --> length of pattern string
# Leetcode : Solved and submitted

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # if the strings matched then return True
        if s == p:
            return True
        
        # get the lengths of the strings
        n = len(s)
        m = len(p)
        
        # create a dp of size of pattern string
        dp = [False for _ in range(m+1)]
        
        # mark the first character which is the empty string as True
        dp[0] = True
        
        # fill the row
        # if we encounter a *, then get the value from 2 indexes before from the same row
        for j in range(1,m+1):
            if p[j-1] == '*':
                dp[j] = dp[j-2]
        
        # traverse over the matrix
        for i in range(1,n+1):
            # we need the diagonal when we are having a matched character and put the dp[0] as False
            diag = dp[0]
            dp[0] = False
            
            for j in range(1,m+1):
                # store the current element at the index in temp
                temp = dp[j]
                
                # if we have a matching character or a '.', we just update the dp index from the diagonal
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[j] = diag
                
                # if we encounter a '*'
                elif p[j-1] == '*':
                    # if the character preceding (just before) '*' matches or it is '.'. then do the either values of the same index or two places back
                    if (s[i-1] == p[j-2] or p[j-2] == '.'):
                        dp[j] = dp[j] or dp[j-2]
                    else:
                        # else just take the value two indexes before from the row
                        dp[j] = dp[j-2]
                else:
                    # if none of the condition satisifies then we update it as False
                    dp[j] = False
                # at each index, we update the diagonal as the temp which we stored as dp[j]
                diag = temp
         
        # at the last, we just return the last value of the dp matrix after traversing all the loops
        return dp[m]
