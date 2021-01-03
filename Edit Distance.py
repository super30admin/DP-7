# Time:- O(len(word1)*len(word2))
# Space:- O(len(word1)*len(word2))
# Approach:- We have to find the minimum no. of transformations which will lead to string1
# being equal to string2. We start the from the first index of both the strings, if the
# charachter matches there are no transformations necessary we can just increment both 
# our pointers. If there is a mismatch we have 3 options delete(increment i),insert(increment
# j),replace(increment both the pointers) and add 1 to our result and recurse on the rest of the
# string. if somehow we finish processing both the strings the minimum transformations necessary
# are 0, if somehow we process one string but the second string is remaining the transformations
# necessary is the remaining string. 
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        @lru_cache(maxsize=None)
        def mcs(i,j):
            if i==len(word1) and j==len(word2):
                return 0
            if i==len(word1):
                return len(word2)-j
            if j==len(word2):
                return len(word1)-i
            if word1[i]==word2[j]:
                return mcs(i+1,j+1)
            delete=1+mcs(i+1,j)
            insert=1+mcs(i,j+1)
            replace=1+mcs(i+1,j+1)
            return min(insert,delete,replace)
        return mcs(0,0)
        