//Time complexity: O(m*n) where m is the length of string s and n the length of string p
// Space complexity: O(m * n)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;

        int m = s.length();
        int n = p.length();

        boolean[][] matrix = new boolean[m+1][n+1];
        matrix[0][0] = true;

        // set only first row
        for (int j=1; j < n + 1; j++) {
            if (p.charAt(j-1) == '*') {
                matrix[0][j] = matrix[0][j-2];
            }
        }

        // filling the rest of the matrix
        for(int i=1; i <m+1; i++) {
            for (int j =1; j < n+1; j++) {
                // dealing with not a star
                if(p.charAt(j - 1) != '*') {
                    // check if the the characters match
                    if ((p.charAt(j - 1) == s.charAt(i-1)) || (p.charAt(j - 1) == '.')) {
                        matrix[i][j] = matrix[i-1][j-1];
                    }
                }  else { // dealing with a star
                    // zero case
                    matrix[i][j] = matrix[i][j - 2];
                    // 1 case only if the previous characters match
                    if ((p.charAt(j - 2) == s.charAt(i - 1)) || (p.charAt(j - 2) == '.')) {
                        matrix[i][j] = matrix[i][j] || matrix[i-1][j];
                    }
                }
            }
        }

        return matrix[m][n];
    }
}