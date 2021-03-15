'''
Time Complexity: O(mn) // where m and n are length of pattern and string resp.
Space Complexity:O(mn) // where m and n are length of pattern and string resp.
// all previous computations are stored.
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:

        dp = [[False for _ in range(len(p) + 1)] for _ in range(len(s) + 1)]

        dp[0][0] = True 

        for col in range(1, len(p) + 1):
            if p[col-1] == "*":
                dp[0][col] = dp[0][col-2]

        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):

                if p[j-1] != '*':
                    
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]

                else:
                    dp[i][j] = dp[i][j-2]

                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        if dp[i-1][j]:
                            dp[i][j] = True

        return dp[-1][-1]

