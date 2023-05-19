class Solution:
    #TC-0(MN),SC-O(MN)
    # all three cases are covered as follows
    # delete- from left, update-diag,insert-top
    # def minDistance(self, word1: str, word2: str) -> int:
    #     m = len(word1)
    #     n = len(word2)
    #     dp = [[0 for i in range(n+1)] for j in range(m+1)]
    #     for i in range(m+1):
    #         dp[i][0]=i
    #     for j in range(n+1):
    #         dp[0][j]=j
    #     for i in range(1,m+1):
    #         for j in range(1,n+1):
    #             if(word1[i-1]==word2[j-1]):
    #                 dp[i][j]=dp[i-1][j-1]
    #             else:
    #                 dp[i][j]=1+min(min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])
    #     return dp[-1][-1]
    #TC-O(MN),SC-O(min(M,N))
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        if(m<n):
            return self.minDistance(word2,word1)
        dp = [0 for i in range(n+1)]
        for j in range(n+1):
            dp[j]=j
        for i in range(1,m+1):
            diagup=dp[0]
            dp[0]=i
            for j in range(1,n+1):
                temp=dp[j]
                if(word1[i-1]==word2[j-1]):
                    dp[j]=diagup
                else:
                    dp[j]=1+min(min(dp[j],diagup),dp[j-1])
                diagup = temp
        return dp[-1]
    

        