#time complexity:O(len(word1)*len(word2))
#space complexity: O(len(word2))
#ran on leetcode: Yes
#the three possoibilities in matching substring word1[:i] and word2[:j] depends on charcter at i and j in word1 and word2 and subproblem till i-1 and j-1 in substrings. So for updating charcter j to charcter i, we have 1 operation in addition to the subproblem solution word1[:i-1] and word2[:j-1]. Similarly we can look at left and top indexes for deletion and addition of a charcter to word2.
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        L=[]
        for i in range(len(word2)+1):
            L.append(i)
        for i in range(1,len(word1)+1):
            for j in range(len(word2)+1):
                if(j==0):
                    diagonal=L[j]
                    L[j]=i
                
                else:
                    if(word2[j-1]==word1[i-1]):
                        temp=diagonal
                        diagonal=L[j]
                        L[j]=temp
                    else:

                        temp=min(L[j-1],L[j],diagonal)+1
                        diagonal=L[j]
                        L[j]=temp
        return L[-1]
        


