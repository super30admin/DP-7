class Solution:
    def isMatch(self, s: str, p: str) -> bool:

        #Method 1 - Be exhaustive - 2^(m+n) TC
        #method 2 - DP - O(m*n) TC and SC
        if s==p: return True
        m=len(s)
        n=len(p)

        dp=[[False for _ in range(n+1)] for _ in range(m+1)]
        dp[0][0]=True

        #top row
        for j in range (1,n+1): #in top row, if its a character or a . it's False.
        #only for * case we need to check 2 steps back
            c=p[j-1]
            if c=='*':
                dp[0][j]=dp[0][j-2]
        #rest of the dp matrix
        for i in  range(1,m+1):
            for j in range(1,n+1):
                pchar=p[j-1]
                if pchar!='*': #if not star, check if it's a . or char, it's a char it needs to equal to current char in source else False
                    if pchar==s[i-1] or pchar=='.': #if either of the above conditions, diagonal up-left
                        dp[i][j]=dp[i-1][j-1] #diagonal up-left
                else: #if it is a star, we have 0 and 1 case to consider. 
                    if p[j-2]==s[i-1] or p[j-2]=='.': #zero case, check if curr-2 index in pattern is either equal to source[i] or it's a .
                        dp[i][j]=dp[i][j-2] or dp[i-1][j]
                    else: #one case 
                        dp[i][j]=dp[i][j-2]
        return dp[-1][-1]
                    

                    