#Time Complexity:O(mn)
#Space Complexity:O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl=len(s)
        pl=len(p)
        dp=[[False for i in range(pl+1)] for j in range(sl+1)] #declare a boolean dp array of size mn
        dp[0][0]=True
        for j in range(1,pl+1):                                 #compare the string with space and fill first row of array
            if p[j-1]=='*':                                     #parse through the first row and if * is encountered copy boolean value from 2 cloumn before
                dp[0][j]=dp[0][j-2]
        for i in range(1,sl+1):                                 #parse through rest of the dp array
            for j in range(1,pl+1):
                if p[j-1]!='*':                                 #if * is encountered in pattern  and the strings before star matches or is a .
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]                   #copy same value as diagonal above
                else:
                    dp[i][j]=dp[i][j-2]                         #if the character in pattern is not a * copy the value of dp array from two columns before
                    if p[j-2]==s[i-1] or p[j-2]=='.':           #if characters match or if the charcater is a . and the previous row value is true then update true at current dp value
                        if dp[i-1][j]:
                            dp[i][j]=True
        return dp[sl][pl]                                       #return the last character from the dp array.