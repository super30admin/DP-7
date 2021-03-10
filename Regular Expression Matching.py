class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Recursion
        #Time Complexity: Exponential
        #Space Complexity: O(len(s) * len(p))
        
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