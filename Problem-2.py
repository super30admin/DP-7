class Solution:
    # Approach 1: DP
    """
    TC: O(mn). m -> len of s, n -> len of p
    SC: O(mn)
    
    """
    def isMatch(self, s: str, p: str) -> bool:
        if s == p:
            return True
        lp = len(p)
        ls = len(s)
        dp = [[False]*(lp+1) for _ in range(ls+1)]
        
        dp[0][0] = True
        for j in range(1, lp+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]

        
        for i in range(1, ls + 1):
            for j in range(1, lp + 1):
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2] # case 0: consider previous char zero times: take the value from previous 2 steps in dp list
                    if p[j-2] == s[i-1] or p[j-2] == '.': # case 1: consider previous char atleast 1 time: the previous char in the pattern is equal to current char in string, take from top. If either of the two is True, the current value can be True
                        dp[i][j] = dp[i][j] or dp[i-1][j]
        return dp[-1][-1]