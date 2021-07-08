
/* Problem Statement: 
Verified on LeetCode
10. Regular Expression Matching
Hard

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false



 * Solution 1 : using Brute Force method. all combinations using recursion  
 * Time Complexity : O(2^N) at every stage if string matches we have 2 choices based on .* pattern  
 * Space Complexity : O(1) excluding the stack space taken by function calls 

 * Solution 2 : Dynamic Programming
 * Time complexity : O(S*P) where S is size of string and P is size of pattern
 * Space complexity : O(S*P)
 */
class Solution {
public:
    /* RECURSION METHOD : TIME LIMIT EXCEEDED */
    int slen,plen;
    bool ismatch(int idx1, int idx2, string s,string p) {
        bool did_it_match = false;
        /* If pattern is finished, then check if string is also finished, ifyes return true */
        if (idx2 == plen) {
            return idx1 == slen;
        }
        
        did_it_match = (idx1 < slen) && (p[idx2] == '.' || s[idx1] == p[idx2]);
        if ((idx2 + 1 < plen) && p[idx2 + 1] == '*') {
                return ((did_it_match && ismatch(idx1 + 1, idx2, s, p)) || 
                        ismatch(idx1, idx2 + 2, s, p));
        } else {
            return did_it_match && ismatch(idx1+1,idx2+1,s,p);
        }
    }
    
    bool isMatch(string s, string p) {
        slen = s.length();
        plen = p.length();
        
        return ismatch(0,0,s,p);
    }
};

class Solution {
public:
    bool isMatch(string s, string p) {
        int slen = s.length();
        int plen = p.length();
        int idx1, idx2;
        
        bool dp[slen + 1][plen + 1];
        // initialize with false
        for (idx1 = 0; idx1 <= slen; idx1++) {
            for (idx2 = 0; idx2 <= plen; idx2++) {
                dp[idx1][idx2] = false;
            }
        }
        dp[0][0] = true;
        
        /* for null string fill the row matching with pattern */
        for (idx1 = 1; idx1 <= plen; idx1++) {
            if (p[idx1 - 1] == '*' && idx1 - 2 >= 0) {
                dp[0][idx1] = dp[0][idx1 - 2];
            }
        }
        
        // for null pattern, the corresponding column will always be false
        
        // now start scanning for each string[i] && pattern[j]
        
        // scan through all changes 
        for (idx1 = 1; idx1 <= slen; idx1++) {
            for (idx2 = 1; idx2 <= plen; idx2++) {
                
                /* if str char match with pattern char using . or exact match use the previous diagonal value as dp[idx1-1][idx2-1] will have the answer of str with pattern match upto that point */
                if (s[idx1-1] == p[idx2-1] || p[idx2-1] == '.') {
                    dp[idx1][idx2] = dp[idx1-1][idx2-1];
                } else if (p[idx2 - 1] == '*') {
                    /* first search if for zero occurrences of x* pattern do we have a match if it doesn't have a match then check if it has 1 or more occurrences */
                    if (dp[idx1][idx2-2] == true) {
                        dp[idx1][idx2] = true;
                    } else {
                        /* search if for x* pattern, the x is matching the string or the pattern is .* then copy it from the previous row which means that for the current char in string I have got a match, let me see if the string before current char matched with pattern or not. and that info is stored in dp[idx1-1][idx2] */
                        if (s[idx1 - 1 ] == p[idx2 - 2] || p[idx2 - 2] == '.') {
                            dp[idx1][idx2] = dp[idx1 - 1][idx2];
                        }
                    }
                }
            }
        }  
        return dp[slen][plen];
    }
};
