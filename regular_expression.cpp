// Time Complexity : O(m * n)
// Space Complexity : O(m*n)
// Any problem you faced while coding this :
//Understand the * and matching character case

// Your code here along with comments explaining your approach
//1. Create a dp array with pattern being number of cols and s being rows
//2. If the characters are equal or if pattern has "." just copy the dp value from the previous s & p match which will be at dp[i-1][j-1]
//3. a.If there is a '*' get the value from two characters before.
//4. b.If there is a '*' match the previous element to current element and then get value from dp[i-1][j] position


class Solution {
public:
    bool isMatch(string s, string p) {
        //logic
        int rows = s.length() + 1;
        int cols = p.length() + 1;
        
        vector<vector<bool>> dp(rows,vector<bool>(cols,false));
        dp[0][0] = true;
        
        for(int i=1; i<cols; i++){
            if(p[i-1] == '*')
                dp[0][i] = dp[0][i-2];
        }
        
        //traverse through the dp array
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
             if(p[j-1] == s[i-1] || p[j-1] == '.'){
                     dp[i][j] = dp[i-1][j-1];
                 }
                else if(p[j-1] == '*'){//"*" case
                    //0th case
                     if(j>1){
                       dp[i][j] = dp[i][j-2];  
                     } //1 case
                    if(j>1 && (p[j-2] == s[i-1] || p[j-2] == '.')){
                        if(dp[i-1][j])
                            dp[i][j] = true;
                    }
               }
            }
        }
        return dp[rows-1][cols-1];
    }
};
