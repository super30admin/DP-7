# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         m,n = len(s), len(p)
#         dp = [[False]*(n+1) for _ in range(m+1)]
#         dp[0][0] = True
#         # first row dp[0][j] start from 1
#         for j in range(1,n+1):
#             # only 0 case available 
#             if p[j-1] == '*':
#                 dp[0][j] = dp[0][j-2]
#         # start filling from 1 to m+1
#         for i in range(1,m+1):
#             for j in range(1,n+1):
#                 # if char is a star
#                 if p[j-1] == '*':
#                     # 0 or 1 case or else
#                     if s[i-1] == p[j-2] or p[j-2] == '.':
#                         dp[i][j] = dp[i][j-2] or dp[i-1][j]
#                     else:
#                         # 0 always available
#                         dp[i][j] = dp[i][j-2]
#                 else:
#                     # check if the character match
#                     if s[i-1] == p[j-1] or p[j-1] == '.':
#                         dp[i][j] = dp[i-1][j-1]
#                     else:
#                         dp[i][j] = False
#         return dp[m][n]


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m,n = len(s), len(p)
        dp = [False]*(n+1)
        dp[0] = True
        # first row dp[0][j] start from 1
        for j in range(1,n+1):
            # only 0 case available 
            if p[j-1] == '*':
                dp[j] = dp[j-2]
        # start filling from 1 to m+1
        for i in range(1,m+1):
            diagup = dp[0]
            dp[0] = False
            for j in range(1,n+1):
                temp = dp[j]
                # if char is a star
                if p[j-1] == '*':
                    # 0 or 1 case or else
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[j] = dp[j-2] or dp[j]
                    else:
                        # 0 always available
                        dp[j] = dp[j-2]
                else:
                    # check if the character match
                    if s[i-1] == p[j-1] or p[j-1] == '.':
                        dp[j] = diagup
                    else:
                        dp[j] = False
                diagup = temp
        return dp[n]
        