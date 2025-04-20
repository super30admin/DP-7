import java.util.HashMap;

/**
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * Brute-force and then memoized
 */

public class RegularExpressionMatch {
    // ------------ Brute Force -----------------------------
    public boolean isMatch1(String s, String p) {
        return match1(s, p, 0, 0);
    }
    
    private boolean match1(String s, String p, int sIndex, int pIndex) {
        // base
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        // logic
        // check first character match
        boolean firstMatch = (sIndex < s.length() && 
        (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.'));

        // check for `*`
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            // Two options:
            // 1. Skip the `char*` pair (zero occurrence)
            // 2. Use `char*` for one match if `firstMatch` is true
            return (match(s, p, sIndex, pIndex + 2) || (firstMatch && match(s, p, sIndex + 1, pIndex)));
        }
            
        return firstMatch && match(s, p, sIndex + 1, pIndex + 1);
    }

    // -------------- Memoization ---------------------------
    HashMap<String, Boolean> memo;
    public boolean isMatch(String s, String p) {
        memo = new HashMap<>();
        return match(s, p, 0, 0);
    }

    private boolean match(String s, String p, int sIndex, int pIndex) {
        String key = sIndex + ":" + pIndex;

        if (memo.containsKey(key)) return memo.get(key);

        boolean result;
        // base
        if (pIndex == p.length()) {
            result = sIndex == s.length();
        } else {
            // logic
            // check first character match
            boolean firstMatch = (sIndex < s.length() && 
            (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.'));

            // check for `*`
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                // Two options:
                // 1. Skip the `char*` pair (zero occurrence)
                // 2. Use `char*` for one match if `firstMatch` is true
                result = (match(s, p, sIndex, pIndex + 2) || (firstMatch && match(s, p, sIndex + 1, pIndex)));
            } else {
                result = firstMatch && match(s, p, sIndex + 1, pIndex + 1);
            }
        }
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        // Your code here
    }
}