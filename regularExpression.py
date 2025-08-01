"""
Use a 2D dp table where dp[i][j] means: does s[0:i] match p[0:j]?
Handle * specially: it can eliminate the previous character or repeat it
Fill the table using bottom-up DP logic
"""
"""
Time Complexity: O(m * n)
Space Complexity: O(m * n)
"""
class regularExpression:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True 

        for j in range(2, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == '.' or p[j - 1] == s[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':

                    dp[i][j] = dp[i][j - 2]

                    if p[j - 2] == '.' or p[j - 2] == s[i - 1]:
                        dp[i][j] = dp[i][j] or dp[i - 1][j]

        return dp[m][n]

if __name__ == "__main__":
    obj = regularExpression()
    print(obj.isMatch("aa", "a"))    
    print(obj.isMatch("aa", "a*"))  
    print(obj.isMatch("ab", ".*"))  
    print(obj.isMatch("mississippi", "mis*is*p*."))  
