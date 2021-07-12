#Time  = O(NM)
#Space O(n,m)
class Solution:
    def minDistance(self, string1: str, string2: str) -> int:
        edits = [[y for y in range(len(string1) + 1)] for x in range(len(string2)+1)]
        
        
        for i in range(1, len(string2) + 1):
            edits[i][0] = edits[i-1][0] + 1
            
        
        for i in range(1, len(string2) + 1):
            for j in range(1, len(string1) + 1):
                if string2[i-1] == string1[j-1]:
                    edits[i][j] = edits[i-1][j-1]
                else:
                    edits[i][j] = 1 + min(edits[i][j-1], edits[i-1][j], edits[i-1][j-1])
        return edits[-1][-1]
        