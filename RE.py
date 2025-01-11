# The isMatch method checks if string `s` matches pattern `p` with '.' and '*' as special characters.

# Dynamic Programming Approach:
# - Use a 1D `dp` array to save space, representing match results for suffixes of `p` with `s`.
# - Traverse both `s` and `p` backward:
#   - For '*', check if skipping or matching multiple characters satisfies the pattern.
#   - For '.', or matching characters, propagate the match from the previous state.
# - Update `dp` iteratively for each character pair.

# TC: O(m * n) - Process each character of `s` and `p`.
# SC: O(n) - Space for the DP array.


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [False] * (len(p) + 1)
        dp[len(p)] = True
        
        for i in range(len(s), -1, -1):
            dp1 = dp[len(p)]
            dp[len(p)] = (i == len(s))
            
            for j in range(len(p) - 1, -1, -1):
                match = i < len(s) and (s[i] == p[j] or p[j] == ".")
                res = False
                if (j + 1) < len(p) and p[j + 1] == "*":
                    res = dp[j + 2]
                    if match:
                        res |= dp[j]
                elif match:
                    res = dp1
                dp[j], dp1 = res, dp[j]

        return dp[0]