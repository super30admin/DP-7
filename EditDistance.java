public class EditDistance {
    //TC-O(M*N) SC-O(M*N)
    public int minDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();

        //if amy string is 0 length then return the other string length as that many
        //operations are needed to make them equal.
        if (word1Length == 0) {
            return word2Length;
        }
        if (word2Length == 0) {
            return word1Length;
        }

        //start memozation - as the first row and col always increase in
        // operations as the string pointer is moving forward 1 length extra for accomidating empty string senario.
        int dp[][] = new int[word1Length + 1][word2Length + 1];

        //first cols of matrix filled
        for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
            dp[word1Index][0] = word1Index;
        }
        //first row of the matrix filled
        for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
            dp[0][word2Index] = word2Index;
        }


        //fill in the table
        for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
            for (int word2Index = 1; word2Index <= word2Length; word2Index++) {

                //if the chars are equal then the cell value is equal to the top diagnol cell
                if ( word2.charAt(word2Index - 1) == word1.charAt(word1Index - 1)) {
                    dp[word1Index][word2Index] = dp[word1Index - 1][word2Index -1];
                } else {
                    //if the chars at the cell are not equal then number of
                    //operation will be equal to the min of the top diaglonal left and top of the cell
                    dp[word1Index][word2Index] = Math.min(
                            dp[word1Index - 1][word2Index],
                            Math.min(
                                    dp[word1Index][word2Index - 1],
                                    dp[word1Index - 1][word2Index - 1]
                            )
                    ) +1;
                }


            }
        }

        return dp[word1Length][word2Length];
    }

}
