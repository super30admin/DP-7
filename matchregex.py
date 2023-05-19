class Solution:
    #TC-O(m*n),SC-O(m*n)
    # create a matrix of m+1 * n+1, and fill first row and column,
    # if the current char at pattern is *, if prev char matches, then take value from prev row, if not matches, false for 1 case and for 0 case, take from two columns before.If curr char in pattern is a char and mathes with string given take diagonal element
    def isMatch(self, s: str, p: str) -> bool:
        n = len(p)
        m = len(s)
        dp=[[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0]=True
        for i in range(1,m+1):
            dp[i][0]=False
        for j in range(1,n+1):
            if(p[j-1]=='*'):
                dp[0][j]=dp[0][j-2]
        for i in range(1,m+1):
            for j in range(1,n+1):
                # not star, check if current elem matches in both strings and take diag elem
                if p[j-1]!='*':
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                    if p[j-2]==s[i-1] or p[j-2]=='.':
                        # if char matches, take both 1 and 0 case
                        dp[i][j]=dp[i-1][j] or dp[i][j-2]
                    else:
                        # take only zero case
                        dp[i][j]=dp[i][j-2]
        return dp[-1][-1]

        