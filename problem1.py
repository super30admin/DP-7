# problem 1 

# https://leetcode.com/problems/edit-distance/

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        dp_arr = [[0] * (n + 1) for _ in range(m + 1)]

        # default j row with val
        for j in range(1, n + 1):
            dp_arr[0][j] = j

        for i in range(1, m + 1):
            for j in range(n + 1):
                if j == 0: # default 
                    dp_arr[i][j] = i
                elif word1[i - 1] == word2[j - 1]: # match we dont need to add, just grab from diagonal
                    dp_arr[i][j] = dp_arr[i - 1][j - 1]
                else: # 1 + min of all diagonal
                    dp_arr[i][j] = 1 + min(dp_arr[i - 1][j], dp_arr[i - 1][j - 1], dp_arr[i][j - 1])
        return dp_arr[m][n]