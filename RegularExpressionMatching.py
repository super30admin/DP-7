'''
TC: O(m*n) - Iterating through the length of both source and pattern
SC: O(m*n) - created a boolean matrix
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s,p = '-'+s, '-'+p 
        matrix = [[False for _ in range(len(p))] for _ in range(len(s))]
        for i in range(len(p)):
            if s[0] == p[i]:
                matrix[0][i] = True
            else:
                if p[i].isalpha() or p[i] == '.':
                    matrix[0][i] = False
                else:
                    matrix[0][i] = matrix[0][i-2]
        for i in range(1,len(s)):
            for j in range(1,len(p)):
                if s[i] == p[j] or p[j] == '.':
                    matrix[i][j] = matrix[i-1][j-1]
                elif p[j] == '*':
                    if p[j-1] != s[i] and p[j-1] != '.':
                        matrix[i][j] = matrix[i][j-2]
                    else:
                        matrix[i][j] = matrix[i-1][j] or matrix[i][j-2]
                else:
                    matrix[i][j] = False
        return matrix[-1][-1]