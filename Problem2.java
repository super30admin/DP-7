//time complexity-O(M*N)
//Space complexity-O(M*N)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public boolean isMatch(String s, String p) {
        int n= s.length();
        int m= p.length();
        boolean [][]dp = new boolean[n+1][m+1];//just like edit distance question the dp matrix represtns same
        dp[0][0]=true;

         for(int j=1;j<m+1;j++){
             if(p.charAt(j-1)  =='*')
                 dp[0][j]=dp[0][j-2];
        }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                
                if(s.charAt(i-1)==p.charAt(j-1)|| p.charAt(j-1)=='.' ){
                    dp[i][j]=dp[i-1][j-1];
                }
                
                else if(s.charAt(i-1)!=p.charAt(j-1) && p.charAt(j-1)!='*'){
                    dp[i][j]= false;
                }
                
                else{
                    boolean DC=dp[i][j-2];
                    boolean C=false;
                    if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.')//for any * we choose it previous element or do not choose it
                        C=dp[i-1][j];
                
                    
                    dp[i][j]= DC|| C;
                }
            }
        }
        return dp[n][m];
        
    }
}

