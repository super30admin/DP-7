/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n^2)
* 
* Space Complexity: O(2n)
* 
*/

public class EditDistanceSpaceOptmization {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int m = word1.length();

        int n = word2.length();

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int index = 0; index <= n; index++) {
            // word1 exhausted or empty
            prev[index] = index;
        }

        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    // operations
                    // add
                    int add = 1 + curr[j - 1];

                    // delete
                    int delete = 1 + prev[j];

                    // update
                    int update = 1 + prev[j - 1];

                    curr[j] = Math.min(Math.min(add, delete), update);
                }
            }
            prev = curr.clone();
        }

        return prev[n];
    }
}