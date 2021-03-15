'''
Time Complexity: O(mn) // where m and n are length of word1 and word2 resp.
// inserted 2 loops
Space Complexity:O(mn) // where m and n are length of word1 and word2 resp.
// all previous computations are stored.
Approach : Reduce problem to sub-problems
if word is same : dp[i][j]=1+min(dp[i−1][j],dp[i][j−1],dp[i−1][j−1])
or else : dp[i][j]=1+min(dp[i−1][j],dp[i][j−1],dp[i−1][j−1]+1)
'''


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        w1_len = len(word1)
        w2_len = len(word2)
        
        #if length of any word is 0
        if w1_len*w2_len == 0:
            return w1_len+w2_len
        
        # save the compuations in dp matrix
        dp = [[0]*(w2_len+1) for _ in range(w1_len+1)]
        # first row
        for i in range(w1_len+1):
            dp[i][0] = i
        #first column
        for j in range(w2_len+1):
            dp[0][j] = j
            
        for i in range(1,w1_len+1):
            for j in range(1,w2_len+1):
                left = dp[i-1][j]+1
                up = dp[i][j-1]+1
                #word is same
                up_left = dp[i-1][j-1]
                #word is different
                if word1[i-1] != word2[j-1]:
                    up_left +=1
                #choose minimum
                dp[i][j] = min(left,up,up_left)
                
        return dp[w1_len][w2_len]
                
                