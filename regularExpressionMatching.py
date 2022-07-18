'''
Time Complexity: 0(m*n)
Space Complexity: 0(m*n)
Run on LeetCode: Yes
'''
class Solution:
    
    def memorization2D(self,p,s):
        # s is my rows
        # p is my cols
        rows = len(s)+1
        cols = len(p)+1
        
        # create memory2D
        memory2D = [[None for c in range(0,cols)] for row in range(0,rows)]
        memory2D[0][0] = True
        
        # mark col 1 as False
        for row in range(1,rows):
            memory2D[row][0] = False
        
        #  fill first row
        for c in range(1, cols):
            if p[c-1] == '*':
                memory2D[0][c] = memory2D[0][c-2]
            else:
                memory2D[0][c] = False
        '''
        row 0 and col 0 will be "" always
        '''
        
        # iterate and fill-up the memory2D
        for r in range(1,rows):
            for c in range(1,cols):
                
                # for other cases
                # case 1
                if (p[c-1] == '.' or s[r-1] == p[c-1]):
                    memory2D[r][c] = memory2D[r-1][c-1]

                # case 2 and 3   
                elif (p[c-1] == '*'):
                    memory2D[r][c] = memory2D[r][c-2]
                    if (p[c-2] == '.' or s[r-1] == p[c-2]):
                        memory2D[r][c] = memory2D[r][c] or memory2D[r-1][c]

                # case 4
                else:
                    memory2D[r][c] = False
                    
            '''end of col iteration'''
        '''end of row iteration'''
        
        # print("Memory 2D is:\t",memory2D)
        
        return memory2D[-1][-1]
    
    def isMatch(self, s: str, p: str) -> bool:
        return self.memorization2D(p,s)