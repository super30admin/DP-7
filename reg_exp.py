"""
. means any form of character
* : Zero or more occurrence of previous character
Brute force: with * there is always a choice choose and not choose.
With . and character there is one choice that to include.
                            c    * a * b
                            ""  0/ \1    [c] is formed
                               c*b c*a*b

DP:
        empty   c  *  a   *    b
empty    T      F  T  F   T    F
a       F       F
a       F
b       F
empty-empty string is the base case scenario that when both the pointers are out of bound means source and empty
pattern matches.
_ a to _ c: if incoming values are both char then check if they match. If yes, True else False
_ a to _ c *: has 2 sub problems: _a to _c (this is 2 steps back) and _ a to _c * (can not be choosen)
if incoming is * 2 cases choose and not choose: value is (2 step back) or if previous char is equal to char then T else F
"""


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # number of rows
        m = len(s) + 1
        # number of cols
        n = len(p) + 1

        dp = [[False for _ in range(n)] for _ in range(m)]
        dp[0][0] = True  # base case

        # fill the first row
        for i in range(1, n):
            # if it is * then only we need to check if T or F. Else it is F, since char is comapred with empty string.
            # it is not i but i-1 because the length of the pattern is n-1.
            pchar = p[i - 1]
            if pchar == "*":
                dp[0][i] = dp[0][i - 2]

        for i in range(1, m):  # s
            for j in range(1, n):  # p
                #  it is not j but j-1 because the length of the pattern is n-1.
                if p[j - 1] != "*":
                    # it could be ch or dot(.). Now if same then diagonal char
                    # if curr pattern "." then also it could be equal to ch
                    if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                        #
                        dp[i][j] = dp[i - 1][j - 1]
                    else:
                        dp[i][j] = False
                # pattern current ch is *
                else:
                    # compare the previous pattern ch with current source ch, if equal then both choose and not
                    # choose possible. choose case is 2 steps back and not choose is up.
                    if p[j - 2] == s[i - 1] or p[j - 2] == ".":
                        dp[i][j] = dp[i][j - 2] or dp[i - 1][j]
                    else:
                        # choose case is not possible because prev ch is not equal to curr, for not choose: 2 steps back
                        dp[i][j] = dp[i][j - 2]

        return dp[m - 1][n - 1]


obj = Solution()
ans = obj.isMatch("aab", "c*a*b")
print(ans)
