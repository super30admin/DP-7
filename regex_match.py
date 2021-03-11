# Approach- In line
# Time - O( M * N)
# Space  - O(M * N)


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        #    _ c * a * b  --> Pattern

        # _  True

        # a

        # a

        # b

        dp = [[False for _ in range(len(p) + 1)] for _ in range(len(s) + 1)]
        
        dp[0][0] = True # since _ matches _
        
        # fill first row, if we encounter * take from 2 cells back on same row
        for col in range(1, len(p) + 1):
            if p[col-1] == "*":
                dp[0][col] = dp[0][col-2]
                
        # Iterate on remaining dp array       
        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):
                
                # if character 
                if p[j-1] != '*':
                    
                    # if characters match(string & pattern) or '.' simply skip, take val from diagnol
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                        
                        
                else:
                    # charac is '*' - need to check zero and 1 case
                    # zero case
                    
                    dp[i][j] = dp[i][j-2]
               
                    # if preceeding character(j-2, since j - 1 is *) to * matches character at s or '.'
                    
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        # choose between zero case and above but just check if above value is True, use that
                        
                        if dp[i-1][j]:
                            dp[i][j] = True
                            
                            
                            
                            
        return dp[-1][-1]
                        
        