# Time complexity : O(m*n)
# Space complexity : O(n)
# Works on leetcode : Yes
#Approach - Dp[i][j] represents shortest distance between w1[:i] and w2[:j]. So if if c == d, then : f[i][j] = f[i-1][j-1], otherwise
#we have 3 operations - 1. replace c with d: f[i][j] = f[i-1][j-1] + 1  2. add d after c: f[i][j] = f[i][j-1] + 1 and 3.
#delete c: f[i][j] = f[i-1][j] + 1. Since f[i][j] depends on only (i, j, i-1, j-1), we use 2 arrays, pre to denote f[i][j-1]
#and cur to denote (i-1)th array 
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        w1, w2 = len(word1)+1, len(word2)+1
        pre = [k for k in range(w2)]
        for i in range(1,w1):
            cur = [i  for k in range(w2)]
            for j in range(1,w2):
                #pre[j-1] = minDis on word1[1:] and word2[1:] + 1 or 0
                #pre[j] + 1 = insert
                #cur[j-1] + 1 = delete
                cur[j] = min(pre[j-1]+(word1[i-1]!=word2[j-1]), pre[j]+1, cur[j-1]+1)
            pre = cur
        return pre[-1]