'''
Time Complexity: 0(m*n)
Space Complexity: 0(m*n)
Run on LeetCode: Yes
'''
class Solution:
    
    def memorization2D(self,word1,word2):
        # initialize rows and cols
        rows = len(word2)+1
        cols = len(word1)+1
        
        # create a memory2D
        memory2D = [[None for col in range(0,cols)] for row in range(0,rows)]
        
        # fill-up row:0 and col:1 with values
        count = 0
        for r in range(0,rows):
            memory2D[r][0] = count
            count += 1
        
        count = 1
        for c in range(1,cols):
            memory2D[0][c] = count
            count += 1
        
        # iterate and fill-up the memory2D
        for r in range(1,rows):
            for c in range(1,cols):
                
                # case: word2[r] and word1[c] are matching
                # just chk diagonally above
                if word2[r-1] == word1[c-1]: 
                    memory2D[r][c] = memory2D[r-1][c-1]
                else:
                    # for update ---> diagonally above
                    # for add    ---> one step above
                    # for delete ---> one step behind
                    memory2D[r][c] = min(memory2D[r-1][c-1],memory2D[r-1][c],memory2D[r][c-1]) + 1 # here +1 signifies the operation done
            '''end of col iteration'''
        '''end of row iteration'''
        
        '''print the memory2D'''
        '''
        for r in range(0,rows):
            print(memory2D[r])
        '''
        return memory2D[-1][-1]
        
    def minDistance(self, word1: str, word2: str) -> int:
        return self.memorization2D(word1,word2)