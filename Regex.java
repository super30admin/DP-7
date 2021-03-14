class Regex {
  
    /*
      Time : O(M * N) | M and N being lengths of strings
      Space : O(M * N)
      Leetcode : YES
     */
  
    /*
      Approach :
      1. While iterating through pattern, if we encounter an alphabet there is no zero case, since we have to consider that character.
      2. So if pattern's curr char is not a '*' then check if curr char in the pattern matches with string and if so then consider
         whatever was the value of prev diagonal, abb -> abb | b and b are same so check if ab -> ab (matches) using diagonal 
      2. if we encounter a * then we have two choices, 
         zero case :
                - means do not cosider current element (*) and precceding element (j-1), so value comes from dp[i][j-2].
                - _ -> _c* | in pattern we have *,
                    zero case here means don not append c at all, so we are left with _ -> _
         One Case : 
                - we can only expand *'s precceding char if curr s  and curr p - 1 char are same,
                - invalid 1 case :
                  _a -> _b* | since a and b are not matching we can not expand *. 
                valid 1 case : _a -> _a* | 
                               sine _a equals * - 1 char we can match one a with a, now we only need to check if _ -> _ which is coming from one row above.
    */
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //top row
        for(int i = 1; i <= n; i++ ){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p.charAt(j-1) != '*'){
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    //zero
                    dp[i][j] = dp[i][j-2];
                    //one case if available, when curr is * and char before * is same as s char
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }
                
                
            }
        }
        
        return dp[m][n];
    }
}
