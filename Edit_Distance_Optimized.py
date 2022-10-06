# Optimized DP (Space)
# Time Complexity : O(n*n)
# Space complexity : O(n) --> length of smaller word
# Leetcode : Solved and submitted

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
       # if the words match, then return 0 as no changes are required
        if word1 == word2:
            return 0
        
        # find the length of the two words
        rows = len(word1)
        cols = len(word2)
        
        # we want to make sure that cols is the lesser length out of the two words
        if rows > cols:
            return self.minDistance(word2, word1)
        
        # create a dp array of size cols which is less space
        dp = [0 for _ in range(cols+1)]
        
        # fill the row with elements as the value of j
        for j in range(1, cols+1):
            dp[j] = j
        
        # traverse the matrix and start filling out the values based on the rule
        for i in range(1,rows+1):
            # for the first step, take the diagonal as the first element and change the dp[0] to the next value as we won't return to this later
            diag = dp[0]
            dp[0] = i
            for j in range(1,cols+1):
                # save the current element in a temp variable 
                temp = dp[j]
                
                # if the character matches then we copy the value from the diagonal which we had saved
                if word1[i-1] == word2[j-1]:
                    dp[j] = diag
                else:
                    # other we find the minimum out of all 3 neighbors (add(top), delete(left), update(diagonal up)) and 1 to it
                    dp[j] = min(dp[j], min(diag,dp[j-1])) + 1
                # for each index, we change the diagonal to the temp value that we had saved as we overwrite it
                diag = temp
        
        # return the value of the last index
        return dp[cols]
