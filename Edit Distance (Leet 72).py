'''
Using DP
Time: O(len(word1)*len(word2))
Space: O(len(word1)*len(word2))
'''

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        m = len(word1)
        n = len(word2)
        
        dp = [[0 for _ in range(m+1)] for _ in range(n+1)]
        
        # top row
        
        for i in range(1, m+1):
            dp[0][i] = i
        
        # left column
        
        for j in range(1, n+1):
            dp[j][0] = j
        
        for i in range(1,n+1):
            for j in range(1,m+1):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])  
        
        
        return dp[-1][-1]
            

'''
Using DP
Time: O(len(word1)*len(word2))
Space: O(len(word1)) # any one word
'''

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        m = len(word1)
        n = len(word2)
        
        dp = [0 for _ in range(m+1)]
        
        # top row
        
        for i in range(1, m+1):
            dp[i] = i
        
        for i in range(1,n+1):
            diag = dp[0]
            for j in range(0,m+1):
                temp = dp[j]
                
                if j == 0:
                    dp[j] = i
                    continue
                
                if word2[i-1] == word1[j-1]:
                    dp[j] = diag
                else:
                    dp[j] = 1 + min(dp[j-1],temp,diag)  
                diag = temp
            
        return dp[-1]
            
        
