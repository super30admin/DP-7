class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """DP Implementation
        Time complexity-O(m*n) where m is the length of s and n is the length of p
        Space complexity-O(m*n)"""
        if s==p:
            return True
        dparr=[[False for _ in range(len(p)+1)] for _ in range(len(s)+1)]
        dparr[0][0]=True
        for j in range(1,len(p)+1):
            if p[j-1]=="*":
                dparr[0][j]=dparr[0][j-2]
        for i in range(1, len(s)+1):
            for j in range(1, len(p)+1):
                if s[i-1]==p[j-1] or p[j-1]==".":
                    dparr[i][j]=dparr[i-1][j-1]
                elif p[j-1]=="*":
                    dparr[i][j]=dparr[i][j-2]
                    if p[j-2]==s[i-1] or p[j-2]==".":
                        dparr[i][j]=dparr[i][j] or dparr[i-1][j]
        return dparr[len(s)][len(p)]