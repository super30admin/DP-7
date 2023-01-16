'''
Time Complexity --> O(m*n)
Space Complexity --> O(m*n)
Solved using DP
'''

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if len(word2)>len(word1):
            self.minDistance(word2, word1)
        m = len(word1) #columns
        n = len(word2) #rows
        if n==0 and m==0:
            return 0
        if n==0:
            return m
        if m==0:
            return n
        dp = [[0 for x in range(m+1)] for i in range(n+1)]
        
        #Marking the first row
        for j in range(1, m+1):
            dp[0][j] = j

        #Marking the row
        for i in range(1,n+1):
            dp[i][0] = i
        
        #we have 3 operations to complete Update, Add, Delete 
        #update means we are looking for diagonal
        #add means we are looking for top
        #delete means we are looking for left
        # we need to take minimum of three to get the steps 
        for i in range(1, n+1):
            for j in range(1, m+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1+min(dp[i-1][j-1],dp[i-1][j], dp[i][j-1])
        print(dp)
        return dp[-1][-1]