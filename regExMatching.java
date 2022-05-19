//TC: O(m*n) m-> length of s string , n -> length of p string
//SC: O(m*n)

//CODE:

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[sl+1][pl+1];
        dp[0][0] =true;
        //filling first row
        for(int j=1;j<dp[0].length;j++){
            // if we  find a "*" then we will have only zero case for first row which is two steps back
            if(p.charAt(j-1) == '*') dp[0][j] = dp[0][j-2];
        }
        //filling rest of the matrix
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                //for j=0  , it will be false(except dp[0][0])
                
                //if we get a normal character
                if(p.charAt(j-1) != '*'){
                    //if it is a normal character and it matches with s string ch
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) =='.'){
                        dp[i][j]= dp[i-1][j-1]; //taking value from diagonal
                    }
                }else{
                    //if it is a "*"
                    //zero case ,which is always available and is two steps back
                    dp[i][j]=dp[i][j-2];
                    
                    //one case-> if we consider * , which may be available which is just one step up 
                    //if the preceding char before '*' is equal to last ch of s string or preceding char before '*' is a dot
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j]; //taking 'or' of zero and one case
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
        
    }
}