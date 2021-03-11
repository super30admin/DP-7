/*
For this problem, we can observe that to match s with p we can recursiviely adding and removeing characters and this is leading to repeated subproblems. Therefore we can use dp

2d dp array of size m * n
-at each step we check if characters are matching, or if there is '*' or '.'
-if the character in pattern is '*', we go 2 step back and above, to check preceeding character. 
-if the characters are matching, we check diagnally up.

TC: O(m*n), m and n are length of strings
SC: O(m*n)

*/
class Solution {
    public boolean isMatch(String s, String p) {
        
        int l1 = s.length();
        int l2 = p.length();
        
        boolean[][]dp = new boolean[l1+1][l2+1];
        
        dp[0][0]= true;// black matches
        
        //for first row
        for(int j = 1; j <= l2;j++){
            if(p.charAt(j-1) == '*'){
                //check 2 step back
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i <= l1; i++){
            for(int j = 1; j <= l2;j++){
                
                //check if character is not '*'
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        //diagonal up
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                
                else{
                    //we can have zero or more chars
                    //case 1: zero char, take 2 steps back
                    dp[i][j] = dp[i][j-2]; //2 steps back
                    
                    //case 1: one char, take from above
                    //here, also check preceding char of pattern string
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i-1][j]){
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                    
                }
            }
        }
        
        
        
        return dp[l1][l2];
    }
    
}