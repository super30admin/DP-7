# Approach - In line
# Time - O(M * N)
# Space - O(M * N)


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        cols = len(word1)
        rows = len(word2)

        # dummy (i0,j0) -- '_' charac

        dp = [[0 for _ in range(cols + 1)] for _ in range(rows + 1)]
        
        # fill top row's columns  - base case
        for col in range(cols+1):
            dp[0][col] = col
            
            
        # fill first column of all rows - base case
        for row in range(rows+1):
            dp[row][0] = row
            
        # iterate on remaining rows, cols
        for row in range(1, rows+1):
            for col in range(1, cols+1):
                # col-1 , row-1 since dummy var _ is at 0th index 
                # if charac matches, take from diagnol
                if word1[col-1] == word2[row-1]:
                    dp[row][col] = dp[row-1][col-1]
                    
                else:
                    # 1 + choose between min(update(diag), add(above row), delete(same row go back) options)
                    dp[row][col] = 1 + min(dp[row-1][col-1], dp[row-1][col], dp[row][col-1])
                    
        return dp[-1][-1]
        
      
        