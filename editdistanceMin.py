# SpaceCompelxity:o(N^2)
# TimeComplexity:O(n^2)
# Approach:Classic DP problem on strings, Brute force would be try 3 choices at every index in treeee you can see repeated sub problems , 
# DP: if equal we dont need to do anything
# else we have choice s adn we take minof those choices and add one to it
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m,n=len(word1),len(word2)
        dp=[[0 for _ in range(m+1)] for ii in range(n+1) ] #[n][m]
        for j in range(len(dp[0])):
            dp[0][j]=j
        for i in range(1,len(dp)):
            dp[i][0]=i #length
            for j in range(1,len(dp[0])):
                if word1[j-1]==word2[i-1]:
                    dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=1+min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
        return dp[n][m]

