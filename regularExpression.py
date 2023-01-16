'''
Time Complexity --> O(n*m)
Space Compelxity --> O(n*m)
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if p ==".*":
            return True
        m = len(s) #rows   row here represents pattern
        n = len(p) #columns column here represnts s
        dp = [[0 for x in range(n+1)] for x in range(m+1)]
        # checking the first row
        dp[0][0] = 1
        j=0
        for j in range(1, n+1):
            #print(p[j-1])
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2]
            else:
                dp[0][j] = 0
        #now check from 1 row 1 column
        print(dp)
        for i in range(1, m+1):
            for j in range(1, n+1):
                if p[j-1]=="*":
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == s[i-1] or p[j-2]=='.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                    
                else:
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j] = dp[i-1][j-1]
                
        print(dp)
        return dp[-1][-1]
        



