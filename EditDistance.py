
"""
TC=O(MN) len word1 and word2 M and N respectively 
SC=O(MN)  in dp table 
DP table will store the number of steps to take to change the word using Update,delete or addition operation.DP table has additional row and column for "-" 

Brute Force will be 3^N kind solution 


"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        n=len(word1)#col
        m=len(word2)#row
        
        dp=[[0]*(n+1) for _ in range(m+1)] #DP string -HORSE in row and -ROS in column
        
        #Fill first row
        for i in range(1,n+1): #dp[0][0] will be 0
            dp[0][i]=i
            
        #Fill first col
        for j in range (1,m+1):
            dp[j][0]=j
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if(word1[j-1]==word2[i-1]):
                    dp[i][j] = dp[i-1][j-1] #take it from one row up diagonal elem
                else:
                    dp[i][j]=1+min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
                    # take from U,D,A minimum +1
        return dp[m][n]
        
            
        