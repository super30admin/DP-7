/*
M -> length of source string
N -> length of pattern string
// Time Complexity : O(M*N)
// Space Complexity : O(M*N) can be optimized to O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
Considering the base condition when strings are empty
Also considering the condition when there is * and it can 
occur more than one time

// Your code here along with comments explaining your approach
If the characters at the index match or '.' check the diagup elements
if the characters at pattern is '*', check -2 index for 0 occurence of preeceding character
check -1 index for 0 occurence of current character
and in this case
if  the preeceding character and current character of the string are equal
then check the top element for prior matching
*/

#include<iostream>
#include<vector>
#include<string>

using namespace std;

class Solution {
public:
    bool isMatch(string s, string p) {
        if(p==".*") return true;
        int s_len = s.size();
        int p_len = p.size();
        vector<vector<bool>> dp_arr(s_len+1,vector<bool>(p_len+1,false));
        dp_arr.at(0).at(0) = true;
        for(int i{1};i<=s_len;++i){
            dp_arr.at(i).at(0) = false;
        }
        for(int j{1};j<=p_len;++j){
            if(p.at(j-1) == '*'){
                dp_arr.at(0).at(j) = dp_arr.at(0).at(j-2);
            }
        }
        for(int i{1};i<=s_len;++i){
            for(int j{1};j<=p_len;++j){
                if(s.at(i-1) == p.at(j-1) || p.at(j-1) == '.'){
                    dp_arr.at(i).at(j) = dp_arr.at(i-1).at(j-1);
                }
                else if(p.at(j-1) == '*'){
                    bool zero_precede_match = dp_arr.at(i).at(j-2);
                    bool zero_current_element = dp_arr.at(i).at(j-1);
                    bool one_current_element = (s.at(i-1) == p.at(j-2) || p.at(j-2) == '.')?dp_arr.at(i-1).at(j):false;
                    dp_arr.at(i).at(j) = zero_precede_match || zero_current_element || one_current_element;
                }
                else{
                    dp_arr.at(i).at(j) = false;
                }
            }
        }
        return dp_arr.at(s_len).at(p_len);            
    }
};