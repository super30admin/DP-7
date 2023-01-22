public class EditDistance {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1,word2));
    }
    //TC O(m*n) SC O(n)
    public static int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        if(m>n) return minDistance(word2, word1);
        int[] dp = new int[n+1];

        for(int j = 0; j<n+1;j++)  dp[j]=j;

        for(int i = 0;i<m;i++) {
            int prev = i;
            dp[0]=i;
            for(int j = 1; j<n+1;j++){
                int temp = dp[j];
                if(word1.charAt(i)==word2.charAt(j-1)) dp[j] = prev;
                else dp[j] = 1+ Math.min(dp[j-1], Math.min(dp[j], prev));
                prev = temp;
            }
        }
        return dp[n];
    }



    //TC O(m*n) SC O(m*n)
    /*
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        if(m>n) return minDistance(word2, word1);
        int[][] dp = new int[m+1][n+1];
        for(int i = 0 ;i<m+1;i++) dp[i][0]=i;
        for(int j = 1; j<n+1;j++)  dp[0][j]=j;

        for(int i = 1;i<m+1;i++) {
            for(int j = 1; j<n+1;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1+ Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[m][n];
    }
    * */

}
