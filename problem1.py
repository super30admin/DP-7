# TC: O(m*n)
# SC: O(m)

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 is word2:
            return 0

        if len(word1) > len(word2):
            return self.minDistance(word2,word1)

        m = len(word1)
        n = len(word2)

        dp = [0]* (m+1)

        for i in range(0,m+1):
            dp[i] = i

        for i in range(1,n+1):
            temp = dp[0]
            dp[0] = i
            for j in range(1,m+1):
                prev = dp[j]

                if word1[j-1] == word2[i-1]:
                    dp[j] = temp

                else:
                    dp[j] = 1+ min(dp[j],dp[j-1],temp)

                temp = prev
        return dp[-1]
