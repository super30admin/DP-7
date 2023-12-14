# Time: O(mn)
# Space: O(m) for 1d array else O(mn)
# Did it run on Leetcode: yes

# we buidl the dp matrix just liek other problem, which can also be replaced by a 1d array instead.
# if the characters are smae - we take the value diagonally up
# if not same - take min of all three possible cases (update, add, delete) (diagonally up, one step back) up + 1
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        m=len(word1)
        n=len(word2)
        if(m<n): return self.minDistance(word2,word1)
        dp=[[0 for i in range(n+1)] for j in range(m+1)]
        for j in range(n+1):
            dp[0][j]=j
        for i in range(1,m+1):
            dp[i][0]=i
            for j in range(1,n+1):
                if(word1[i-1]==word2[j-1]):
                    dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=min(dp[i-1][j-1], min(dp[i][j-1], dp[i-1][j])) + 1
        return dp[m][n]