class Solution:
    
    """
    Description: find minimum number of operations to convert word1 to word2 (3 operations allowed: insert, delete or replace a character)
    
    Time Complexity: O(m*n)
    Space Complexity: O(n)
    where m is length of target (word2) and n is length of source (word1)
    
    Approach: Use of dp array
    - start a dp array to identify number of operations matching "-" to each proceeding character of the source string
    - in next iteration (starting first character of target)
      + the first element of the dp array should be the index of the character
      + otherwise, the number of operations are 1 + minimum operations applied to current index (for source) and preceeding index (target)
      + last element of the dp array can be returned as result
    """
    
    def minDistance(self, word1: str, word2: str) -> int:
        
        m = len(word2); n = len(word1)
        dp = [j for j in range(n + 1)]
        
        for i in range(1, m + 1):
            temp1 = dp[0];
            dp[0] = i
            for j in range(1, n + 1):
                temp2 = dp[j]
                if word2[i - 1] == word1[j - 1]:
                    dp[j] = temp1
                else:
                    dp[j] = min(temp1, dp[j - 1], dp[j]) + 1
                    
            temp1 = temp2
            
        return dp[-1]
