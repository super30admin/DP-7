# problem 2

#https://leetcode.com/problems/regular-expression-matching/description/


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp_mat = [[False]*(n+1) for _ in range(m+1)]
        dp_mat[0][0] = True
        for i in range(1,n+1):
            if p[i-1] == '*':
                dp_mat[0][i] = dp_mat[0][i-2]
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[j-1] == '*':
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp_mat[i][j] = dp_mat[i][j-2] or  dp_mat[i-1][j]
                    else:
                        dp_mat[i][j] = dp_mat[i][j-2]
                else:
                    if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                        dp_mat[i][j] = dp_mat[i - 1][j - 1]
        return dp_mat[m][n]