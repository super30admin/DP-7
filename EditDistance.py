# class Solution:
#     def minDistance(self, word1: str, word2: str) -> int:
#         m,n=len(word1),len(word2)
#         memo = [[None]*n for _ in range(m)]
#         def helper(i,j):
#             # base
#             if i == m:
#                 return n-j
#             if j == n:
#                 return m-i
#             if memo[i][j]: return memo[i][j]
#             result = float("inf")
#             # logic
#             if word1[i] == word2[j]:
#                 result = helper(i+1,j+1)
#             else:
#                 add = 1+helper(i+1,j)
#                 delete = 1+helper(i,j+1)
#                 edit = 1+helper(i+1,j+1)
#                 result = min(add,delete,edit)
#             memo[i][j] = result
#             return result
#         return helper(0,0)


# class Solution:
#     def minDistance(self, word1: str, word2: str) -> int:
#         m,n = len(word1), len(word2)
#         dp = [[0]*(n+1) for _ in range(m+1)]
#         for j in range(n+1):
#             dp[0][j] = j
#         for i in range(1,m+1):
#             for j in range(n+1):
#                 if j == 0:
#                     dp[i][j] = i
#                 else:
#                     if word1[i-1] == word2[j-1]:
#                         dp[i][j] = dp[i-1][j-1]
#                     else:
#                         dp[i][j] = 1+ min(dp[i-1][j],dp[i][j-1], dp[i-1][j-1])
#         return dp[m][n]

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m,n = len(word1), len(word2)
        dp = list(range(n+1))
        for i in range(1,m+1):
            diagup = dp[0]
            for j in range(n+1):
                temp = dp[j]
                if j == 0:
                    dp[j] = i
                elif word1[i-1] == word2[j-1]:
                    dp[j] = diagup
                else:
                    dp[j] = 1+min(dp[j-1], diagup,dp[j])
                diagup = temp
        return dp[n]


        
    
        