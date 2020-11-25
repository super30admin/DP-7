/*
TC: O(M*N)
SC: O(M*N)

1. If char at p matches with char at s. We retain entry at diagonal.
2. If chara at p is star, we have two options - to choose or dont choose.
    2.1 Dont choose: get the value from two steps previous
    2.2 Choose: If previous row value or two steps previous is true, we retain true.
3. If not 1 and 2, then dp array is already filled with false.

*/
public class RegexMatching {
    public boolean regexMatching(String s, String p){
        int c = p.length() + 1;
        int r = s.length() + 1;

        boolean[][] dp = new boolean[r][c];
        //base
        dp[0][0] = true;

        //first row fill
        for(int j = 1; j < c; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i = 1; i < r; i++){
            for(int j = 1; j < c; j++){
                if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2]; //dont choose

                    //choose
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i][j-2] || dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }else if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[r-1][c-1];
    }

    public static void main(String[] args){
        RegexMatching rm = new RegexMatching();
        System.out.println(rm.regexMatching("aab", "c*a*b"));
    }
                    
    

}
