// Time Complexity : O(m*n)
// Space Complexity : O(m) // only one row is used

// Approach:
// 1. Create a dp matrix of size m+1 and n+1
// 2. Fill the first row and first column
// 3. If the char is not a star, then check if the char is a dot or if the char matches with the string char
// 4. If the char is a star, then check if the preceding char matches with the string char or if the preceding char is a dot
// 5. If the preceding char matches with the string char or if the preceding char is a dot, then check for one and zero case
// 6. If the preceding char does not match with the string char or if the preceding char is not a dot, then check for zero case
// 7. Return the last element of the dp matrix

public class EditDistance {
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(obj.minDistance(word1, word2));
    }

    public int minDistance(String word1, String word2) {
        // base 
        if(word1 == word2) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int j=0;j <= n; j++){
            dp[0][j] = j;

        }

        for(int i=1; i<=m; i++){
            for(int j=0 ; j<=n; j++){
                if(j==0) {
                    dp[i][j] = i; //column 1
                }else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]; // diagonal
                    }else{
                        dp[i][j] = Math.min(dp[i][j-1] , Math.min(dp[i-1][j-1] , dp[i-1][j])) + 1;
                    }
                }
            }
        }
        return dp[m][n];
    }

    public int minDistance1D(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n+1];

        for(int j=0; j<=n; j++)
        {
            dp[j] = j;
        }

        for(int i=1; i<=m; i++){
            int diagUp = dp[0];
            for(int j=0; j<=n; j++){
                int temp = dp[j];
                if(j == 0){
                    dp[j] = i;
                }else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[j] = diagUp;
                    }else{
                        dp[j] = 1 + Math.min(diagUp,Math.min(dp[j-1],dp[j]));
                    }
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }
    
}
