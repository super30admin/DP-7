# Time Complexity: O(m*n)
# Space Complexity: O(m*n)
class Solution:
    def isMatch(self, s, p):
        s = ' ' + s
        p = ' ' + p
        output  = [[False]*len(p) for i in range(len(s))]
        output[0][0] = True
        for row in range(len(s)):
            for col in range(1, len(p)):
                if p[col] != '*':
                    if p[col] == s[row] or p[col] == '.' and row != 0:
                        output[row][col] = output[row-1][col-1]
                else:
                    output[row][col] = output[row][col-2] 
                    if (not output[row][col]) and ((s[row] == p[col-1]) or p[col-1] == '.'):
                        output[row][col] = output[row-1][col]
        return output[-1][-1]
        