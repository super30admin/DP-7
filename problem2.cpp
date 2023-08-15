/*
Time complexity: O(mn)
Space complexity: O(n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
*/

class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        vector<bool> pre(n + 1, false), cur(n + 1, false);
        cur[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p[j - 1] == '*') {
                    cur[j] = cur[j - 2] || (i && pre[j] && (p[j - 2] == s[i - 1] || p[j - 2] == '.'));
                } else {
                    cur[j] = i && pre[j - 1] && (p[j - 1] == s[i - 1] || p[j - 1] == '.');
                }
            }
			swap(pre, cur);
            fill(cur.begin(), cur.end(), false);
        }
        return pre[n];
    }
};