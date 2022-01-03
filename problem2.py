#Time space O(n*m)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        st=len(s)
        pt=len(p)
        dp = [[False for x in range(pt+1)] for y in range(st+1)]
        dp[0][0]=True
        for j in range(1,pt+1):
   #First row         
            if p[j-1]=='*':
                dp[0][j]=dp[0][j-2]
    
        
        for  i in range(1,st+1):
            for j in range(1,pt+1):
                c=s[i-1]
                c1=p[j-1]
                #If they are equal then diagonal
                if c== c1 or c1 ==".":
                    dp[i][j]= dp[i-1][j-1]
            #If * then  taken and nottaken      
                elif c1 =="*":
                    nottaken = dp[i][j-2]
                    taken = False
                 #check the previous if they are equal  and above row
                    if (p[j-2]==c or p[j-2]=="." ) and dp[i-1][j]:
                        taken = True
                    dp[i][j]=nottaken or taken
                
                
        return dp[st][pt]
                            
            
