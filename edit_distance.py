"""
word1 = "h o r s e",
         ^
         i
word2 = "r o s"
        ^
        j
Brute force method: for h there are 4 operations: replace, delete, add/insert and no operation
same as "o", "r", "s", "e"
if choose replace operation then:  replace h with r, then move i+1 and j+1
if choose delete operation: j remains same and i+1
if choose add operation: i and j+1
if choose noop: i+1, j+1
TC: 2 power (m+n)
DP: Repeated sub-problems: children of h (add and edit): r o (i) s, from "orse" how many operations to
form "os".
        empty   h  o  r   s     e
empty     0     1  2  3   4     5
r        1      2  2  2   3    4
o       2
s       3
How many operations to form empty string from empty then empty + h, then  empty + h + o?
How many operations to form empty + r string from empty then empty + h, then  empty + h + o?
_ h to _ r = for delete the sub-problem becomes: _ to _ r comes from the left.
            for edit, the sub-problem becomes: _ h (replaced by r) to _ r. r and r are same and left with
            _ to _: this problem comes from diagonal up.
            for  add operation: the sub problem becomes: _ r to _ h (r is added), r and r same, the sub problem
            is now _r to _ h which is: up
            The value for th cell is miminum of (left, up, diagonal up) + 1
For no operation: diagonal up: _ r to  _ h o r : diagonal up
TC: O(m*n) and SC: O(m*n)
"""


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # create a dp matrix
        m = len(word1) + 1  # number of cols
        n = len(word2) + 1  # number of rows
        dp = [[0 for _ in range(m)] for _ in range(n)]

        # fill the first row
        for i in range(m):
            dp[0][i] = i

        # fill the first col
        for i in range(n):
            dp[i][0] = i

        print(dp)

        # fill the dp table:
        for i in range(1, n):
            for j in range(1, m):
                # check if incoming ch is equal to last ch
                # incoming is jth
                if word2[i - 1] != word1[j - 1]:
                    dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
                else:
                    dp[i][j] = dp[i - 1][j - 1]
        print(dp)
        print(len(dp), len(dp[0]))
        return dp[n-1][m-1]


obj = Solution()
ans = obj.minDistance("horse", "ros")
print(ans)
