class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp=[i for i in range(len(word1)+1)]

        for i in range(len(word2)):
            diag=dp[0]
            dp[0]=i+1
            for j in range(1, len(dp)):
                temp=dp[j]
                
                if word2[i]==word1[j-1]:
                    dp[j]=diag
                else:
                    dp[j]=1+min(dp[j-1], dp[j], diag)
                
                diag=temp
        
        return dp[-1]
                

                
                
                

            
