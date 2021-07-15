// Time complexity-O(nxm)
// space complexity O(n*m);
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : no

// build a dp array to represent the repeating sub problems
// if the char in both strings are equal or char in pat is a '.' use the preceeding diagonal value
// if char in the pattern is '*' try a case wheere you dont pick the character after it and another case where yo pick the character



class Solution {
public:
    bool isMatch(string s, string p) {
        vector<vector<bool>> dp(s.length()+1,vector<bool>(p.length()+1,false));
        
        // top row
        dp[0][0] = true; 
        
        // check for cases in the top row where there is * also note in this case we only pick o occurence since first char is 0
        for(int j=1;j< dp[0].size(); j++){
            if(p[j-1] =='*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        
        for(int i =1; i <dp.size(); i++){
            for(int j =1; j<dp[0].size();j++){
                if(p[j-1] =='.' || p[j-1]== s[i-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else if( p[j-1] =='*'){
                    dp[i][j]=dp[i][j-2];
                    if(p[j-2] == '.' || p[j-2]==s[i-1]){
                        dp[i][j]= dp[i][j] || dp[i-1][j];
                    }
                    
                }
                else{
                    dp[i][j]=false;
                }
            }
        }
        return dp[s.size()][p.size()];
    }
};