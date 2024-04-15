'''
TC: O(m*n) - Iterating through the length of both word1 and word2
SC: O(m*n) - created a matrix to keep track of change
'''
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        word1, word2 = '-'+word1, '-'+word2
        matrix = [[0 for _ in range(len(word1))] for _ in range(len(word2))]
        for i in range(1,len(word1)):
            matrix[0][i] = matrix[0][i-1]+1
        for i in range(1,len(word2)):
            matrix[i][0] = matrix[i-1][0]+1
        for i in range(1,len(word2)):
            for j in range(1,len(word1)):
                if word1[j] == word2[i]:
                    matrix[i][j] = matrix[i-1][j-1]
                else:
                    matrix[i][j] = min(matrix[i-1][j], matrix[i][j-1], matrix[i-1][j-1])+1
        return matrix[-1][-1]