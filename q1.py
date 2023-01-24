# Time Complexity : O(m*n), Where m,n is number of elements in string word1,word2 respectively
# Space Complexity : O(m*n), Where m,n is number of elements in string word1,word2 respectively
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Nothing specific

class Solution:
    def solution(self,i,j,word1,word2,memo):
        #Base Case
        #When we reach any of the end, Then just return length of the remaining word
        if(i==len(word1)):
            return (len(word2)-j)
        if(j==len(word2)):
            return (len(word1)-i)
        
        if(memo[i][j]!=None):
            return memo[i][j]
        
        #Actual logic
        if(word1[i]==word2[j]):
            #Here when both the letters are same
            ans=self.solution(i+1,j+1,word1,word2,memo)
        else:
            #insert(just go the next index in word2)
            ans1=1+self.solution(i,j+1,word1,word2,memo)
            #update(here we should go for both words)
            ans2=1+self.solution(i+1,j+1,word1,word2,memo)
            #delete(just go to the index in word1)
            ans3=1+self.solution(i+1,j,word1,word2,memo)
            ans=min(ans1,ans2,ans3)
        memo[i][j]=ans
        return ans
    def minDistance(self, word1: str, word2: str) -> int:
        dp=[[None for j in range(len(word2))] for i in range(len(word1))]
        return self.solution(0,0,word1,word2,dp)