"""
Cases for DP Matrix --
eg.
s = "aab"
p = "c*a*b"
1. when we have same character, we will take diagonal value. see the case ==> '_c * a' will it match' _a'
2. If we have different characters for comparison ==> straighforward false. eg ==>  ' _c * a *' will it match '_aab'
3. When i have * and some other character ==> we have two cases 0 and 1. eg ==> '_c * a *' will it match '_aa'
For case 0 ==> we go 2 steps back
For case 1 ==> if previous character is matching, we take the value from the cell above or else its false

TC - O(m * n)
SC - O(m * n)
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p: return True

        m = len(s)
        n = len(p)

        dp = [[False for i in range(n+1)]for i in range(m+1)]
        # print(dp)

        # mark the 0th row 0th col as true since _ matches _
        dp[0][0] = True

        # fill 1st row
        # we start from 1st col since 0th col is already true as seen in line above
        for j in range(1, n+1):
            # j-1 because the for loop is starting from 1st index but
            # we are checking string p's characters from 0th idx
            if p[j-1] == '*':
                # check value from two steps back
                dp[0][j] = dp[0][j-2]

        for i in range(1, m+1):
            for j in range(1, n+1):
                if p[j-1] != '*':
                    if p[j-1] == s[i-1] or p[j-1] == '.':
                        # get value from diagonal element
                        dp[i][j] = dp[i-1][j-1]
                else:
                    # zero case or case zero
                    dp[i][j] = dp[i][j-2]
                    print("dp[i][j]: ", dp[i][j])
                    # check for one case
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        # print("dp[i][j] or dp[i-1][j]: ", dp[i][j] or dp[i-1][j])
                        dp[i][j] = dp[i][j] or dp[i-1][j]
        # print(dp)
        return dp[m][n]