// TC: O(m * n)
// SC: O(m * n)

// Approach: Take one character at a time from both strings.
// If they are the same, the number of operations should only
// depend on the remaining string. If not, take min of add, replace
// and delete.

class EditDistance {
    public int minDistance(String word1, String word2) {
        int[][] matrix = new int[word1.length() + 1][word2.length() + 1];
        matrix[0][0] = 0;

        // first row fill
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = i;
        }

        // first column fill
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = i;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // last two chars match; so keep them as is and check number of ops on remaining
                    // string
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    // min of add, delete, replace operation
                    matrix[i][j] = 1 + Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]));
                }
            }
        }

        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}