'''
Time Complexity - O(m*n). i.e. Length of the two words
Space Complexity - O(m*n), O(n) for optimized DP

Works on Leetcode
'''
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        if m > n:
            self.minDistance(word2, word1)
        # dp = [[0 for j in range(n+1)] for i in range(m+1)]
        # for j in range(1,n+1):
        # First character in both words is - so total distance for each character will be position of character in word
        #     dp[0][j] = j
        # for i in range(1,m+1):
        # First character in both words is - so total distance for each character will be position of character in word
        #     dp[i][0] = i
        # for i in range(1,m+1):
        #     for j in range(1, n+1):
        #         if word1[i-1] == word2[j-1] :
        #       if current characters are match take the current distance is same as that until previous characters in both strings i.e. diagonally upwards to left
        #             dp[i][j] = dp[i-1][j-1]
        #         else:
        #         else the distance will be 1 + minimum of edit, delete or replacing the characters i.e. diagonally left up, left, up  
        #             dp[i][j] = 1 + min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1])
        # print(dp[m][n-1])
        # return dp[m][n]

        #Space Optimized Bottom Up DP
        prev = [j for j in range(n+1)]
        for i in range(1,m+1):
            curr = [0 for k in range(n+1)]
            curr[0] = i
            for j in range(1, n+1):
                if word1[i-1] == word2[j-1] :
                    curr[j] = prev[j-1]
                else:
                    curr[j] = 1 + min(prev[j], prev[j-1], curr[j-1])
            prev = curr
        return prev[n]
        