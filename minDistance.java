// Time Complexity : O(m*n)
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO

// Your code here along with comments explaining your approach
class Solution {
  public int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    if (n * m == 0)
      return n + m;

    int [][] d = new int[n + 1][m + 1];

    // init boundaries
    for (int i = 0; i < n + 1; i++) {
      d[i][0] = i;
    }
    for (int j = 0; j < m + 1; j++) {
      d[0][j] = j;
    }

    // DP  
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        int left = d[i - 1][j] + 1;
        int up = d[i][j - 1] + 1;
        int diagonal = d[i - 1][j - 1];
        if (word1.charAt(i - 1) != word2.charAt(j - 1))
          diagonal += 1;
        d[i][j] = Math.min(left, Math.min(up, diagonal));

      }
    }
    return d[n][m];
  }
}
