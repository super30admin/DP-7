// Time Complexity : O(n * m)
// Space Complexity : O(n * m)
// Method used : DP

class Solution {

    boolean[][] dp;
    boolean flag = false;

    public boolean isMatch(String s, String p) {

        dp = new boolean[s.length() + 1][p.length() + 1];

        // we are doing this bcoz we are optimizing overlapping subproblems which return false so that we don't solve it again
        for (int i = 0; i < s.length() + 1; i++) Arrays.fill(dp[i], true);

        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int index1, int index2)
    {
        // We perfectly matched both the strings
        if(index1 >= s.length() && index2 >= p.length())
        {
            flag = true;
            return true;
        }

        // remember that only string p would have characters containing * and . So if we have crossed this string and index1
        // is still pointing to a valid index then we return false bcoz there is no way we gonna match strings
        // We can't do vice versa saying if index1 >= s.length() return false. Because index2 is still pointing to valid index
        // and that could generate a valid string. For example s = "b" and p = "a*b" and index1 crosses the string s
        // but still we can generate a valid string which matches s
        if(index2 >= p.length()) return false;

        if(dp[index1][index2] == false) return false;

        // Already got the answer
        if(flag == true) return true;

        // This is bcoz index1 might have already crossed the string and we are still trying to generate a valid string from p, then
        // we skip this step. This check should happen after the above one bcoz even if characters are same we could have a *
        // after it in string p
        boolean match = false;

        if(index1 < s.length() && (s.charAt(index1) == p.charAt(index2) || p.charAt(index2) == '.'))
            match = true;

        if(index2 + 1 < p.length() && p.charAt(index2 + 1) == '*')
        {
            //don't choose *, so right now we are at index2 we make 2 jumps from here
            // choose * only if the both the characters of strings are matching. Just used the match variable above
            // As one of the character at index i matched we move to i + 1 but j stays there only
            return dp[index1][index2] = helper(s, p, index1, index2 + 2) || (match == true && helper(s, p, index1 + 1, index2));
        }

        if(match == true) return dp[index1][index2] = helper(s, p, index1 + 1, index2 + 1);

        // None of them were satisfied above
        return false;
    }
}
