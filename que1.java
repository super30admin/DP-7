class Solution {
    private boolean helper(String s, int i, String p, int j) {
        // base
        if (j == p.length()){
            return i==s.length();
        }

        // TODO
        // first character
        boolean fistCharMatch =(i < s.length()) &&  (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // TODO
        // 2ND char of pattern is a *
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // match the strings
            boolean take = fistCharMatch && helper(s, i + 1, p, j);
            // not match strings
            boolean notTake = helper(s, i, p, j + 2);
            return take || notTake;
        }
        // TODO
        // 2ND char of pattern is a char
        else {
            return fistCharMatch && helper(s, i + 1, p, j + 1);
        }

    }

    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }
}
