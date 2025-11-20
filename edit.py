# Time Complexity : O(N*M) as we are iterating through both the words
# Space Complexity : O(M) as we are using extra space to store the dp array
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Np

# Your code here along with comments explaining your approach:
# I am using a dynamic programming approach to find the minimum number of operations required to convert word1 to word2.
# I maintain a dp array where dp[j] represents the minimum number of operations required to convert the first i characters of word1 to the first j characters of word2.
# I initialize the dp array with the base case values, where dp[j] is initialized to j (the number of insertions required to convert an empty string to the first j characters of word2).
# I then iterate through each character of word1 and word2, updating the dp array based on whether the characters match or not.
# If the characters match, I set dp[j] to the value of dia (the diagonal value from the previous iteration).
# If the characters do not match, I set dp[j] to 1 plus the minimum of the values from the left (dp[j]), diagonal (dia), and top (dp[j-1]) positions, representing the three possible operations (deletion, substitution, insertion).
# Finally, I return dp[m] as the result, which represents the minimum number of operations required to convert word1 to word2.  

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n = len(word1)
        m = len(word2)
        dp = [j for j in range(m + 1)]
        for i in range(1,n+1):
            dia = dp[0]
            dp[0] = i
            for j in range(1,m+1):
                temp = dp[j]
                if word1[i-1] == word2[j-1]:
                    dp[j] = dia
                else:
                    dp[j] = 1 + min(dp[j],dia,dp[j-1])
                dia = temp
        return dp[m]