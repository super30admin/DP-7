# time - O(mn)
# sapce - O(mn)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1) + 1
        n = len(word2) + 1
        matrix = [[0 for j in range(m)] for i in range(n)]

        for i in range(m):
            matrix[0][i] = i

        for j in range(n):
            matrix[j][0] = j
        # print(matrix)

        for i in range(1, n):
            for j in range(1, m):
                if word1[j - 1] == word2[i - 1]:
                    matrix[i][j] = matrix[i - 1][j - 1]
                else:
                    matrix[i][j] = 1 + min(matrix[i - 1][j], matrix[i - 1][j - 1], matrix[i][j - 1])
        return matrix[n - 1][m - 1]