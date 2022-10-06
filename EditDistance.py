#Time Complexity : O(n)
#Space Complexity :O(n)
#Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No



class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)  # ros
        n = len(word1)  # horse
        dp = [0 for i in range(n + 1)]
        for i in range(0, n + 1):
            dp[i] = i

        #         No Change-diagUp
        #         Delete-dp[j-1]
        #         Update-diagUp
        #         Add-dp[j]

        for i in range(1, m + 1):
            diagUp = 0
            for j in range(0, n + 1):
                temp = dp[j]
                if j == 0:
                    dp[j] = i
                else:
                    if word1[j - 1] == word2[i - 1]:
                        dp[j] = diagUp
                    else:
                        dp[j] = min(dp[j - 1], diagUp, dp[j]) + 1
                diagUp = temp
        print(dp)
        return dp[n]