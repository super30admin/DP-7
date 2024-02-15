#TC: O(m+1*n+1)
#SC: O(m+1*n+1)
#This is same as the regex problem. Here the return type is integer so, we fill the table with 
#numbers unlike true or false previously. In insertion, we will insert the character to be matched. 
#In edit, replace h with r, -r cancels -r and becomes - and -1. Which is the diagonal left element
#In insert, insert h with r, so -hr and -r, cancel r and the result is at the left element
#In delete, delete h for r , so -  and -r, which is the top element
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m=len(word1)
        n=len(word2)
        dp=[[0]*(n+1) for _ in range(m+1)]
        for j in range(1,n+1):
            dp[0][j]=j
        for i in range(1,m+1):
            dp[i][0]=i

        for i in range(1, m+1):
            for j in range(1, n+1):
                #edit: diagonal left i-1,j-1
                #delete: top
                #insert: left
                if word1[i-1]!=word2[j-1]:
                    dp[i][j]=min(dp[i-1][j-1], dp[i-1][j],dp[i][j-1])+1
                else:
                    dp[i][j]=(dp[i-1][j-1])
        
        return dp[-1][-1]


