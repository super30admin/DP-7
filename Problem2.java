class Solution {
    //TC: O(m*n)
    //SC: O(n)
    public boolean isMatch(String s, String p) {
         if(s.equals(p)) return true;
         int m = s.length();
         int n = p.length();

         boolean [] dp = new boolean[n+1];
         dp[0] = true;
         //processing top row first
         for(int i = 1; i <= n; i++){
             if(p.charAt(i-1) == '*'){
                 dp[i] = dp[i-2];
             }
         }
         dp[0] = false;
         for(int i = 1; i<= m; i++){ 
             boolean diagup = false;  
             if( i == 1)  diagup = true;  
             for(int j = 1; j <= n; j++){
                 //normal char
                 boolean temp = dp[j];
                 if(p.charAt(j-1) != '*'){
                     if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                         //diag up
                         dp[j] = diagup;
                     }else{
                         dp[j] = false;
                     }
                 }else{
                     //1 case
                      if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                          dp[j] = dp[j] || dp[j-2];
                        }else{
                          dp[j] =  dp[j-2];
                        }
                    }
                    diagup = temp;
                }
                
            }
         
         return dp[n];
    }
}
