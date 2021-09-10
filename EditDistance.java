//T.C:O(m*n) m is length of word1, n is length of word 2;
//S.C: O(m*n);
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        //first row
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        //first col
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        //fill the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //not equal
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1])+1;
                }
                //equal
                else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}

//T.C:O(m*n) m is length of word1, n is length of word 2;
//S.C: O(n);
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;

        int m = word2.length();
        int n = word1.length();
        if (m > n)
            return minDistance(word2, word1);


        int[] dp = new int[n + 1];

        for (int j = 1; j <= n; j++)
            dp[j] = j;

        //fill the array
        int temp1,temp2;
        for (int i = 1; i <= m; i++) {
            temp1 = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                //not equal
                temp2 = dp[j];
                if (word1.charAt(j - 1)!= word2.charAt(i - 1)) {
                    dp[j] = Math.min(Math.min(temp1, dp[j - 1]), dp[j])+1;
                }
                else {
                    dp[j] = temp1;
                }
                temp1 = temp2;
            }
        }

        return dp[n];
    }
}