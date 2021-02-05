//Time - O(mxn)
//Space - O(mxn)
class Solution {
public:
    int minDistance(string word1, string word2) {
        
        if(word1.size() == 0 || word2.size() == 0) return max(word1.size(),word2.size());
        
        vector<vector<int>> dp (word2.size()+1,vector<int>(word1.size()+1));
        
        for(int i=0;i<=word2.size();i++){
            dp[i][0] = i;
        }
        
        for(int i=0;i<=word1.size();i++){
            dp[0][i] = i;
        }
        
        for(int i=1;i<=word2.size();i++){
            for(int j=1;j<=word1.size();j++){
                if(word2[i-1] == word1[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = min({dp[i-1][j-1],dp[i-1][j],dp[i][j-1]})+1;
                }
            }
        }
        
        return dp[word2.size()][word1.size()];
        
        
    }
};