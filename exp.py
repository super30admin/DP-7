# Time: O(mn)
# Space: O(m) for 1d array else O(mn)
# Did it run on Leetcode: yes

# we are building the matrix with the values previsouly calcuated - repeated subproblems - we can use a 1d array instead of matrix as well and store the diagonal-up in a variable
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        if(s==p):
            return True
        m=len(s)
        n=len(p)
        dp=[[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0]=True
        for j in range(1,n+1):
            if(p[j-1]=='*'):
                dp[0][j]=dp[0][j-2]
        for i in range(1,m+1):
            for j in range(1,n+1):
                if(p[j-1] != '*'):
                    if(s[i-1]==p[j-1] or p[j-1]=='.'):
                        dp[i][j]=dp[i-1][j-1]
                    else:
                        dp[i][j]=False
                else:
                    if(p[j-2]==s[i-1] or p[j-2] == '.'):
                        dp[i][j]=dp[i][j-2] or dp[i-1][j]
                    else:
                        dp[i][j]=dp[i][j-2]
        return dp[m][n]