// Time Complexity :O(M*N)
// Space Complexity :O(M*N)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        boolean[][]dp = new boolean[m+1][n+1];

        //only at * we have 01 case 
        // if its * -- > 1 case  and preceding character matches, then check top
                      // 1 case  and preceding character doesnt match, then just False, we dont have its availability   

        // if its * --> 0 case : 2 steps back

        // For non star : if character matches then just check left diagonal up, else just false

        // if '.' and '.*' then, . is same as character, need to check top left. and if it matches(it will match always) 
        dp[0][0] = true;   
        for(int i = 1; i <= n; i++){
            if(p.charAt(i - 1) == '*'){
                dp[0][i] = dp[0][i - 2];
            }
        }
        //No need to take care of 1st col, as it a false

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                char pChar = p.charAt(j - 1); // taking pattern char 
                if(pChar != '*'){ // can be normal character/ '.'
                    if(pChar == s.charAt(i - 1) || pChar == '.' ){// . means it will surely match
                       //it matches, not look for remaining matches in top left
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{  
                    if(p.charAt(j-2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'){// checking preceeding character of pattern matches source   or 2 steps back       
                        dp[i][j] = dp[i-1][j] ||  dp[i][j-2];
                     }else{ // if precesing character doesnt match then, 0 case it is
                        dp[i][j] = dp[i][j-2];
                     }      
              
                        
                }  
            }
        }

        return dp[m][n];


        
    }
}