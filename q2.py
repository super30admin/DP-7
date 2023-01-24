# Time Complexity : O(m*n), Where m,n is number of elements in string s,p respectively
# Space Complexity : O(m*n), Where m,n is number of elements in string s,p respectively
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Nothing specific

class Solution:
    def solution(self,i,j,s,p,memo):
        #Here j is used to keep track of "p"
        # i is used to keep track of solution created
        
        #Base Case
        if(j==len(p)):
            return i==len(s)
        
        if(memo[i][j]!=None):
            return memo[i][j]
        
        #Actual Logic
        c=p[j]
        n=len(p)#n is used for length of the pattern p
        m=len(s)#m is used for the length of string s
        if(j<n-1 and p[j+1]=="*"):
            #We have check the two possibliltes, include and doesnot include
            memo[i][j]=self.solution(i,j+2,s,p,memo) or (i<m and self.match(s[i],c) and self.solution(i+1,j,s,p,memo))
            return memo[i][j]
        else:
            #We have to check only the present element and go to next indices 
            memo[i][j]=i<m and self.match(s[i],c) and self.solution(i+1,j+1,s,p,memo)
            return memo[i][j]
                                
    def match(self,a,b):
        if(b=="."):
            return True
        else:
            return a==b
                
                
    def isMatch(self, s: str, p: str) -> bool:
        m,n=len(s),len(p)
        dp=[[ None for y in range(n)] for z in range(m+1)]
        ans= self.solution(0,0,s,p,dp)
        #print(dp)
        return ans
        