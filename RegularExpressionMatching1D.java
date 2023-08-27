import java.util.Scanner;

public class RegularExpressionMatching1D {

        // Dynamic Programming - 1D - Time O(m*n) and Space O(n)

        public boolean isMatch(String s, String p) {

            if(s.equals(p)) {
                return true;
            }

            int m = s.length();
            int n = p.length();

            boolean[] dp = new boolean[n+1];

            // first element true
            dp[0] = true;

            // fill first row
            for(int j = 1; j <= n; j++) {

                if(p.charAt(j-1) == '*') {

                    dp[j] = dp[j-2];
                }
            }

            // build 2D dp array
            for(int i = 1; i <= m; i++) {

                // diagUpLeft is initially of first column
                boolean diagUpLeft = dp[0];

                // first column false
                dp[0] = false;

                for(int j = 1; j <= n; j++) {

                    // store dp[j] before updating
                    boolean temp = dp[j];

                    // character in p is not *
                    if(p.charAt(j-1) != '*') {

                        // matching s, p characters
                        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {

                            // get from diagonal up left
                            dp[j] = diagUpLeft;
                        }

                        else {

                            // make dp[j] false, if no match
                            dp[j] = false;
                        }
                    }

                    // character in p is *
                    else {

                        // preceding character to * in p matches character in s
                        if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {

                            // zero case-two columns back or one case - one row before
                            dp[j] = dp[j-2] || dp[j];
                        }

                        else {

                            // character in p is * and preceding p character does not match with s character, zero case-two columns back
                            dp[j] = dp[j-2];
                        }
                    }
                    // dp[j] before updating stored in temp becomes new diagonal up left
                    diagUpLeft = temp;
                }

            }
            // bottom right boolean output
            return dp[n];
        }

        public static void main(String[] args) {

            RegularExpressionMatching1D obj = new RegularExpressionMatching1D();
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
Space Complexity = O(n)
*/