# Time: O(len(s)*len(p))
# Space: O(len(s)*len(p))
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        st = len(s)
        pt = len(p)
        dp = [[False for x in range(pt+1)] for y in range(st+1)]
        dp[0][0] = True
        for j in range(1, pt+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]
        for i in range(1, st+1):
            for j in range(1, pt+1):
                c = s[i-1]
                c1 = p[j-1]
                if c == c1 or c1 == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif c1 == '*':
                    not_taken = dp[i][j-2]
                    taken = False
                    if (p[j-2] == c or p[j-2] == '.') and dp[i-1][j] == True:
                        taken = True
                    dp[i][j] = not_taken or taken
        #print(dp)
        return dp[st][pt]
