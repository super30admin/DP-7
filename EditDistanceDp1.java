import java.util.Arrays;

/*
Algorithm (Top-down DP with memoization):
- Recursively compare characters from indices i and j.
- If characters match, recurse on (i+1, j+1).
- Otherwise consider three operations: delete (i+1, j), insert (i, j+1), replace (i+1, j+1) and take 1 + min of them.
- Use memo[i][j] to cache results and avoid recomputation.

Time Complexity: O(m * n) where m = word1.length(), n = word2.length() because each (i,j) state is computed once.
Space Complexity: O(m * n) for the memo table + O(m + n) recursion stack (worst-case), overall O(m * n).
LeetCode: https://leetcode.com/problems/edit-distance/
*/

public class EditDistanceDp1 {

    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for(int[] arr : memo){
            Arrays.fill(arr, -1);
        }
        return helper(word1, word2, 0, 0, memo);
    }

    private int helper(String word1, String word2, int i, int j, int[][] memo) {
        //base
        if (i == word1.length() && j == word2.length()) return 0;
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;

        if(memo[i][j] != -1) return memo[i][j];
        //logic
        int minDist;
        if (word1.charAt(i) == word2.charAt(j)) {
            minDist=  helper(word1, word2, i + 1, j + 1, memo);
        } else {
            int delCase = 1 + helper(word1, word2, i + 1, j, memo);
            int inCase = 1 + helper(word1, word2, i, j + 1, memo);
            int upCase = 1 + helper(word1, word2, i + 1, j + 1, memo);
            minDist = Math.min(delCase, Math.min(inCase, upCase));
        }
        memo[i][j] = minDist;
        return minDist;
    }

    public static void main(String[] args) {
        System.out.println(new EditDistanceDp1().minDistance("intention", "execution"));
    }
}
