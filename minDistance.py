class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        '''
        T = O(m*n)
        S = O(m*n)
        '''
        if word1 == word2 : return 0 
        n = len(word1)
        m = len(word2)
        
        # if one of the strings is empty
        if n * m == 0:
            return n + m
        
        # array to store the convertion history
        d = [ [0] * (m + 1) for _ in range(n + 1)]
        
        # init boundaries
        for i in range(n + 1):
            d[i][0] = i
        for j in range(m + 1):
            d[0][j] = j
        
        # DP compute 
        for i in range(1, n + 1):
            for j in range(1, m + 1):
                if word1[i - 1] == word2[j - 1]:
                    d[i][j] = d[i-1][j-1]
                else:
                    d[i][j] = 1 + min( d[i-1][j-1] , d[i-1][j] , d[i][j-1] )
        
        return d[n][m]
