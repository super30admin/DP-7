// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode :

public class RegularExpressions {

    public boolean isMatch(String s, String p) {
        int slength = s.length();
        int plength = p.length();

        boolean[][]dp = new boolean[slength + 1][plength + 1];
        dp[0][0] = true;

        //top row -
        for(int j = 1; j <= plength; j++){
            //prev char in my pattern is '*' - Zero case
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; //2 steps left
            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                //normal char
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    //* char
                    //zero case
                    dp[i][j] = dp[i][j-2];
                    //if preceeding char in p and curr matches or  "."
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        //one case
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[slength][plength];
    }
}
