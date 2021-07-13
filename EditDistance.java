public class EditDistance {
    
    //Bottom up DP approach
    //For comparing each sub string with other sub string
    //Perform all three different operations. Update, Delete and Add
    //First row compare with empty string which we need incremental operations.
    //Sub problems can be found in 
    //Delete Left
    //Update  Diagnal Up
    //Add Up
    //If character itself match then get it from top.
    
    public int minDistance(String word1, String word2) {
        
        int m = word2.length();
        int n = word1.length();
        if(n == 0) return m;
        int[][] dp = new int[m+1][n+1];
        
        
        //Fill up the first coloumn
        for(int i=0;i<dp.length;i++) {
            dp[i][0] = i;
        }
        
        //Fill up the first row
        for(int i=0;i<dp[0].length;i++) {
            dp[0][i] = i;
        }
        
        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                //If the character matches, get diagnal
                if(word2.charAt(i-1) == word1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //Delete - Left
                    //Update - Diagnal Up
                    //Add - Up
                    int update = dp[i-1][j-1];
                    int delete = dp[i][j-1];
                    int add = dp[i-1][j];
                    dp[i][j] = 1+ Math.min(Math.min(update, delete), add);
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance edit = new EditDistance();
        int minSteps = edit.minDistance("horse", "ros");
        System.out.println("The minimum steps to form the word 2 is: "+ minSteps);
    }
}
