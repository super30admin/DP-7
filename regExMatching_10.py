# Time Complexity : O(nm)
# Space Complexity : O(nm)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Approach/Intuition: 
# Whenever we encounter * (eg. a*)-> either choose a*->a* or don't choose a*-> empty string. If you choose, next you'd either choose a(a*)-> aa* or don't choose next a(a*)-> a
# Eg. (c*a*b -> aab): there is a repeating sub problem, we can create a DP matrix and start filling boolean values that represent if x to y transformation is possible or not.
# Observations while we choose/don't choose going forward in the DP matrix:
# 1. If chars are not equal, put False
# 2. If there is a *, check if prev char == curr char -> put True If don't choose: update value of two steps back.
# 3. If char == curr char, check diagonal and update
# 4. case: _c*a -> _a ? DC: False, Ch: _c*(a*)a-> _a Problem: How would we know if _c*(a*)-> _ ? Answer is in the cell right above it.

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n =len(s)+1
        m = len(p)+1
        
        dp = [[False for _ in range(m)]for _ in range(n)]
        
        dp[0][0]= True
        
        for j in range(1, m):
            if p[j-1]=='*':
                dp[0][j] = dp[0][j-2]
                
        for i in range(1, n):
            for j in range(1, m):
                if p[j-1] == s[i-1] or p[j-1] =='.':
                    dp[i][j]= dp[i-1][j-1]
                elif p[j-1]=='*':
                    dp[i][j] = dp[i][j-2]
                    
                    #checks: if prev to * == curr char or prev char is '.' then put True 
                    if p[j-2] == s[i-1] or p[j-2]=='.':
                        if dp[i][j-2] or dp[i-1][j]:
                            dp[i][j] = True
                            
        return dp[-1][-1]
        