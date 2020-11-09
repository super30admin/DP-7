// Time Complexity : O(n2)
// Space Complexity : O(n2)
// Any problem you faced while coding this :
//Understand the operations once

// Your code here along with comments explaining your approach
//1. Create dp array. Fill it in based on the minimum values from the left, diagonal and top cells
//2. Return the last index
//3. The operations are printed by backtracking


//diagonal: insert
//left:delete
//up :replace

class Solution {
public:
    int minDistance(string word1, string word2) {
        //edge
        if(word2.length()==0)
            return word1.length();
        
        //logic:DP
        int rows = word2.length()+1;
        int cols = word1.length()+1;
        vector<vector<pair<int,char>>> dp(rows, vector<pair<int,char>>(cols));
        
        //fill first row
        for(int i=0; i<cols;i++){
            pair<int, char> temp ={i, 'D'};
            dp[0][i] = temp;
        }
        //fill first col
        for(int i=0; i<rows;i++){
            pair<int, char> temp ={i, 'I'};
            dp[i][0] = temp;
        }
        //iterate through the row
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                if(word2[i-1] == word1[j-1]){
                    pair<int, char> temp = dp[i-1][j-1];
                    temp.second = 'N';
                    dp[i][j] = temp;
                }else{
                    pair<int, char> temp;
                    int v1 = dp[i-1][j-1].first;
                    int v2 = dp[i-1][j].first;
                    if(v1<v2){
                        temp.second = 'R';
                        temp.first = 1+v1;
                    }else{
                       temp.second = 'I'; 
                       temp.first = 1+v2;
                    }
                    
                    int v3 = dp[i][j-1].first;
                    
                    if(temp.first > v3){
                        temp.second = 'D';
                        temp.first = 1+v3;
                    }
                    
                    dp[i][j] = temp;    
                }
            }
        }
//diagonal: insert
//left:delete
//up :replace
        int i=rows-1; int j=cols-1;
        while(i>=1 && j>=1){
            if(dp[i][j].second == 'N' || dp[i][j].second == 'I' ){
                cout<<dp[i][j].second<<"\t";
                    i--; j--;
                }
            else if(dp[i][j].second == 'R'  ){
                cout<<dp[i][j].second<<"\t";
                    i--; 
                }
             else if(dp[i][j].second == 'D'  ){
                cout<<dp[i][j].second<<"\t";
                    j--; 
                }
        }
        cout<<endl;
        return dp[rows-1][cols-1].first;
    }
};