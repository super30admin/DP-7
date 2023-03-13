# Time Complexity :
# O (NM)

# Space Complexity :
# O(NM)

# Did this code successfully run on Leetcode :
#Yes

#We store a DP matrix that stores at each point how many steps are required to change the string until now in the column to the string in the row. We first fill the first column and first row in ascending order since we need that many steps to cahnge an empty string to a string of any lenght.

#Then we fill it row by row - if the characters are the same (in the word1 and word2), then the number of steps to change is the same number of steps as one character less for both. If not, then it's the minimum of either replacing a character, removing a character or adding a character - we select the minimum and update the current value at DP 

#The final value in the DP matrix is the total number of least steps required

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:

        m = len(word1)
        n = len(word2)
        if m < n :
            return self.minDistance(word2,word1)

        dp = [0] * (m+1)
        for j in range(1,m+1) :
            dp[j] =  dp[j-1] + 1

        for i in range(1,n+1) :
            diag_up = i-1
            dp[0] = i
            for j in range(1,m+1):
                temp = dp[j]
                if word2[i-1] != word1[j-1] :
                    dp[j] = min(diag_up+1,dp[j-1]+1, dp[j]+1)
                else :
                    dp[j] = diag_up
                diag_up = temp
        return dp[-1]
