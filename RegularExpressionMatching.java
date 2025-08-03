class Solution {
    public boolean isMatch(String s, String p) {
      int m = s.length();
      int n = p.length();
      boolean[] dp = new boolean[n+1];
      dp[0] = true;
      //fill the top row
      for(int j = 1 ; j <= n ; j++){
        if(p.charAt(j-1) == '*'){
          dp[j] = dp[j-2];
        }
      }
      //start filling matrix
      for(int i = 1 ; i <=m ; i++){
        boolean diagup = dp[0]; //first value of dp[j]
        dp[0] = false; //as we r overwriting , we manually wrote False (like in 2d : -a : - gives false , -aa: - gives false , -aaa : - gives false and - : - is true we already did this above(top row))
        for(int j = 1 ; j <=n ; j++){
          boolean temp = dp[j];
          if(p.charAt(j-1) == '*'){ // we will have 0 case , 1 case
             if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                //1 case 
                dp[j] = dp[j-2] || dp[j];
             }else{
              //0 case : 2 steps back
             dp[j] = dp[j-2];
             }

          }
          else{ //if its normal char
            if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
              dp[j] =diagup; //diaognal up left
            }
            else{
              dp[j] = false;
            }

          }
          diagup = temp; //prev state of dp[j] has to be diagup in 1d
        }
      }
      return dp[n];
        
    }
}