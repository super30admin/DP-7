# Time Complexity - O(MN)
# Space Complexity - O(MN)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
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
