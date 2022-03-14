'''
TC: O(m*n)
SC: O(m*n)
'''

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        wlen = len(word1)
        slen = len(word2)
        
        if not wlen:
            return len(word2)
        if not slen:
            return len(word1)
        
        dp = [[0 for j in range(slen+1)] for i in range(wlen+1)]
        
        for i in range(1, slen + 1):
            dp[0][i] = i
        
        for i in range(1, wlen + 1):
            dp[i][0] = i
        
        for i in range(1, wlen + 1):
            for j in range(1, slen + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1
        
        return dp[-1][-1]
        