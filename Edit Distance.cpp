//Time Complexity-O(m*n)-->'m' is length of word2 and 'n' is length of word1
//Space Complexity-O(m*n)
//Did the code execute on Leetcode? Yes

class Solution {
public:
    int minDistance(string word1, string word2) {
        if(word2.length()==0)
        {
            return word1.length();
        }
        vector<vector<int>>dp(word2.length()+1,vector<int>(word1.length()+1));
        for(int i=1;i<dp[0].size();i++)
        {
            dp[0][i]=i;    
        }
        for(int i=1;i<dp.size();i++)
        {
            dp[i][0]=i;
        }
        for(int i=1;i<dp.size();i++)
        {
            for(int j=1;j<dp[0].size();j++)
            {
                if(word2[i-1]!=word1[j-1])
                {
                    dp[i][j]=min(min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1])+1;
                }
                else
                {
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[word2.length()][word1.length()];
    }
};