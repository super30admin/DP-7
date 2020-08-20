// Time Complexity - O(mn)
// Space Complexity - O(mn)
// Approach
// Maintain a dp matrix and use bottom up apprach to solve the problem.
//If we come across new char for any of the strings, check if the addtl chars for both the strings
// are same, then you can use prev diagonal result as it creates a same scenario if the
// strings are without that char else take min of top, left and diagonal and add 1 to it which is addition of
// new char. Min because we want to perform min no of edits

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null) return 0;
        else if(word1==null) return word2.length();
        else if(word2==null) return word1.length();
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++) dp[i][0]=i;
        for(int i=0;i<dp[0].length;i++) dp[0][i]=i;
        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[i].length;j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1+Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}