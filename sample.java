// Edit Distance
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

class Solution {
    public int minDistance(String word1, String word2) {
               
        int m = word1.length() + 1; int n = word2.length() + 1;
        
        // initializing dp array
        int[][] dp = new int[n][m];
        
        // populating 1st row
        for (int j=0; j<m; j++) {
            dp[0][j] = j;
        }
        
        // population 1st col
        for (int i=0; i<n; i++) {
            dp[i][0] = i;
        }
        
        // populating remaining elements
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (word1.charAt(j - 1) == word2.charAt(i - 1)) { // if char same, then get the diagonal
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // if not same, then get min and add 1
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        
        // return the last element
        return dp[n - 1][m - 1];
    }
}

// Regular Expression Matching
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

class Solution {
    public boolean isMatch(String s, String p) {
        // base condition
        if(p == null) {
            return s == null;
        }

        // initialiaze dp array with boolean
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for(int i = 1; i < dp[0].length; i++) { // for 1st row
            if(p.charAt(i - 1) == '*') { // when * encountered
                dp[0][i] = dp[0][i-2];
            }
        }

        // performing dp
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                // if pattern and string char matches or . encountered
                if(p.charAt(j - 1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j - 1)  == '*') { // if *
                    dp[i][j] = dp[i][j-2];
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) {
                        if( dp[i][j-2] || dp[i-1][j]) {
                             dp[i][j] = true;
                        }
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
