import java.util.Scanner;

public class EditDistance1Ddp {

        // Dynamic Programming - 1D - Time O(m*n) and Space O(n)

        public int minDistance(String word1, String word2) {

            // if words are equal, no change
            if(word1.equals(word2)) {
                return 0;
            }

            // word lengths
            int m = word1.length();
            int n = word2.length();

            int[] dp = new int[n+1];

            // to optimize space
            if(m < n) {

                return minDistance(word2, word1);
            }

            // top row
            for(int j = 1; j <= n; j++) {

                dp[j] = j;
            }

            // build 1D dp array
            for(int i = 1; i <= m; i++) {

                // first diagonal up left for each row
                int diagUpLeft = dp[0];

                // left column
                dp[0] = i;

                for(int j = 1; j <= n; j++) {

                    // store before updating dp[j]
                    int temp = dp[j];

                    // if same characters currently, go left up diagonal
                    if(word1.charAt(i-1) == word2.charAt(j-1)) {

                        dp[j] = diagUpLeft;
                    }

                    else {

                        // if characters do not match currently, add 1 to minimum of left-delete, up-add and diagonal-replace values
                        dp[j] = 1 + Math.min(diagUpLeft, Math.min(dp[j], dp[j-1]));
                    }

                    //
                    diagUpLeft = temp;
                }
            }

            // bottom right value of dp array
            return dp[n];
        }

        public static void main(String[] args) {

            EditDistance1Ddp obj = new EditDistance1Ddp();

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
Space Complexity = O(n)
*/