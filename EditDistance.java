/*
Time Complexity: O(N*M), N is the length of word1 and M is the length of word2
Space Complexity: O(N*M), size of DP array
Run on leetcode: yes
any difficulties: no

Approach:
1. Dynamic Programming, maintaining DP state
2. **Well commented code**
 */
public class EditDistance {
    public static int editDistance(String word1, String word2){
        if(word1 == null || word2 == null){
            return 0;
        }

        int[][] dp = new int[word1.length()+1][word2.length()+1];

        int n = word1.length();
        int m = word2.length();

        // Initialize DP state for the empty string
        // First row
        for(int i = 0; i<=n; i++){
            dp[i][0] = i;
        }

        // First column
        for(int j = 0; j<=m; j++){
            dp[0][j] = j;
        }


        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                // Iterate over the given strings and if the character matches then take the state from the diagonal element
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    /*
                    Otherwise, the minimum state from the edits
                     */
                    dp[i][j] = 1+ Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args){
        System.out.println("Number of edits: "+ editDistance("horse","ros"));
    }
}
