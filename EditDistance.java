public class EditDistance {
    //Time complexity: O(M*N)
    //Space complexity: O(M*N)
    public int minDistanceDP(String word1, String word2) {
        if(word1.length() == 0 && word2.length() != 0){
            return word2.length();
        }
        if(word1.length() != 0 && word2.length() == 0){
            return word1.length();
        }
        if(word1.length() == 0 && word2.length() == 0){
            return 0;
        }

        int[][] dp = new int[word1.length() +1][word2.length()+1];

        for(int i = 1; i<= word1.length(); i++){
            dp[i][0] = dp[i-1][0] + 1;
        }
        for(int i = 1; i<= word2.length(); i++){
            dp[0][i] = dp[0][i-1] + 1;
        }

        for(int i = 1; i<= word1.length(); i++){
            for(int j= 1; j <= word2.length(); j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    //Time complexity: 3^N as in each node we are exploring 3 options
    //Space complexity max of length of word 1 and word2
    public int minDistanceRecursion(String word1, String word2) {
        if(word1.length() == 0 && word2.length() != 0){
            return word2.length();
        }
        if(word1.length() != 0 && word2.length() == 0){
            return word1.length();
        }
        if(word1.length() == 0 && word2.length() == 0){
            return 0;
        }

        return helper(word1, word2, word1.length()-1, word2.length()-1);

    }

    private int helper(String word1, String word2, int idx1, int idx2){

        if(idx1 < 0){
            return idx2 +1;
        }
        if(idx2 < 0){
            return idx1 +1;
        }

        if(word1.charAt(idx1) == word2.charAt(idx2)) return helper(word1, word2, idx1-1, idx2-1);

        int insert = helper(word1, word2, idx1, idx2 - 1);
        int delete = helper(word1, word2, idx1-1, idx2);
        int replace = helper(word1, word2, idx1-1, idx2-1);

        return Math.min(insert, Math.min(delete, replace))+1;
    }
}
