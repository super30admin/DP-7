//Time Complexity-O(m*n)-->'m' is length of string and 'n' is length of pattern
//Space Complexity-O(m*n)
//Did the code run on Leetcode? yes

class Solution {
public:
    bool isMatch(string s, string p) {
         if(p.length()==0)
         {
             return s==p;
         }
        vector<vector<bool>>dp(s.length()+1,vector<bool>(p.length()+1));
        dp[0][0]=true;
        for(int i=1;i<dp[0].size();i++)
        {
            if(p[i-1]=='*')
            {
                dp[0][i]=dp[0][i-2];
            }
        }
        for(int i=1;i<dp.size();i++)
        {
            for(int j=1;j<dp[0].size();j++)
            {
                if(s[i-1]==p[j-1] || p[j-1]=='.')
                {
                    dp[i][j]=dp[i-1][j-1];
                }
                if(p[j-1]=='*')
                {
                    dp[i][j]=dp[i][j-2];
                    if(s[i-1]==p[j-2] || p[j-2]=='.')
                    {
                        if(dp[i][j-2]==true || dp[i-1][j]==true)
                        {
                            dp[i][j]=true;
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
};