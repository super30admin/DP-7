class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False]*(len(p)+1) for _ in range(len(s)+1)]

        dp[0][0] = True
        #First row is filled here
        #No boundaery check cause given in the question
        for j in range(1,len(p)+1):
            if p[j-1] == "*":
                #if it is a star you check if you can ignore the letter befroe else false
                
                dp[0][j] = dp[0][j-2]
                
        for i in range(1, len(s)+1):
            for j in range(1,len(p)+1):
                
                if p[j-1] == "*":
                    #Two cases
                    #Case-1
                    dp[i][j] = dp[i][j-2]
                    #case 2
                    if p[j-2] == s[i-1] or p[j-2]== ".":
                        if dp[i-1][j]:
                            dp[i][j] = True
                else:
                    #the elements either mtach or the pattern needs to be a dot which can map to anything
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]

        return dp[-1][-1
Time : O(MN)
Space: O(MN)
                
            
    
