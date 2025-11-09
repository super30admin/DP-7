# Time is O(m*n) where m and n are the length of word1 and word2. Space is O(m*n) for using the self.memo and recursion depth stack

# The intuition here is to do a top down DP recursive approach with two cases, one when char matches, other when char doesn't match.

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        self.memo = {}
        def helper(i, j):
            if i == 0: return j
            if j == 0: return i

            if (i,j) in self.memo:
                return self.memo[(i,j)]
            res = 0
            if word1[i-1] != word2[j-1]:
                case1 = helper(i-1, j-1)
                case2 = helper(i, j-1)
                case3 = helper(i-1, j)

                res = 1 + min(case1, case2, case3)
            else:
                res = helper(i-1, j-1)
            self.memo[(i,j)] = res
            return res

        return helper(len(word1), len(word2))

    # This is a bottom up solution, the logic is pretty much the same. The time is O(m*n). However, the space is O(n) as we could optimize since we only need one row. 
    def minDistanceBFS(self, word1: str, word2: str) -> int:
        if len(word2) > len(word1):
            word1, word2 = word2, word1
        m = len(word1)
        n = len(word2)

        dp = [j for j in range(n + 1)]

        for i in range(1, m + 1):  # 1 - 5
            prevDiag = dp[0]
            dp[0] = i
            for j in range(1, n + 1):  # 1 - 4
                temp = dp[j]
                if word1[i - 1] != word2[j - 1]:
                    dp[j] = 1 + min(dp[j], dp[j - 1], prevDiag)
                else:
                    dp[j] = prevDiag
                prevDiag = temp

        return dp[n]