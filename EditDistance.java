/**
 * Intuition: Each character in the word1 can be added, deleted or updated and after that the resultant string again has the same options.
 *             using dp, each char is again given 3 options, one from the top, other from the left and the last from beside(left). Using these a decision has to be made based on whether the character is matching or not at that position
 *
 *             Ex: word1 : horse word2: ros  task is to convert word1 to word2 and return min actions required to do so.
 *             - h o r s e
 *           - 0 1 2 3 4 5  // here from col - want to change it to row - : require 0 moves, from col -h to - require 1 and -ho to - require 2 and so on
 *           r 1 1 2 2 3 4  // here from col - want to change it to row -r : require 1 move, from col -h to row -r require 1, from -ho to -r require 2 moves n so on
 *           o 2 2 1 2 3 4
 *           s 3 3 2 2 2 3
 *  Time: O(m*n) m and n - are the lengths of word1 and word2 respectively
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp =new int[n+1][m+1];

        for(int i=0;i<=m;i++)
            dp[0][i] = i;

        for(int i=0;i<=n;i++)
            dp[i][0] = i;

        System.out.println();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(word2.charAt(i-1) != word1.charAt(j-1))
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                else
                    dp[i][j] = dp[i-1][j-1];
            }
        }

        return dp[n][m];
    }
}