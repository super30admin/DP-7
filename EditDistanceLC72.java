class Solution {


    //Bottom Up Approach
    //Time Complexity: O(m*n)
    //Space Complexity: O(min(m,n))  

    public int minDistance(String word1, String word2) {

        if(word1.equals(word2)) return 0;

        int n = word1.length();
        int m = word2.length();

        int[] dp = new int[n+1];

        for(int j = 1; j<=n; j++){
            dp[j] = j;
        }

        for(int i = 1; i<=m; i++){
            
            int diagUp =dp[0];
            dp[0] = i;
            
            for(int j=1; j<=n; j++){
                
                int temp = dp[j];
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[j] = diagUp;
                }
                else{
                    dp[j] = 1 + Math.min(
                                    diagUp, Math.min(
                                        dp[j], dp[j-1]
                                    ));
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
    

    //Bottom Up Approach
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n)  

    // public int minDistance(String word1, String word2) {

    //     if(word1.equals(word2)) return 0;

    //     int n = word1.length();
    //     int m = word2.length();

    //     int[][] dp = new int[m+1][n+1];

    //     for(int j = 1; j<=n; j++){
    //         dp[0][j] = j;
    //     }

    //     for(int i = 1; i<=m; i++){
    //         dp[i][0] = i;
    //     }

    //     for(int i = 1; i<=m; i++){
    //         for(int j=1; j<=n; j++){

    //             if(word2.charAt(i-1) == word1.charAt(j-1)){
    //                 dp[i][j] = dp[i-1][j-1];
    //             }
    //             else{
    //                 dp[i][j] = 1 + Math.min(
    //                                 dp[i-1][j-1], 
    //                                 Math.min(
    //                                     dp[i-1][j],
    //                                     dp[i][j-1]
    //                                 ));
    //             }
    //         }
    //     }
    //     return dp[m][n];
    // }



    //Recursive Approach, time limit excceedd error
    //Time Complexity: O(3^max(m*n))
    //Space Complexity: O(max(m, n))
    
    
    //Top Down Approach : Memoizing 
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n) + max(m,n) stack space
    
    
    
    //Bottom Approach 2 with only 2 rows
    //Time Complexity: O(m*n)
    //Space Complexity: O(2*n)
    
    
    //Bottom Approach 3 with only 1 rows and some extra variable
    //Time Complexity: O(m*n)
    //Space Complexity: O(n)
    
    
    
    //Bottom Up Approach 1
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n)
    // public int minDistance(String word1, String word2) {
        
    //     int m = word1.length();
    //     int n = word2.length();
        
    //     int[][] dp = new int[m+1][n+1];
        
    //     for(int i=0; i<=m; i++){
    //         dp[i][0] = i;
    //     }
        
    //     for(int j=0; j<=n; j++){
    //         dp[0][j] = j;
    //     }
        
    //     for(int i=1; i<=m; i++){
    //         for(int j=1; j<=n; j++){
                
    //             if(word1.charAt(i-1) == word2.charAt(j-1)){
    //                 dp[i][j] = dp[i-1][j-1];
    //             }
    //             else{
    //                 dp[i][j] = 1 + Math.min(
    //                     dp[i-1][j-1],
    //                     Math.min(
    //                         dp[i][j-1],
    //                         dp[i-1][j]
    //                     )   
    //                 );
    //             }
    //         }
    //     }
    //     return dp[m][n];
    // }
    
    
    /*
     DP state : minimum number of ways to form a substring(0, j) with given substring(0, i)
   i                     0           1       2         3 => j
                   "Empty String"    r       o       s
   0 "Empty String"        0         1       2       3
   1       h               1         1       2       3
   2       0               2         2       1
   3       r               3
   4       s               4
   5       e               5
    
    if character equals dp[i][j] = dp[i-1][j-1]
    if not
        Insert => 1 + dp[i][j-1]
        Delete => 1 + dp[i-1][j]
        Replace => 1+ dp[i-1][j-1]
    
    */ 
    
}
