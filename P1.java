//TC - O(m * n)
// SC - O(m * n)

class Solution {

    public boolean isMatch(String s, String p) {

        int[][] memo = new int[s.length() + 1][p.length() + 1];

        return helper(s, p, 0, 0, memo);

    }

    private boolean helper(String s, String p, int i, int j, int[][] memo) {

        //base

        if (j == p.length())
            return i == s.length();

        if (memo[i][j] != 0) { //checks if i, j already visited/evaluated

            return memo[i][j] == 1;

        }

        //logic

        boolean result = false;

        //next char is *

        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {

            boolean case0 = helper(s, p, i, j + 2, memo);

            boolean case1 = false;

            //preceding char in p == curr char in s

            if (i != s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {

                case1 = helper(s, p, i + 1, j, memo);

            }

            result = case0 || case1;

        } else {

            if (i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {

                result = helper(s, p, i + 1, j + 1, memo);

            }

        }

        if (result) {

            memo[i][j] = 1; //visited and marked true

        } else {

            memo[i][j] = -1;//visited and marked false

        }

        return result;

    }

}