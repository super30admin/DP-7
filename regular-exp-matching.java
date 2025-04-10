// TC: OO(2^(n+m))
// SC: O(n+m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }
    
    private boolean helper(String s, String p, int i, int j) {
        // Base case: if the end of the pattern is reached, the string must also be exhausted.
        if (j == p.length()) return i == s.length();
        
        // If the next character in the pattern is '*', consider two cases.
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            // Case 0: Treat the '*' and its preceding character as matching zero instances.
            boolean case0 = helper(s, p, i, j + 2);
            boolean case1 = false;
            // Case 1: If the current character in s matches the current character in p (or p has '.'),
            // proceed to match the rest of s with the same pattern j.
            if (i != s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
                case1 = helper(s, p, i + 1, j);
            }
            return case0 || case1;
        } else {
            // If no '*' is present, ensure the current characters of s and p match.
            if (i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
                return helper(s, p, i + 1, j + 1);
            }
        }
        return false;
    }
}
