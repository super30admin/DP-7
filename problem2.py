class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # if not s and p==".":
        #     return False
        # if not s:
        #     return True
        # dp=[[False for _ in range(len(p)+1)] for _ in range(len(s)+1)]
        # # print(dp)
        # dp[0][0]=True
        # for i in range(1,len(dp[0])):
        #     if p[i-1]=='*':
        #         dp[0][i]=dp[0][i-2]
        # for i in range(1,len(dp)):
        #     for j in range(1,len(dp[0])):
        #         if p[j-1]=='.' or p[j-1]==s[i-1]:
        #             dp[i][j]=dp[i-1][j-1]
        #         elif p[j-1]=='*':
        #             dp[i][j]=dp[i][j-2]
        #             if p[j-2]=='.' or p[j-2]==s[i-1]:
        #                 dp[i][j]=dp[i][j] or dp[i-1][j]
        #         else:
        #             dp[i][j]=False
        # print(dp)
        # return dp[len(s)][len(p)]
#         m = len(s)
#         n = len(p)
#         dp = [[False for i in range(m+1)] for j in range(n+1)]
#         dp[0][0] = True
#         for i in range(1,n+1):
#             if i>1 and p[i-1] == '':
#                 dp[i][0] = dp[i-2][0]
#             else:
#                 dp[i][0] = False
#         for i in range(1,n+1):
#             for j in range(1,m+1):
#                 if p[i-1] == '.' or p[i-1] == s[j-1]:
#                     dp[i][j] = dp[i-1][j-1]
#                 elif p[i-1] == '*':
#                     dp[i][j] = dp[i-2][j]
#                     if p[i-2] == s[j-1] or p[i-2] == '.':
#                         dp[i][j] = dp[i][j] or dp[i][j-1]
#         return dp[-1][-1]
                
        def match(a,b):
            return a=='.' or a==b
        
        m,n = len(p),len(s)
        dp = [[False]*(n+1) for _ in range(m+1)]
        dp[0][0] = True
        
        for i in range(1,len(p),2):
            if p[i] == "*":
                dp[i+1][0] = True
            else:
                break
                
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[i-1] == "*":
                    dp[i][j] = dp[i-2][j] or (match(p[i-2],s[j-1]) and dp[i][j-1])
                else :
                    dp[i][j] = (match(p[i-1],s[j-1]) and dp[i-1][j-1])
                
        
        return dp[-1][-1]       
        