# Time Complexity :
# O (NM)

# Space Complexity :
# O(NM)

# Did this code successfully run on Leetcode :
#Yes

#We store a DP array that stores if the strings match or not. The row goes through the actual string and columns go through the search string
#when processing the first ro, the value at any point is False except when a '*' is encountered - then the value is the value stored 2 steps back
#From then on, while processing any row, 
#If the current element is '*', and the last character of search string and current string are same or last character in search string is '.', then current value is either what was seen 2 steps back(if that character does not appear at all) or one level above (if the character appears one or more times) 
#Else, if both the characters are same or the search string contains a '.', then it is a match and so current value is same as one character less in both strings

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p :
            return True
        if len(s) == 0 or len(p) == 0 :
            return False
        dp = [[False] * (len(p)+1) for i in range(len(s)+1)]
        dp[0][0] = True

        for i in range(0,len(dp)):
            for j in range(1,len(dp[0])):
                if i == 0 :
                    if p[j-1] == '*':
                        dp[0][j] = dp[0][j-2]
                else :
                    if p[j-1] == '*' :
                        dp[i][j] = dp[i][j-2]
                        if p[j-2] == s[i-1] or p[j-2] == '.' :
                            dp[i][j] = dp[i][j] or dp[i-1][j]
                    else :
                        if p[j-1] == s[i-1] or p[j-1] == '.':
                            dp[i][j] = dp[i-1][j-1]

        return dp[-1][-1]
