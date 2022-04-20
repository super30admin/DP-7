class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        '''
        T = O(len(s)*len(p))
        S = O(len(s)*len(p))
        '''
        # fill the entire matrix with False
        dp = [[False]*(len(p)+1) for _ in range(len(s)+1)]
        
        # first cell is True
        dp[0][0] = True
        
        # first row 
        for i in range(1,len(p)+1):
            if p[i-1] == '*':
                dp[0][i] = dp[0][i-2]
                
        for i in range(1, len(dp)):
            dp[i][0] = False
            for j in range(1, len(dp[0])):
                if p[j-1] != '*': 
                    # if the char matches then fill the cell with diagonal value
                    if s[i-1] == p[j-1] or p[j-1] == '.':  
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = False
                else: # if the present char is star, 
                    #zero case
                    dp[i][j] = dp[i][j-2]  # see if the prev element match 
                    #one case
                    if not dp[i][j]:   
                        if (s[i-1] == p[j-2]) or (p[j-2] == '.'):
                            dp[i][j] = dp[i-1][j]     
      
        return dp[-1][-1]
    
    
    
    
