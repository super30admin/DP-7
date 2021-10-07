//https://leetcode.com/problems/regular-expression-matching/
/*
Time: O(m*n) where m=s.length(), n=p.length()
Space: O(m*n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class RegexMatching {
    public boolean isMatch(String s, String p) {
        boolean T[][] = new boolean[s.length() + 1][p.length() + 1];

        char[] str = s.toCharArray(); // y axis
        char[] pattern = p.toCharArray(); // x axis

        T[0][0] = true;
        // Deals with 0th Row with patterns like a* or a*b* or a*b*c* can match with
        // empty string

        for (int j = 1; j < T[0].length; j++)
            if (pattern[j - 1] == '*')
                T[0][j] = T[0][j - 2];

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {

                if (pattern[j - 1] == '.' || pattern[j - 1] == str[i - 1]) // see a dot (or) see a same char
                    T[i][j] = T[i - 1][j - 1]; // diagonally

                else if (pattern[j - 1] == '*') {
                    // zero occurance
                    T[i][j] = T[i][j - 2]; // stay on same row and move 2 cols back

                    // one or more occurance
                    if (pattern[j - 2] == '.' || pattern[j - 2] == str[i - 1]) // same as prev if block
                        T[i][j] = T[i][j] || T[i - 1][j]; // curr || top

                }

                else // values are different
                    T[i][j] = false;

            }
        }
        return T[str.length][pattern.length];
    }

}
