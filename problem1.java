
// time - O(M*N)
// space - O(M*N)


class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        if(n == 0) return word2.length();

        int [][] dp = new int[m+1][n+1];

        for(int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j; // colum would be 0 1 2 3 4 5
        }
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = i; // row would be 0 1 2 3 4 5
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length;  j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)) { // if the char at both the strings are equal refer top diagonal element
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1; // if not equal refer min of left, diagonal & top element + 1
                }
            }
        }

        return dp[m][n];

    }
}