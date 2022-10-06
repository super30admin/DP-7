# Time complexity : O(n*m)
# Space complexity : O(n*m)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # if both the string are same, then return True
        if s == p:
            return True
        
        # find the lengths of strings
        m = len(s)
        n = len(p)
        
        # create a 2-d dp array of size n+1 * m+1
        dp = [[False for _ in range(n+1)] for _ in range(m+1)]
        
        # mark the first element in matrix as True
        dp[0][0] = True
        
        # filling the first row
        # if we find an *, then we fetch the value which is 2 indexes before
        for i in range(1,n+1):
            ch = p[i-1]
            if ch == '*':
                dp[0][i] = dp[0][i-2]
        
        # traversing the strings
        for i in range(1,m+1):
            for j in range(1,n+1):
                # if we have a matching character or we find a period
                if p[j-1] == s[i-1] or p[j-1] == '.':
                    # then fetch the value from the diagonal left
                    dp[i][j] = dp[i-1][j-1]
                    
                    # if we encounter an *
                elif p[j-1] == '*':
                    # we have two cases
                    # 0 case - not to choose
                    # values are fetched from two indexes before
                    dp[i][j] = dp[i][j-2]
                    
                    # 1 case
                    # choose the character
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        # do an or operation with the value from the top cell or two indexes before
                        dp[i][j] = dp[i][j-2] or dp[i-1][j]
        
        # return the final answer as the last element of the dp matrix
        return dp[m][n]
                    
