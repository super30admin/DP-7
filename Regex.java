class Solution {
    //Time Complexity:O(s*p)
    //Space Complexity:O(s*p)
    public boolean isMatch(String s, String p) {
       if (s.equals(p)) 
       return true;
       
       int m = s.length();
       int n = p.length();
       boolean [][] dp = new boolean [m+1][n+1];

        dp [0][0] = true;
    
       for (int j =1;j < n+1 ; j++){
        if (p.charAt(j-1) == '*'){
            dp[0][j] = dp[0][j-2];
        }
       }

       for (int i= 1 ; i < m+ 1;i++){
       for (int j= 1 ; j < n+ 1;j++){
        //if character in s matches in p or wildcard
        if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){ //
               dp[i][j] = dp[i-1][j-1]; // then take diagonal
               //explaination:  if the two characters match, then take the result if both weren't there 
            } else if (p.charAt(j-1) == '*') {
                dp[i][j] = dp [i][j-2];
                if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                //           ^ -> taking the preceding character here and checking
                    dp[i][j] = dp[i][j] || dp[i-1][j];
                }

            }
            }
       }
       return dp [m][n];
    }
}
