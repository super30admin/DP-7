#Time Complexity:O(mn)
#Space Complexity:O(mn)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m=len(word1)
        n=len(word2)
        dp=[[0 for i in range(n+1)] for j in range(m+1)]             #create dp array of size mn and fill the first row and column  representing blank spaces.
        for j in range(n+1):
            dp[0][j]=j
        for i in range(m+1):
            dp[i][0]=i
        for i in range(1,m+1):                                      #parse through the rest of the array
            for j in range(1,n+1):
                if word2[j-1]==word1[i-1]:                          #if the characters of two strings match update with diagonal top left value
                    dp[i][j]=dp[i-1][j-1]
                else:                                               #else update with incremented value of minimum amogst top, left and top left diagonal values
                    dp[i][j]=1+min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
        return dp[m][n]                                             #return the value at the end of the dp array