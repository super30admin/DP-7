public class EditDistance {
    //Time complexity - o(mXn)
    //Space complexity - o(mXn)
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        int m = word2.length();
        int n = word1.length();
        int[][] dp = new int[m + 1][n + 1];
        // fill all the rows of first coloumn
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        // fill all the coloumns of first row
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }
        // i and j starts from 1 because we filled first row and first col.
        //we are using i-1, j-1 bcos cords index start from zero but dp index start from 1.
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // if characters match we are not perfoming any
                //operation and directly taking from left diagonal
                if(word2.charAt(i-1)==word1.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    //if characters are not matching we are taking min of left and top
                    //amd adding 1 to it as current operation.
                    dp[i][j]= 1+ Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
                }
            }
        }
        //return the result as it is on the last row last col
        return dp[m][n];

    }
}
