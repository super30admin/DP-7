public class RegularExpMatching {
    //Time complexity: O(M*N)
    //Space complexity: O(M*N)
     public boolean isMatchDP(String s, String p) {

         if(p.equals(".*")) return true;

         //case 1: when isCharacter-> if Match then diag up
         //case 2: when . diag up
         //case 3: when * 0 case 2 steps back and 1 case above

         boolean[][] dp = new boolean[s.length()+1][p.length()+1];
         dp[0][0] = true;
         for(int j=1; j<= p.length(); j++){
             if(p.charAt(j - 1) == '*') dp[0][j] = dp[0][j-2];
         }

         for(int i = 1 ; i<= s.length(); i++){
             dp[i][0] = false;
             for(int j = 1; j <= p.length(); j++){

                 //case1 when pattern is character and matches or case 2 when pattern is '.'
                 if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                     dp[i][j] = dp[i-1][j-1];
                 }
                 //case 3 when pattern is *
                 else if(p.charAt(j-1) == '*'){
                     if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                         // 1 case exists where we can repeat preceeding char as it matches
                         dp[i][j] = dp[i-1][j] || dp[i][j-2];

                     }
                     else{
                         dp[i][j] = dp[i][j-2];
                     }
                 }
             }
         }
         return dp[s.length()][p.length()];
     }

    //Time complexity: O(M*N)
    //Space complexity: O(N)
    public boolean isMatchSpaceoptimized(String s, String p) {

        if(p.equals(".*")) return true;

        boolean[] dp = new boolean[p.length()+1];
        dp[0] = true;
        for(int j=1; j<= p.length(); j++){
            if(p.charAt(j - 1) == '*') dp[j] = dp[j-2];
        }


        boolean diagUp, temp;
        for(int i = 1 ; i<= s.length(); i++){
            dp[0] = false;
            if(i==1) diagUp = true;
            else diagUp = dp[0];
            for(int j = 1; j <= p.length(); j++){
                temp = dp[j];

                //case1 when pattern is character and matches or case 2 when pattern is '.'
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[j] = diagUp;
                }
                //case 3 when pattern is *
                else if(p.charAt(j-1) == '*'){
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        // 1 case exists where we can repeat preceeding char as it matches
                        dp[j] = dp[j] || dp[j-2];

                    }
                    else{
                        dp[j] = dp[j-2];
                    }
                }
                else{
                    dp[j] = false;
                }
                diagUp = temp;
            }
        }
        return dp[p.length()];
    }
}
