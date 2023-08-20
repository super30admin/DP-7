#Time complexity is: O(mn)
#Space complexity is O(mn)
#No issues faced while coding
#Code ran successfully on leetcode
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        #Base condition
        if s == p:
            return True
        #Initializing all the required variables
        m = len(s)
        n = len(p)
        dp = [[False for _ in range(n+1)] for _ in range(m+1)]
        #Intializing the value to True in top left corner
        dp[0][0] = True
        #Moving over first row in the matrix
        for j in range(1, n+1):
            c = p[j-1]
            #If the current character is *, we will replace that 
            #with the value two stpes back
            if c == "*":
                dp[0][j] = dp[0][j-2]
        #Iterating through the matrix
        for i in range(1, m+1):
            for j in range(1, n+1):
                #Taking the required characters in the s and p string
                sChar = s[i-1]
                pChar = p[j-1]
                #If the incoming characters in the string and pattern matches
                #or character in the pattern is equal to ".", we will replace that
                #To the diagonal top left corner
                if sChar == pChar or pChar == ".":
                    dp[i][j] = dp[i-1][j-1]
                #If the character is equal to *
                elif pChar == "*":
                    #Zero case
                    dp[i][j] = dp[i][j-2]
                    #Once case, where character in s is equal to p[j-2]
                    #or p[j-2]=="."
                    if sChar == p[j-2] or p[j-2] == ".":
                        #Then we will take the or value at the position dp[i][j]
                        dp[i][j] = dp[i][j] or dp[i-1][j]
        #Finally we are returning dp[m][n] value
        return dp[m][n]
