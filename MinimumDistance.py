-------------------------Miniumum Distance------------------------------------------
# Time Complexity : O(MXN) as M is the number of word1 and N is number of word2 
# Space Complexity : O(MXN) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# 
# We will iterate through word1 and word2 and check all the 3 conditions add, update and delete of the strings. if word1 element is 
# equal to the word2 element then we will check number of steps needed until previous step. if they are not same then we will take min of 
# all add, update and delete steps that is min of all previous surrounding elements.

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if not word1 and not word2:
            return 0
        
        m = len(word1)
        n = len(word2)
        dp = [[0 for i in range(n+1)] for j in range(m+1)]
        
        for i in range(m+1):
            for j in range(n+1):
                if i == 0:
                    dp[i][j] = j
                elif j == 0:
                    dp[i][j] = i
                elif word1[i-1] == word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
        return dp[-1][-1]