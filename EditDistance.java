// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class EditDistance {
    // Time : O(3 ^ m) m : length of larger string
    // Space: O(1)

    public static int minDistanceBF(String s, String p) {
        int m = s.length();
        int n = p.length();

        if(m == 0){
            return n;
        }

        if(n == 0){
            return m;
        }

        if(s.charAt(m-1) == p.charAt(n-1)){
            return minDistanceBF(s.substring(0, m-1), p.substring(0, n-1));
        }

        return 1 + findMin(minDistanceBF(s, p.substring(0, n-1)), //Insert
                minDistanceBF(s.substring(0, m-1), p), // Remove
                minDistanceBF(s.substring(0, m-1), p.substring(0, n-1))); //Replace
    }

    public int minDistanceDP(String s, String p) {
        int m = s.length();
        int n = p.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = 0 ; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0){
                    dp[i][j] = j;
                } else if(j == 0){
                    dp[i][j] = i;
                } else if(s.charAt(i-1) == p.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + findMin(
                            dp[i][j-1], // Insert
                            dp[i-1][j], // Remove
                            dp[i-1][j-1] // Replace
                    );
                }
            }
        }

        return dp[m][n];
    }

    private static int findMin(int x, int y, int z){
        if(x <= y && x <= z){
            return x;
        } else if (y <= x && y <= z){
            return y;
        } else {
            return z;
        }
    }
    public static void main(String args[]){
        System.out.println(minDistanceBF("abc",
                "xyz"));
    }

    // Taken in class, easiest
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        // Inititalizing "" wrt word2
        for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }

        // Inititalizing "" wrt word1
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }

        // two conditions, if two chars are same, copy from last pair of words, i.e. diagonals, else take min of prev three i.e. up, corner, prev
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
