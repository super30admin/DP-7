// TIME AND SPCAE complexity : O(mn)

class Solution {
  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];

    for (int[] temp : dp)
      Arrays.fill(temp, -1);

    return cal(word1, word2, word1.length() - 1, word2.length() - 1, dp);
  }

  public int cal(String s1, String s2, int a, int b, int[][] dp) {

    if (a < 0 && b < 0)
      return 0;

    if (a < 0 && b >= 0) {
      return b + 1;
    }

    if (b < 0 && a >= 0) {
      return a + 1;
    }

    if (dp[a][b] != -1)
      return dp[a][b];

    if (s1.charAt(a) == s2.charAt(b)) {
      return dp[a][b] = cal(s1, s2, a - 1, b - 1, dp);
    }

    int c = cal(s1, s2, a - 1, b, dp); // delete

    int d = cal(s1, s2, a - 1, b - 1, dp);// repalce

    int e = cal(s1, s2, a, b - 1, dp); // insert

    return dp[a][b] = 1 + Math.min(c, Math.min(d, e));

  }
}