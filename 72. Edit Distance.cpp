/*
Time: O(n^2)
Space: O(n^2)

Use DP
At every position, we have the choises : insert/delete/replace or move (if chars in both str match)
*/
class Solution {
public: 
    string s1, s2;
    int n1, n2;
    vector<vector<int>> dp;

    int call(int p1, int p2){
        if(p1==n1)  return n2-p2;
        if(p2==n2)  return n1-p1;

        if(dp[p1][p2] != -1)  return dp[p1][p2];
        if(s1[p1] == s2[p2])  return dp[p1][p2] = call(p1+1,p2+1);
        
        int o1 = 1 + call(p1,p2+1); //insert at s1 / delete at s2
        int o2 = 1 + call(p1+1,p2); //insert at s2 / delete at s1
        int o3 = 1 + call(p1+1,p2+1); //replace a char at either string
        
        return dp[p1][p2] = min(min(o1, o2), o3);
    }

    int minDistance(string word1, string word2) {
        s1 = word1;
        s2 = word2;
        
        n1 = word1.length();
        n2 = word2.length();

        dp = vector(n1,vector<int>(n2,-1));

        return call(0,0);
    }
};
