"""
1. Since we need to edit distance and find minimum moves, BFS can be used (BRUTE FORCE)
2. However, there are repeated sub problems --> which leads to DP
3. We create an adjacency matrix with columns (large) to update/add/delete and rows is the small string
4. Add an extra empty string to row and column, respectively in adjacency matrix
5. Process the matrix and at every step do update, add, delete operations. Choose the operation which takes minimum moves/steps
6. Continue till the end of the matrix

2D DP matrix solution
TC - O(m*n)
SC - O(m*n)

Optimized 1D Array solution
TC - O(m * n)
SC - O(n) ==> n = string of min length
"""


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2: return 0

        m = len(word1)  # rows
        n = len(word2)  # cols

        # we always want larger length string to be taking rows position
        # see dp matrix example 1==> horse, ros
        # if n > m:
        #     self.minDistance(word2, word1)

        dp = [0 for j in range(n + 1)]
        # print(dp)

        # mark 0th row
        # we start from 1 since 0th row is already marked as 0
        for j in range(0, n + 1):
            dp[j] = j

        # print(dp)

        # //update(replace) or add or delete operations
        for i in range(1, m + 1):
            # we use prev to grab upper diagonal left element value
            prev = dp[0]
            # update dp[0] to increment as we proceed through the word1 string
            dp[0] = i
            for j in range(1, n + 1):
                temp = dp[j]
                # -1 because even though dp is starting at 1st row and 1st col,
                # the word chars are starting at 0th index
                if word1[i - 1] == word2[j - 1]:
                    # get upper diagoal element value - stored as prev
                    dp[j] = prev
                else:
                    # 1 step behind, top, diagonal
                    # +1 since current operation
                    dp[j] = min(dp[j - 1], dp[j], prev) + 1
                # update prev's value
                prev = temp

        return dp[n]