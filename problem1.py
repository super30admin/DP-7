#time complexity o(m*n)
#space complexity o(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(p)
        m = len(s)

        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True

        for j in range(1,n+1):
            pchar = p[j-1]
            if pchar == '*':
                #only 0 case
                dp[0][j] = dp[0][j-2]

        for i in range(1,m+1):
            for j in range(1,n+1):
                schar = s[i-1]
                pchar = p[j-1]

                if pchar != '*':
                    #normal character or a .
                    if pchar == schar or pchar == '.':
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = False
                else:
                    # *
                    # zwro case
                    dp[i][j] = dp[i][j-2]

                    #one case availability
                    #curr char of source matches preceeding char of p
                    if schar == p[j-2] or p[j-2] == '.':
                        dp[i][j]= dp[i-1][j] or dp[i][j-2]
                    else:
                        dp[i][j] = dp[i][j-2]
        
        return dp[m][n]

                

        

        