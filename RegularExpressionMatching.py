# Time is O(m*n) with help of memoization. Space is also O(m*n) for using cache and recursion depth

# Intuition to choose a couple of scenarios when we have a *, we can either chose or not chose. If we chose then we need to check if the preceding value is equal to a value in
# source otherwise we can skip it by jumping two steps ahead.
# Then if characters are same then we can do a recursion with i + 1 and j + 1

# Once both the strings length have passed that means we were able to match and we can return true

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        cache = {}

        def dfs(i, j):
            if i >= len(s) and j >= len(p):
                return True
            if j >= len(p): return False

            if (i, j) in cache:
                return cache[(i, j)]
            firstMatch = i < len(s) and (s[i] == p[j] or p[j] == ".")

            if (j + 1) < len(p) and p[j + 1] == "*":
                # Dont chose and then chose
                cache[(i, j)] = (dfs(i, j + 2) or (firstMatch and dfs(i + 1, j)))
                return cache[(i, j)]

            if firstMatch:
                cache[(i, j)] = dfs(i + 1, j + 1)
                return cache[(i, j)]

            cache[(i, j)] = False
            return False

        return dfs(0, 0)


# This is bottom up solution, we can use this to optimize the space: O(n). However, the time remains the same O(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)

        dp = [False] * (n + 1)

        dp[0] = True

        for i in range(1, n + 1):
            if p[i - 1] == "*":
                dp[i] = dp[i - 2]
            else:
                dp[i] = False
        diagUp = False
        for i in range(1, m + 1):
            diagUp = dp[0]
            dp[0] = False

            for j in range(1, n + 1):
                temp = dp[j]
                if p[j - 1] != "*":
                    if s[i - 1] == p[j - 1] or p[j - 1] == ".":
                        dp[j] = diagUp
                    else:
                        dp[j] = False
                else:
                    if s[i - 1] == p[j - 2] or p[j - 2] == ".":
                        dp[j] = dp[j] or dp[j - 2]
                    else:
                        dp[j] = dp[j - 2]
                diagUp = temp

        return dp[n]