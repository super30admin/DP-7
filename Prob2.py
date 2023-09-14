class Solution:
    def minDistance(self, word1: str, word2: str) -> int:

        #Method 1 - Exhaustive - at each point 4 possible decissions - nothing,edit,add,del -> O(4^n) -  TC

        #Method 2 - DP - TC and SC - O(m*n)
        m=len(word1)
        n=len(word2)
        dp=[[0 for _ in range(n+1)] for _ in range(m+1)]
        

        for j in range(n+1): #fill top row 
            dp[0][j]=j
        
        for i in range(1,m+1):
            for j in range(n+1):
                if j==0: #fill the first col
                    dp[i][j]=i
                else:
                    if word1[i-1]==word2[j-1]: #if characters meet, then do nothing -> diagonal up-left
                        dp[i][j]=dp[i-1][j-1]
                    else: #else, 1+min of edit,add,del -> 1+min(diagonal up-left,top,left) respectively
                        dp[i][j]=1+min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])
        return dp[-1][-1]

        
        