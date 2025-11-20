# Time Complexity : O(N*M) as we are iterating through both the strings
# Space Complexity : O(N+M) for the recursion stack and memoization
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using a recursive approach with memoization to check if the string s matches the pattern p.
# I define a helper function that takes two indices, i and j, representing the current positions in the string s and pattern p respectively.
# The base case checks if both indices have reached the end of their respective strings, in which case we return True.
# If the pattern index j has reached the end but the string index i has not, we return False.
# I use a memo dictionary to store the results of previously computed states (i, j)  to avoid redundant calculations.
# If the next character in the pattern is '*', I have two cases to consider:
# 1. If the current characters in s and p match (or if p[j] is '.'), I can either:
#    - Treat '*' as matching one or more occurrences of the preceding element (move i forward).
#    - Treat '*' as matching zero occurrences (move j forward by 2).
# 2. If the characters do not match, I can only treat '*' as matching zero occurrences (move j forward by 2).
# If the next character in the pattern is not '*', I check if the current characters match (or if p[j] is '.').
# If they do, I move both indices forward by 1. If they don't match, I return False.
# Finally, I return the result of the helper function starting from indices 0, 0.


class Solution:
    def isMatch(self, s: str, p: str) -> bool:        
        m = len(p)
        n = len(s)
        memo = {}
        def helper(i,j):
            if i == n and j == m:
                return True
            if j == m:
                return False
            if (i,j) in memo:
                return  memo[(i, j)]
            if j + 1 < m and p[j+1] == "*":
                if i < n and (s[i] == p[j] or p[j] == "."):
                    memo[(i, j)] = helper(i+1,j) or helper(i,j+2) 
                else:
                    memo[(i, j)] = helper(i,j+2)  
            elif i < n and( s[i] == p[j] or p[j] == "."):
                memo[(i, j)] = helper(i+1,j+1)
            else:
                memo[(i, j)] = False
                
            return  memo[(i, j)]
        return helper(0,0)