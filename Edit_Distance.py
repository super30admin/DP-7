// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach
i,j are pointers to the strings word1 and word2.
a) if character in both the strings are the same ,we decrement both the pointers i and j indicating same character.
b) If they are not the same.we can perform three operations to make wrd1 to word2.
        1)Insert --> for insert , i remain the same but j=j-1.
        2) Update --> Both i and j should decrement.
        3)delete --> i should decrement and j will remain the same.
    As we want the min number of operations to be done to m=change word1 to word2.we take min of the above three operations and add 1 to indicate above one of the operation is performed.


# Time complexity --> o(mn) m-->len(word1) and n--> len(word2)
# space complexity --> o(mn)
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        out=[[None for i in range(len(word2)+1)]for j in range(len(word1)+1)]
        for i in range(len(word1)+1):
            out[i][0]=i
        for j in range(len(word2)+1):
            out[0][j]=j
        for i in range(1,len(word1)+1):
            for j in range(1,len(word2)+1):
                if word1[i-1]==word2[j-1]:
                    out[i][j]=out[i-1][j-1]
                else:
                    out[i][j]=1+min(out[i][j-1],out[i-1][j-1],out[i-1][j])
        return out[len(word1)][len(word2)]