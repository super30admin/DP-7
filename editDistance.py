# T:O(m*n)
# S:O(m*n) matrix and memo //O(n) array
# Leetcode: Yes
# Issues: m * n vs n * m

# dp tabulation matrix 

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)

        dp = [[0]*(n+1) for _ in range(m+1)]            

        for j in range(n+1):                                    # fill row 0
            dp[0][j] = j

        for i in range(1,m+1):
            for j in range(n+1):
                if j == 0 :                                     # fill col 0
                    dp[i][0] = i
                else:
                    if word1[i-1] == word2[j-1]:                # same word? edit
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] =1+ min(dp[i][j-1],dp[i-1][j-1],dp[i-1][j])  # otherwise 1+min

        return dp[m][n]
    

# dp tabulation array
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)

        dp = [0]*(n+1)      

        for j in range(n+1):
            dp[j] = j

        diagUp = 0                                                              # save left

        for i in range(1,m+1):
            for j in range(n+1):
                temp = dp[j]
                if j == 0 :
                    dp[j] = i
                else:
                    if word1[i-1] == word2[j-1]:
                        dp[j] = diagUp
                    else:
                        dp[j] = 1 + min(dp[j-1],diagUp,dp[j])
                diagUp = temp                                                   # last value needed update
        return dp[n]
    
# memoisation dfs
class Solution:

    
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        self.memo = [[0]*n for i in range(m)]
        return self.helper(word1,word2,0,0)


    def helper(self, word1, word2, i, j):
        #basecase
        if i == len(word1): return len(word2) - j
        if j == len(word2): return len(word1) - i
        if self.memo[i][j] != 0:                                    # if for non 0 values return 
            return self.memo[i][j]
        #logic
        result = 0 

        if word1[i]== word2[j]:
            result = self.helper(word1,word2,i+1,j+1)
        else:
            add = self.helper(word1,word2,i+1,j)
            edit = self.helper(word1,word2,i+1,j+1)
            delete = self.helper(word1,word2,i,j+1)

            result = min(add,edit,delete) + 1 

        self.memo[i][j] = result                                    # memoise
        return result