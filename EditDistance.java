// Time Complexity: O(m * n)
// Space Complexity: O (m * n)

public class EditDistance {
    public int minDistance(String word1, String word2){
        int m = word1.length();
        int n = word2.length();
        // construct dp

        int [][] dp = new int [m+1][n+1];
        // base case - fill first row and first coloumn
        for(int i =0; i<=m;i++) dp[i][0] =i;
        for(int j=0; j<=n;j++) dp[0][j] = j;

        //fill the remaining dp

        for(int i =1; i<=m; i++){
            for(int j =1; j<=n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){ // if characters of both the words are same then No operation
                    dp[i][j] = dp[i-1][j-1];
                } else{ // choose 1 + min of add(same as top), delete(same as left) and replace(same as top left)
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }

            }
        }
        return dp[m][n];
    }

    public static void main(String [] args){
        EditDistance solution = new EditDistance();

        String word1 = "horse";
        String word2 = "ros";
        int result1 = solution.minDistance(word1,word2);
        System.out.println(result1);

        String word3 = "intention";
        String word4 = "execution";

        int result2 = solution.minDistance(word3,word4);
        System.out.println(result2);
    }
}
