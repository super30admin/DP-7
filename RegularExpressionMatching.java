/*
Time Complexity: O(N*M), N is the length of the string s and M is the length of the string p
Space Complexity: O(N*M) , size of DP array
Run on leetcode: yes
Any difficulties: no

Approach:
Attempted once discussed in the class-> dynamic programming solution
 */
public class RegularExpressionMatching {
    public static boolean regularExpressionMatching(String s, String p){
        s = " "+s;
        p = " "+p;

        int sLen = s.length();
        int pLen = p.length();

        boolean[][] dp = new boolean[sLen][pLen];

        dp[0][0] = true;
        dp[0][1] = false;

        for(int j = 2; j<pLen; j++){
            if(p.charAt(j) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i = 1; i<sLen; i++){
            char sChar = s.charAt(i);

            for(int j = 1; j<pLen; j++){
                char pChar = p.charAt(j);

                boolean equal = sChar == pChar || pChar == '.';
                if(pChar == '*'){
                    char prevPChar = p.charAt(j-1);
                    boolean prevEqual = prevPChar == sChar || prevPChar == '.';

                    boolean consider = prevEqual && (dp[i][j-1] || dp[i-1][j]);
                    boolean noConsider = dp[i][j-2];

                    dp[i][j] = consider || noConsider;
                }else{
                    dp[i][j] = equal && dp[i-1][j-1];
                }
            }
        }
        return dp[sLen-1][pLen-1];
    }

    public static void main(String[] args){
        System.out.println("Regular Expression Matching: "+ regularExpressionMatching("aa", "a"));
        System.out.println("Regular Expression Matching: "+ regularExpressionMatching("aa", "a*"));
        System.out.println("Regular Expression Matching: "+ regularExpressionMatching("ab", ".*"));
    }
}
