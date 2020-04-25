'''
Solution:
1.  Perform DP as there are repeated subproblems.

Recursive equation:
1.  all insertions if word1 id empty
2.  all deletions if word2 is empty
3.  if word1[i] == word2[j] ==> minDist(word1, word2, i-1, j-1)
4. else, 1 + min(deletion, insertion, updation)
Deletion => minDist(word1, word2, i-1, j)
Insertion => minDist(word1, word2, i, j-1)
Updation => minDist(word1, word2, i-1, j-1)

Time Complexity:    O(m x n) for both approaches
Space Complexity:   O(m x n) for approach - 1 and O(m) for approach - 2

--- Passed all testcases successfully on leetcode for both the solutions.
'''


class MinDistanceDP-I:
    def minDistance(self, word1: str, word2: str) -> int:
        
        m = len(word1)  #   cols
        n = len(word2)  #   rows
        
        editDists = [[0 for i in range(m+1)] for j in range(n+1)]
        
        #   if length(word2) == 0
        for c in range(m+1):
            editDists[0][c] = c
        
        #   if length(word1) == 0    
        for r in range(n+1):
            editDists[r][0] = r
        
        #   iterate the DP table   
        for r in range(1, n+1):
            for c in range(1, m+1):
                
                #   if words match => diagonally top-left element
                if (word1[c-1] == word2[r-1]):
                    editDists[r][c] = editDists[r-1][c-1]
                    
                else:
                    editDists[r][c] = 1 + min(editDists[r][c-1],    #   delete
                                             editDists[r-1][c-1],   #   replace
                                             editDists[r-1][c])     #   insert
        
        #   return the last cell           
        return editDists[n][m]


class MinDistanceDP-II:
    def minDistance(self, word1: str, word2: str) -> int:
        
        m = len(word1)  #   cols
        n = len(word2)  #   rows
        
        editDists = [[], []]
        
        #   if length(word2) == 0
        for c in range(m+1):
            editDists[0].append(c)
           
        for r in range(1, n+1):
            editDists[1] = [r]
            
            for c in range(1, m+1):
                
                if (word1[c-1] == word2[r-1]):
                    editDists[1].append(editDists[0][c-1])
                    
                else:
                    newValue = 1 + min(editDists[1][c-1],    #   delete
                                             editDists[0][c-1],   #   replace
                                             editDists[0][c])     #   insert
                    editDists[1].append(newValue)
            
            editDists[0] = editDists[1]
        
        return editDists[0][m]