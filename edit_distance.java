class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0; //if word1 and word 2 are equal, return 0
        int m = word1.length(); // m stores the length of word1
        int n = word2.length(); // n stores the length of word2
        int[][] dp = new int[m + 1][n + 1]; //dp is 2d array initialized to m + 1 amd n + 1
        for(int j = 0; j < n + 1; j++) { //we compute the values in the first row
            dp[0][j] = j;
        }
        for(int i = 0; i < m + 1; i++) { //we compute the values in the first col
            dp[i][0] = i;
        }
        for(int i = 1; i < m + 1; i++) { //we go through all the elements in the col
            for(int j = 1; j < n + 1; j++) { //we go through all the elements in the row
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) { //if char at i - 1 is equal to j - 1
                    dp[i][j] = dp[i - 1][j - 1]; //we bring the same value to i and j in the dp array
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])); //else we get min of all the 3 surrounding elements
                }
            }
        }
        return dp[m][n] //in the end, we return the last index of the 2d array.
    }
}
//tc and sc - O(m * n)