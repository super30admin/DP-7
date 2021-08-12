
// time - O(M*N)
// space - O(M*N)

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();

        boolean [][] dp = new boolean[sl+1][pl+1];

        dp[0][0] = true; // put 0,0 position as true as it is '-'

        for(int j = 1; j < dp[0].length; j++) { // if we find '*' then copy element from two places before the current

            if(p.charAt(j - 1) == '*') {

                dp[0][j] = dp[0][j-2];

            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++) {

                if(p.charAt(j - 1) != '*') { // if it not equal to '*'

                    if(p.charAt(j - 1) == s.charAt(i-1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1]; // if the element matches copy the diagonal elemnt
                    }

                }
                else {
                    // 0 case
                    dp[i][j] = dp[i][j-2]; // if it is equal, 0 case

                    // 1 case if it is preceding character then
                    if(p.charAt(j - 2) == s.charAt(i-1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j]; // 0 case or 1 case which is at top row
                    }


                }

            }

        }
        return dp[sl][pl]; //return the last element

    }
}