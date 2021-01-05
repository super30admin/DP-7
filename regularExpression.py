"""
TC=O(NM) where N and M are len of s and p str 
SC=O(NM)
"""

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s1=len(s)
        p1=len(p)
        dp=[[False]*(p1+1) for _ in range(s1+1)] #Patter in col and S in row.+1 for '-'       
        
        dp[0][0]=True
        #fill top row 
        for i in range(1,p1+1):
            if(p[i-1]=='*'):
                dp[0][i]=dp[0][i-2]#* will be preceded with a letter always 
                
        #First col for all row is False but take care in initialization 
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if(p[j-1]!='*'):
                    if(p[j-1]==s[i-1] or p[j-1]=='.'):
                        dp[i][j]=dp[i-1][j-1] #Taken from diagonal
                else:
                    # we have "*" with 0 case and 1 case 
                        
                    # 0 case Not taking *
                    dp[i][j]=dp[i][j-2] # take from 2 step back
                        
                    #1 case match preceding character
                    if(p[j-2]==s[i-1] or p[j-2]=='.'):
                        if(dp[i-1][j]): #check above row same column 
                            dp[i][j]=True
        return dp[s1][p1]
        
        