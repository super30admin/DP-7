#time complexity: O(len(s)*len(p))
#space complexity: )(len(s)*len(p))
#ran on leetcode: yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp=[]
        for i in range(len(s)+1):
            temp=[]
            for j in range(len(p)+1):
                temp.append(False)
            dp.append(temp)
        dp[0][0]=True

        for i in range(0,len(dp)):
            for j in range(1,len(dp[0])):
                char_match=False
                if(s[i-1]==p[j-1] or p[j-1]=='.'):
                    char_match=True
                
                if(p[j-1]=='*'):
                    if(  j-2>=0 and i-1>=0 and(p[j-2]==s[i-1] or p[j-2]=='.')):
                        dp[i][j]=dp[i-1][j] or dp[i][j-2]
                    else:
                        dp[i][j]=dp[i][j-2]
                else:
                    dp[i][j]=char_match and dp[i-1][j-1]
        #print(dp)
        return dp[-1][-1]





