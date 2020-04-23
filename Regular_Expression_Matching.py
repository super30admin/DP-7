// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
we start by including offset to both string and pattern for the base cases.
a)If both the characters are the same and the pattern at that index is '.',then the output will be the one which is coming  from the previous characters.
b)If the pattern is * we check if the previous character in the pattern is equal to the current character in the string or not.If yes we take the or between the case when previous pattern characters are considered and not considered.


# Time complexity --> o(mn) where m--> len(s) and n--> len(p)
# space complexity --> o(mn)
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        out=[[False for i in range(len(p)+1)]for j in range(len(s)+1)]
        out[0][0]=True
        #For the base cases.
        for j in range(1,len(p)+1):
            if p[j-1]=='*':
                out[0][j]=out[0][j-2]
        for i in range(1,len(s)+1):
            for j in range(1,len(p)+1):
            #when both charcters are the same then we take the diagonal output indicating the output of the previous strings
                if s[i-1]==p[j-1] or p[j-1]=='.':
                    out[i][j]=out[i-1][j-1]
              #If the pattern is * we have to check the previous pattern character and if it is equal to the current character then we take or of the cases when previous pattern character is considered and not considered.
                if p[j-1]=='*':
                    out[i][j]=out[i][j-2]
                    if p[j-2]==s[i-1] or p[j-2]=='.':
                        out[i][j]=(out[i][j] or out[i-1][j])
        # print(out)
        return(out[len(s)][len(p)])