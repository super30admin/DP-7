// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size();
        int n = p.size();
        
        bool dp[m+1][n+1];
        memset(dp,false,sizeof dp);
        
        dp[0][0] = true;
        
        for(int j=2;j<n+1;j++) // handling case like a*, b*c* 
        {
            if(p[j-1] == '*')
            dp[0][j] = dp[0][j-2];
        }
        
        for(int i = 1;i<m+1;i++)
        {
            for(int j = 1;j<n+1;j++)
            {
                if((s[i-1] == p[j-1]) || (p[j-1]=='.')) 
                {
                    dp[i][j] = dp[i-1][j-1];// if matched, we place the answer of already calculated remaining values i.e., from i-1 to j-1
                }
                else if(p[j-1] == '*')
                {
                    if(s[i-1] == p[j-2] || p[j-2]=='.') //if matched we have two options to take, 1 is : matched then take previous row value as current char is considered in i-1,j position value , 2 is : we can also not consider this * and take the value of i,j-2 position
                    {
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }
                    else// here we have only second option from above
                    {
                        dp[i][j] = dp[i][j-2]; 
                    }
                }
            }
        }
        return dp[m][n];
    }
};