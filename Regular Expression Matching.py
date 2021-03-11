class Solution:
    #Solution 1
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Dynamic Programming
        #Time Complexity: O(m * n)
        #Space Complexity: O(m * n)
        #where, m and n are the lengths of s and p, respectively
        
        dp = [[False for j in range(len(p) + 1)] for i in range(len(s) + 1)]
        dp[0][0] = True
        
        #first row
        for j in range(1, len(dp[0])):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]
                
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                #normal character
                if p[j - 1] != '*':
                    if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                        dp[i][j] = dp[i - 1][j - 1]
                
                else:   # '*'
                    #match none
                    dp[i][j] = dp[i][j - 2]
                    if dp[i][j]:
                        continue
                    
                    #match one
                    if p[j - 2] == s[i - 1] or p[j - 2] == '.':
                        dp[i][j] = dp[i - 1][j]
        
        return dp[-1][-1]
    
    #Solution 2
    """
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Recursion
        #Time Complexity: Exponential
        #Space Complexity: Exponential
        
        self.result = False
        self.helper(s, p, 0, 0)
        return self.result
    
    def helper(self, s, p, sIndex, pIndex):
        #base
        if sIndex == len(s) and pIndex == len(p):
            self.result = True
            return
        
        elif pIndex == len(p):
            return
        
        #logic
        if pIndex + 1 < len(p) and p[pIndex + 1] == '*' :   # match any number of characters
            self.helper(s, p, sIndex, pIndex + 2)
            if sIndex < len(s) and p[pIndex] in ['.', s[sIndex]]:
                self.helper(s, p, sIndex + 1, pIndex)
            
        else:   # match exactly one character
            if sIndex < len(s) and p[pIndex] in ['.', s[sIndex]]:
                self.helper(s, p, sIndex + 1, pIndex + 1)
    """