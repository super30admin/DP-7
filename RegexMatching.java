// Time Complexity O(m* n)
// Space Complexity O(m * n)
public class RegexMatching {
    public boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean [m +1][n +1];

        // base case - empty string will always be true
        dp[0][0]= true;

        // pattern with '*'

        for ( int j =1; j <=n; j++){
            if( p.charAt(j -1 ) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        // fi;ll the dp table
        for( int i =1; i <=m; i++){
            for ( int j =1; j <=n; j++){
                if(p.charAt(j -1) == s.charAt(i -1) || p.charAt(j -1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j -1) == '*'){
                    dp[i][j] = dp[i][j - 2];// match zero occurances
                    if(p.charAt(j -2) == s.charAt(i - 1) || p.charAt(j - 2) =='.') {
                        dp[i][j] |= dp[i-1][j]; //match one or more occurance
                    }
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        RegexMatching solution = new RegexMatching();
        System.out.println(solution.isMatch("aab", "c*a*b")); // true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
