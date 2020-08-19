# Time Complexity : O(mxn) 
# Space Complexity :O(mxn)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
# 1) consider the substring for word1 in a particular mxn dp matrix. For the same cell, consider the word2 substring
#    if there is a matching character, exlcude that, and look for the remaining substring`s value in the dp matrix
# 2) Formula is :
#       If 2 characters are matching then use dp[i-1][j-1] previous cells value 
#       else, take the min of all the 3 preivous neighbours and add +1 to it. As we will need to form that updatetime A combination of a date and a time. Attributes: ()
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp = [[0 for _ in range(len(word1)+1)] for _ in range(len(word2)+1)]
        
        # 1st Row filling
        for i in range(len(dp[0])):
            dp[0][i] = i 
        
        # 1st Column Filling
        for i in range(len(dp)):
            dp[i][0] = i 
            
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min( dp[i-1][j-1], dp[i][j-1], dp[i-1][j]) + 1 
        
        return dp[-1][-1]

if __name__ == "__main__":
    s = Solution()
    
    # Test case 1
    res1 = s.minDistance("hor","r")
    assert res1 == 2 
    
    # Test case 2 
    res2 = s.minDistance("horse","ros")
    assert res2 == 3 
    
    # Test case 3 
    res3 = s.minDistance("intention","execution")  
    assert res3 == 5