// TC: O(m * n)
// SC: O(m * n)

// Approach: Build the choose not choose tree in the matrix.
// Consider one character at a time from each string and pattern.
// If pattern is '.' or last characters are the same; the answer
// depends on the rest of the string. If pattern is '*', we can either
// not choose (2 steps back in the matrix) or choose only if the character
// before the '*' is same as str. If this is the case, check if with the same
// pattern, you can make the remaining string.

class RegularExpression {
    public boolean isMatch(String s, String p) {
        boolean[][] matrix = new boolean[s.length() + 1][p.length() + 1];
        matrix[0][0] = true;

        // fill first row
        for (int i = 1; i < matrix[0].length; i++) {
            char current = p.charAt(i - 1);
            if (Character.isAlphabetic(current) || current == '.') {
                continue;
            }

            // if it's a '*'; only no choose scenario is applied
            matrix[0][i] = matrix[0][i - 2];
        }

        for (int i = 1; i < matrix.length; i++) {
            char strChar = s.charAt(i - 1);
            for (int j = 1; j < matrix[0].length; j++) {
                char patternChar = p.charAt(j - 1);

                if (strChar == patternChar || patternChar == '.') {
                    // last char matches; check the rest of the string
                    matrix[i][j] = matrix[i - 1][j - 1];
                    continue;
                }

                // last character is alphatbetic and does not match (no way the string can be
                // made)
                if (patternChar != '*') {
                    continue;
                }

                // no choose case
                matrix[i][j] = matrix[i][j - 2];

                // choose scenario only if the last two values are equal
                if (p.charAt(j - 2) == strChar || p.charAt(j - 2) == '.') {
                    matrix[i][j] = matrix[i][j] || matrix[i - 1][j];
                }
            }
        }

        return matrix[s.length()][p.length()];
    }
}