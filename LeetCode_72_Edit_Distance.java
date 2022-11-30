/*BF approcah - this question has multiple repeated subproblems and looks as tree.  
 * we can use DFS as it  helps us to find minimum operation perfomed to change string. 
 * in BFS - size varia;e of Queue i our answer, if we ever get the string == ros
 * tc - exponential
 * sc - O(h) : using Queue.
*/


//Optimized DP approach for the repeated sub problems
//1. if character matches we fetch the diagonal values
//else we take the minimum of the left, diagonal and top value and +1 to that. 
// we keep  the smaller word on cols, just for ease, but it doesnt matter, if we keep it on rows that should work as well.
//tc, sc- O(m*n)

class Solution {
    public int minDistance(String word1, String word2) {
        
        int m =  word1.length(), n = word2.length();
        
        if(m==0) return n;
        if(n ==0) return m;
        
        int[][] dp = new int[m+1][n+1];
        
        //fill the first row
        for(int j =0; j <n+1; j++)
        {
            dp[0][j] = j;
        }
        
        //fill the first col
        for(int i =0; i <m+1; i++)
        {
            dp[i][0] = i;
        }
    
        
        
        for(int i=1; i< m+1; i++)
        {
            for(int j =1; j < n+1; j++)
            {
                if(word1.charAt(i -1) == word2.charAt(j-1))                
                {
                    //diagonal value
                    dp[i][j] = dp[i-1][j-1];
                }else
                {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]) )+1;
                }
            }
        }
        return dp[m][n];
    }
}


//Optimized DP approach for the repeated sub problems
//1. if character matches we fetch the diagonal values
//else we take the minimum of the left, diagonal and top value and +1 to that. 
// we keep  the smaller word on cols, just for ease, but it doesnt matter, if we keep it on rows that should work as well.

// in earlier approach we used a matrix but we can also do with one row. 
//we will take two for loops to traverse word1 and word2 seprately ,but with 1D array, we can hold prev and temp to hold diagonal and self value respectively.
//tc, O(m*n), sc- O(n) // here taking smaller word on the col make sensse to save space 

class Solution {
    public int minDistance(String word1, String word2) {
        
        int m =  word1.length(), n = word2.length();
        
        if(m == 0) return n;
        if(n ==0) return m;
        
        if(m< n) return minDistance(word2, word1);
       
        int[] dp = new int[n+1];
        
        //fill the first row
        for(int j =0; j <n+1; j++)
        {
            dp [j] = j;
        }
        
            
        
        for(int i=1; i< m+1; i++)
        {
            int prev = dp[0];
            dp[0] =i;
            
            for(int j =1; j < n+1; j++)
            {
                int temp = dp[j]; //self value before change
                
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[j] = prev; //prev holds diagonal value
                else
                    dp[j] = Math.min(dp[j-1], Math.min(prev, dp[j]))+1;
                
                prev = temp;
                
            }
        }
        return dp[n-1];
    }
}