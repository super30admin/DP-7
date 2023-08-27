import java.util.Scanner;

public class RegularExpressionMatching {

        // Dynamic Programming - Time O(m*n) and Space O(m*n)

        public boolean isMatch(String s, String p) {

            if(s.equals(p)) {
                return true;
            }

            int m = s.length();
            int n = p.length();

            boolean[][] dp = new boolean[m+1][n+1];

            // first element true
            dp[0][0] = true;

            // fill first row
            for(int j = 1; j <= n; j++) {

                if(p.charAt(j-1) == '*') {

                    dp[0][j] = dp[0][j-2];
                }
            }

            // build 2D dp array
            for(int i = 1; i <= m; i++) {

                for(int j = 1; j <= n; j++) {

                    // character in p is not *
                    if(p.charAt(j-1) != '*') {

                        // matching s, p characters
                        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {

                            // get from diagonal up left
                            dp[i][j] = dp[i-1][j-1];
                        }
                    }

                    // character in p is *
                    else {

                        // preceding character to * in p matches character in s
                        if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {

                            // zero case-two columns back or one case - one row before
                            dp[i][j] = dp[i][j-2] || dp[i-1][j];
                        }

                        else {

                            // character in p is * and preceding p character does not match with s character, zero case-two columns back
                            dp[i][j] = dp[i][j-2];
                        }
                    }
                }
            }
            // bottom right boolean output
            return dp[m][n];
        }

        public static void main(String[] args) {

            RegularExpressionMatching obj = new RegularExpressionMatching();

            Scanner scanner = new Scanner(System.in);

            System.out.println("String: ");
            String s = scanner.nextLine();

            System.out.println("Pattern: ");
            String p = scanner.nextLine();

            boolean answer = obj.isMatch(s,p);

            System.out.println("Is there a matching? " + answer);
        }



}

/*
Time Complexity = O(m*n)
Space Complexity = O(m*n)
*/