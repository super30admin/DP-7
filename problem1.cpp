/*
// Time Complexity : O(M*N) m = length of word-1 , n = length of word 2
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

Taking the base case for empty string , the base condition 


// Your code here along with comments explaining your approach
Create a dp array of size (len_1+1,len_2+1)
fill the row 0 with increasing range(0,len_2+1)
fill the column 0 with increasing range(0,len_1+1)
for any other matrix element 
insert = back+1
del = top+1
replace = diag_up or diag_up + 1 , 
if the character is equal or not equal
*/

#include<iostream>
#include<vector>
#include<string>

using namespace std;

class Solution {
public:
    int minDistance(string word1, string word2) {
        int len_1 = word1.size();
        int len_2 = word2.size();
        vector<vector<int>> dp_arr(len_1+1,vector<int>(len_2+1,0));
        for(int i{};i<=len_1;++i){
            dp_arr.at(i).at(0) = i;
        }
        for(int j{};j<=len_2;++j){
            dp_arr.at(0).at(j) = j;
        }

        for(int i{1};i<=len_1;++i){
            for(int j{1};j<=len_2;++j){
                int insert = dp_arr.at(i).at(j-1) + 1; //back+1
                int del = dp_arr.at(i-1).at(j)+1; //top+1
                int replace = (word1.at(i-1)!=word2.at(j-1))?dp_arr.at(i-1).at(j-1)+1:dp_arr.at(i-1).at(j-1);
                dp_arr.at(i).at(j) = min(insert,min(del,replace));
            }
        }
        return dp_arr.at(len_1).at(len_2);
    }
};

