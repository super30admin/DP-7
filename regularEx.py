class Solution:
    
    """
    Description: from pattern (p) and string (s) where p can support characters like "." and "*" where "." match any character and "*" maches 0+ preceding elements
    
    Time Complexity: O(m*n)
    Space Complexity: O(m*n)
    where, m is length of s and n is length of p
    
    Approach: use dp matrix
    - create a dp matrix with size len(p) + 1 and len(s) + 1 (all False values)
    - with starting string as "" for both p and s, starting with first element as True (empty match with empty)
    - in top row, to 2 steps back for every incoming * (star cannot be the first element, no conditional statement other than match with "*")
    - left column will be False by default except the top element
    - 3 conditions:
      1. if character is not star (i.e. "." or a character), 
      2. if character matches or "." in p, return the diagonally opposite preceding element
      3. otherwise, go 2 steps back if preceding character does not match and element above current -> return True if either of them is True
    - return last element of the dp matrix
    """
    
    def isMatch(self, s: str, p: str) -> bool:
        
        # create a dp matrix
        m = len(s); n = len(p)
        dp = [[False for _ in range(n + 1)] for _ in range(m + 1)]
        
        # First item in dp
        dp[0][0] = True

        # Top row
        for j in range(1, n + 1):
            if p[j - 1] == "*": dp[0][j] = dp[0][j - 2]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                
                if p[j - 1] != "*":
                    
                    # either a character or "."
                    if s[i - 1] == p[j - 1] or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                        
                else:
                    
                    # case_0
                    dp[i][j] = dp[i][j - 2]
                    
                    # case_1
                    if p[j - 2] == s[i - 1] or p[j - 2] == ".":
                        dp[i][j] = self.getans(dp[i][j - 2], dp[i - 1][j])
        
        return dp[m][n]
        
    def getans(self, case_0, case_1):
        if case_0 or case_1: return True
        return False
