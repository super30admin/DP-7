import java.util.Scanner;

public class EditDistance {

        // Dynamic Programming - Time O(m*n) and Space O(m*n)

        public int minDistance(String word1, String word2) {

            // if words are equal, no change
            if(word1.equals(word2)) {
                return 0;
            }

            // word lengths
            int m = word1.length();
            int n = word2.length();

            int[][] dp = new int[m+1][n+1];

            // to optimize space
            if(m < n) {

                return minDistance(word2, word1);
            }

            // top row
            for(int j = 1; j <= n; j++) {

                dp[0][j] = j;
            }

            // left column
            for(int i = 1; i <= m; i++) {

                dp[i][0] = i;
            }

            // build 2D dp array
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {

                    // if same characters currently, go left up diagonal
                    if(word1.charAt(i-1) == word2.charAt(j-1)) {

                        dp[i][j] = dp[i-1][j-1];
                    }

                    else {

                        // if characters do not match currently, add 1 to minimum of left-delete, up-add and diagonal-replace values
                        dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                    }
                }
            }

            // bottom right value of dp array
            return dp[m][n];
        }

        public static void main(String[] args) {

            EditDistance obj = new EditDistance();

            Scanner scanner = new Scanner(System.in);

            System.out.println("word 1: ");
            String word1 = scanner.nextLine();

            System.out.println("word 2: ");
            String word2 = scanner.nextLine();

            int answer = obj.minDistance(word1, word2);

            System.out.println("the minimum number of operations required to convert word1 to word2: " + answer);
        }

}

/*
Time Complexity = O(m*n)
Space Complexity = O(m*n)
*/