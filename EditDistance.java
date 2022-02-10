// Time Complexity: O(n*m) where n is length of word2 and m is length of word1
// Space Complexity: O(1)
// DP variables
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        
        int m = word1.length(), n = word2.length();
        if(m > n)
            return minDistance(word2, word1);
        // let word1 is smaller always
        int dp[] = new int[m + 1];
        
        // first row _ == word
        for(int j = 0 ; j < m+1 ; j++)
        {
            dp[j] = j;
        }
        

        for(int i = 1 ; i < n + 1 ; i ++)
        {
            int diag = dp[0]; // diag of current row first col
            dp[0] = i; // initialize first column for all rows
            for(int j = 1 ; j < m + 1 ; j++) // word1
            {
                int top = dp[j];
                if(word1.charAt(j-1) == word2.charAt(i-1))
                {
                    // copy over the diagonal, check if previous match
                    dp[j] = diag;
                }
                else
                {
                    // current + min(update, add, delete)
                    dp[j] = 1 + Math.min(diag, 
                                            Math.min(dp[j-1], top));
                }
                // update diag for next column
                diag = top;
            }
        }
        
        return dp[m];
    }
}

// Time Complexity: O(n*m) where n is length of word2 and m is length of word1
// Space Complexity: O(n*m) where n is length of word2 and m is length of word1
// DP Pattern 1 i.e 1D matrix
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        
        int m = word1.length(), n = word2.length();
        int dp[][] = new int[m + 1][n + 1];
        
        // first row _ == word1
        for(int j = 0 ; j < n+1 ; j++)
        {
            dp[0][j] = j;
        }
        // first column word2 == _
        for(int i = 0 ; i < m + 1 ; i++)
        {
            dp[i][0] = i;
        }
        
        for(int i = 1 ; i < m + 1 ; i ++)
        {
            for(int j = 1 ; j < n + 1 ; j++)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                {
                    // copy over the diagonal, check if previous match
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    // current + min(add, update, delete)
                    dp[i][j] = 1 + Math.min(dp[i][j-1], 
                                            Math.min(
                                                dp[i-1][j-1], dp[i-1][j]));
                }
            }
        }
        
        return dp[m][n];
    }
}
