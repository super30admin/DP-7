/*Time Complexity: O(3^N)

Space Complexity: O(N) - stack space

Did this code successfully run on Leetcode : Yes

Approach: Recursion

Prob: 10. Regular Expression Matching
*/

class Solution {
    public boolean helper(int i,int j,String s, String p){
        // base
        if(i < 0 && j < 0) return true;
        // if pattern is over and source is remaining
        if(j < 0) return false;
        // if source is over and pattern is remaining
        if(i < 0){
            boolean starFound = false;
            for(int k=j;k>=0;k--){
                if(p.charAt(k) == '*'){
                    starFound = true;
                } else {
                    if(starFound){
                        starFound = false;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } 
        // logic
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            return helper(i-1,j-1,s,p);
        }
        else if(p.charAt(j) == '*'){
            boolean zeroOccurence = helper(i,j-2,s,p);

            boolean oneOccurrence = false;

            boolean multipleOccurrence = false;

            if(p.charAt(j-1) == s.charAt(i) || p.charAt(j-1) == '.'){
                oneOccurrence = helper(i-1,j-2,s,p);
                
                multipleOccurrence = helper(i-1,j,s,p);
            }

            return (zeroOccurence || oneOccurrence || multipleOccurrence);
        }
        
        return false;
    }
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        return helper(m-1,n-1,s,p);
    }
}