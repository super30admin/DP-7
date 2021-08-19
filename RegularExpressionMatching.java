//Time Complexity - O(m*n)
//Sopace Complexity - O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
      int s1 = s.length();
      int p1 = p.length();
      if(s.equals(p)) return true;
      
      boolean dp[][] = new boolean[s1+1][p1+1];
      //fill the first row and column cell
      dp[0][0] = true;
      
      for(int j = 1; j< dp[0].length; j++) {
        if(p.charAt(j-1) == '*'){
          dp[0][j] = dp[0][j-2];
        }
          
      }
      //fill remaining rows and column of dp matrix
      for(int i = 1; i<dp.length;i++) {
        for(int j = 1; j < dp[0].length; j++) {
          if(p.charAt(j-1) != '*') {
            if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
              dp[i][j] = dp[i-1][j-1]; //digonal up element
            }
          } else {
            //if its star
            //0 case consider 0 character
            dp[i][j] = dp[i][j-2];
            //1 case 
            if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
              dp[i][j] = dp[i][j] || dp[i-1][j]; // true or false that means string can be formed from the pattern or not
            }
          }
        }
      }
      return dp[s1][p1];
    }
}