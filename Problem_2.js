// Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Comming up with the dp solution


// Your code here along with comments explaining your approach

/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    if (s === null || p === null)
        return false;

    if (s === p)
        return true;

    let m = s.length;
    let n = p.length;
    let dp = new Array(m + 1);
    for (let i = 0; i < m + 1; i++) {
        dp[i] = new Array(n + 1);
        dp[i].fill(false);
    }

    dp[0][0] = true;
    for (let j = 1; j < n + 1; j++) {
        if (p[j - 1] === '*')
            dp[0][j] = dp[0][j - 2];
    }
    for (let i = 1; i < m + 1; i++) {
        for (let j = 1; j < n + 1; j++) {
            // It is a char or .
            if (p[j - 1] != '*') {
                // if it is same char or a ., take the diagonal value
                if (s[i - 1] === p[j - 1] || p[j - 1] === '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else {
                // It is a *
                // Zero case
                dp[i][j] = dp[i][j - 2];
                if (s[i - 1] === p[j - 2] || p[j - 2] === '.') {
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }

            }
        }
    }
    return dp[m][n];
};