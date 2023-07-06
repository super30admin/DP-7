
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// Approach 1: recurrsion + memoization
// Time Complexity : O(m*n) m= word1.size(),n = word2.size()
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes

class Solution {
public:
    vector<vector<int>>dp;
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        
        if(m<n){
            return minDistance(word2,word1);
        }
        dp = vector<vector<int>>(m+1,vector<int>(n+1,-1));
        
        return recurse(word1,word2,0,0,m,n);
    }
    int recurse(string &word1,string &word2,int i,int j,int m,int n)
    {
        if(i==m) return dp[i][j] = n-j;
        if(j==n) return dp[i][j] = m-i;
        if(dp[i][j]!=-1) return dp[i][j];
        
        if(word1[i]==word2[j])
        {
            return dp[i][j] = recurse(word1,word2,i+1,j+1,m,n);
        }
        else
        {
            int inserting = 1 + recurse(word1,word2,i,j+1,m,n);
            
            int deleting = 1 + recurse(word1,word2,i+1,j,m,n);
            
            int replacing = 1 + recurse(word1,word2,i+1,j+1,m,n);
            
            return dp[i][j] = min({inserting,deleting,replacing});
        }
    }
};

// Approach 2: Bottom Up dp 
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes

class Solution {
public:
    
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        
        if(m<n){
            return minDistance(word2,word1);
        }
        vector<vector<int>>dp(m+1,vector<int>(n+1,-1));
        
        for(int i = 0;i<m+1;i++){
            dp[i][0] = i;
        }
        for(int j = 0;j<n+1;j++){
            dp[0][j] = j;
        }
        for(int i = 1;i<m+1;i++)
        {
            for(int j = 1;j<n+1;j++)
            {
                if(word1[i-1] == word2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = 1 + min({dp[i][j-1],dp[i-1][j],dp[i-1][j-1]});
                }
            }
        }
        return dp[m][n];
        
    }

};

// Approach 3: bottom up with space optimization
// Time Complexity : O(m*n)
// Space Complexity : O(min(m,n))
// Did this code successfully run on Leetcode : yes

class Solution {
public:
    
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        
        if(m<n){
            return minDistance(word2,word1);
        }
        vector<int>dp(n+1,0);
        
        int left = 0,up = 0;
        
        for(int j = 0;j<n+1;j++){
            dp[j] = j;
        }
        for(int i = 1;i<m+1;i++)
        {
            for(int j = 0;j<n+1;j++)
            {
                if(j==0){
                    left = dp[j];
                    dp[j] = i;
                    continue;
                }
                up = dp[j];
                if(word1[i-1] == word2[j-1]){
                    dp[j] = left;
                }
                else
                {
                    dp[j] = 1 + min({dp[j-1],left,up});
                }
                left = up;
            }
        }
        return dp[n];
    }
};



