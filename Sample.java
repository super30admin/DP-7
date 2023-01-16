Problem-10 (Regular Expression Matching)
TC and SC- O(m*n)
Solution- 
  
class Solution
{
    public boolean isMatch(String s, String p)
    {
        if(s.equals(p)) return true;
        int s1=s.length();
        int p1=p.length();
        boolean dp[][]=new boolean[s1+1][p1+1];
        dp[0][0]=true;
        for(int j = 1; j < dp[0].length ; j++){
            //first row - we have a *
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; //2 steps back
            }else {
                dp[0][j] = false;
            }
        }

        for(int i = 1 ; i < dp.length ; i++){
            for(int j = 1 ; j < dp[0].length ; j++){
                //incoming character is regular character
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        // diagonal element
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                //incoming character is *
                else{
                    //zero case - 2 steps back
                    dp[i][j] = dp[i][j-2];

                    //one case - 2 steps back OR one step above
                    // check if preceding character is same
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[s1][p1];


        
    }
}














Problem-72 (Edit Distance)
TC and SC- O(m*n)
Solution- 
 class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=n;j++){
            dp[0][j]=j;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i][j-1],Math.min(dp[i-1][j-1],dp[i-1][j]))+1;
                }
            }
        }
        return dp[m][n];
    }
}
