# // Time Complexity :O(m*n), m and n are word lengths
# // Space Complexity :O(n)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


# // Your code here along with comments explaining your approach
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if len(word1)>len(word2):#word1 smaller
            word1,word2=word2,word1
        dp=[i for i in range(len(word1)+1)]
        print(dp)
        print(word1)
        print(word2)
        for i in range(1,len(word2)+1):
            temp=dp[0]
            print(temp)
            for j in range(len(word1)+1):
                temp2=dp[j]
                if j==0:
                    dp[j]=i
                else:
                    if word1[j-1]==word2[i-1]:
                        print(word1[j-1])
                        dp[j]=temp
                    else:
                        dp[j]=1+min(dp[j-1],dp[j],temp)
                temp=temp2
            print(dp)
        print(len(word2),len(word1))
        return dp[-1]
                
        
            
        