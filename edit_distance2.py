# // Time Complexity :O(m*n)
# // Space Complexity :O(m*n)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp=[[0 for i in range(len(word1)+1)] for j in range(len(word2)+1)]
        for i in range(len(dp)):
            for j in range(len(dp[0])):
                if i==0 and j==0:
                    dp[i][j]=0
                elif i==0:
                    dp[0][j]=j
                elif j==0:
                    dp[i][0]=i
                else:
                    if word1[j-1]==word2[i-1]:
                        dp[i][j]=dp[i-1][j-1]
                    else:
                        dp[i][j]=min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1
        return dp[-1][-1]
                    