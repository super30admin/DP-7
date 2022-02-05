# DP-7

## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

# Time Complexity =O(mn)
# Space Complexity =O(mn)

class Solution:
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        n = len(word1)
        m = len(word2)
        
        # if one of the strings is empty
        if n * m == 0:
            return n + m
        
        # array to store the convertion history
        d = [ [0] * (m + 1) for _ in range(n + 1)]
        
        # init boundaries
        for i in range(n + 1):
            d[i][0] = i
        for j in range(m + 1):
            d[0][j] = j
        
        # DP compute 
        for i in range(1, n + 1):
            for j in range(1, m + 1):
                left = d[i - 1][j] + 1
                down = d[i][j - 1] + 1
                left_down = d[i - 1][j - 1] 
                if word1[i - 1] != word2[j - 1]:
                    left_down += 1
                d[i][j] = min(left, down, left_down)
        
        return d[n][m]


## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

# Time Complexity =O(tp)
# Space Complexity =O(tp)

class Solution(object):
    def isMatch(self, text, pattern):
        sl=len(text)
        pl=len(pattern)
        dp=[]
        for i in range(sl+1):
            dp.append([False for _ in range(pl+1)])
        dp[0][0]=True
        for j in range(1,pl+1):
            if pattern[j-1]=='*':
                dp[0][j]=dp[0][j-2]
        for i in range(1,sl+1):
            for j in range(1,pl+1):
                s=text[i-1]
                p=pattern[j-1]
                if s==p or p=='.':
                    dp[i][j]=dp[i-1][j-1]
                elif p=='*':
                    nottaken=dp[i][j-2]
                    taken=False
                    if (pattern[j-2]==s or pattern[j-2]=='.') and dp[i-1][j]:
                        taken=True
                    dp[i][j]= taken or nottaken
        return dp[sl][pl]          
                
                    
                
                
            
        
       

