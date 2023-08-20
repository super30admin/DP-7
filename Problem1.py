#Time complexity is O(mn)
#Space complexity is O(mn)
#No issues faced while coding
#COde ran successfully on leetcode
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        #base condition
        if(word1==word2):
            return 0
        #Initializing all the requreid variables
        m=len(word1)
        n=len(word2)
        dp=[[0 for i in range(n+1)] for j in range(m+1)]
        #Updating the values of the first row
        for j in range(1,n+1):
            dp[0][j]=j
        #Iterating through all other values in the matirx
        for i in range(1,m+1):
            for j in range(0,n+1):
                #If its first column, update the value with i
                if(j==0):
                    dp[i][j]=i
                else:
                #If two characters are same, the update the value with diagonal value
                    if(word1[i-1]==word2[j-1]):
                        dp[i][j]=dp[i-1][j-1]
                    #Else, we wneed to take mininmu vlaue from 3 directions and we need to add 
                    #1 to that
                    else:
                        dp[i][j]=1+min(dp[i-1][j],min(dp[i-1][j-1],dp[i],dp[i][j-1]))
        #Finally we will return dp[m][n]
        return dp[m][n]
                