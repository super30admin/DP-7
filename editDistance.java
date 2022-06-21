/*
Problem: https://leetcode.com/problems/edit-distance/
O(n1 * n2)
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0 && n2 == 0) return 0;
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
        
        int dp[][] = new int[n1 + 1][n2 + 1];
        
        // if word1 == "", moves to convert to word2 is the number of letters in word2
        for (int i = 0; i <= n2; ++i) {
            dp[0][i] = i;
        }
        
        // if word2 == "", moves to convert to word1 to "" is the number of letters in word1
        for (int i = 0; i <= n1; ++i) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    /**
                      _ r o s
                    _ 0 1 2 3
                    h 1 ?
                    o 2
                    r 3
                    s 4
                    e 5

                    _h = _r ?

                    Case 1: delete => _ = _r ? => matrix[_][_r] = 1
                    Case 2: Add => _hr = _r ? delete r => _h = _ ? => matrix[_h][_]
                    Case 3: Update => _h = _r ? matrix[_][_] + 1 to convert h to r

                    dp[i-1][j] -> delete
                    dp[i-1][j-1] -> update
                    dp[i][j-1] -> add
                    **/
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        
        return dp[n1][n2];
    }
}

// Can optimize on space by using a single array
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0 && n2 == 0) return 0;
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
        
        if (n1 > n2)
            return minDistance(word2, word1);
        
        int dp[] = new int[n1 + 1];
        
        for (int i = 0; i <= n1; ++i) {
            dp[i] = i;
        }
        
        
        for (int i = 1; i <= n2; ++i) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n1; ++j) {
                int temp = dp[j];
                if (word2.charAt(i-1) == word1.charAt(j-1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j-1], dp[j]));
                }
                prev = temp;
            }
        }
        
        return dp[n1];
    }
}