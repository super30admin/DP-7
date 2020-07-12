'''
Solution:
1.  1.  Perform DP as there are repeated subproblems.

Recursive equation:
1.  If string is empty => pattern depends on the truth value of 2 indices before.
2.  If s[i] == p[j] or p[j] == '.' => isMatch(s, p, i-1, j-1)
3.  If p[j] == '*':
    if isMatch(s, p, i, j-2) == False:
        if p[j-1] == '.' or p[j-1] == s[i] => isMatch(s, p, i-1, j)
4.  Else, isMatch(...) = False

Time Complexity:    O(m x n) for both approaches
Space Complexity:   O(m x n) for approach - 1 and O(m) for approach - 2

--- Passed all testcases successfully on leetcode for both the solutions.
'''



class RegExDP-I:
    def isMatch(self, s: str, p: str) -> bool:
        
        #   initializations
        m = len(p)
        n = len(s)
        
        dpMatches = [[False for i in range(m+1)] for j in range(n+1)]
        dpMatches[0][0] = True
        
        #   fill first row
        for c in range(1, m+1):
            if p[c-1] == '*':
                dpMatches[0][c] = dpMatches[0][c-2]
         
        #   fill DP table       
        for r in range(1, n+1):
            for c in range(1, m+1):
                
                #   condition 1
                if (p[c-1] == '.' or s[r-1] == p[c-1]):
                    dpMatches[r][c] = dpMatches[r-1][c-1]
                
                #   condition 2 and 3   
                elif (p[c-1] == '*'):
                    dpMatches[r][c] = dpMatches[r][c-2]
                    if (p[c-2] == '.' or s[r-1] == p[c-2]):
                        dpMatches[r][c] = dpMatches[r][c] or dpMatches[r-1][c]
                
                #   condition 4
                else:
                    dpMatches[r][c] = False
        
        #   return last cell            
        return dpMatches[n][m]

class RegExDP-II:
    def isMatch(self, s: str, p: str) -> bool:
        
        #   entire proedure is same except we have only 2 rows which are required.
        m = len(p)
        n = len(s)
        
        dpMatches = [[False for i in range(m+1)] for j in range(2)]
        dpMatches[0][0] = True
        
        for c in range(1, m+1):
            if p[c-1] == '*':
                dpMatches[0][c] = dpMatches[0][c-2]
                
        for r in range(1, n+1):
            dpMatches[1] = [False]
            
            for c in range(1, m+1):
                
                if (p[c-1] == '.' or s[r-1] == p[c-1]):
                    dpMatches[1].append(dpMatches[0][c-1])
                    
                elif (p[c-1] == '*'):
                    dpMatches[1].append(dpMatches[1][c-2])
                    if (p[c-2] == '.' or s[r-1] == p[c-2]):
                        dpMatches[1][c] = dpMatches[1][c] or dpMatches[0][c]
                
                else:
                    dpMatches[1].append(False)
                    
            dpMatches[0] = dpMatches[1]
        
        return dpMatches[0][m]