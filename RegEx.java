
// TC: O(m*n)
// SC: O(m*n) 
//Approach: Use result of smaller sub problem to solve bigger problem.


class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;

        int m = s.length(); int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1]; //taking one extra element in both row and col

        //fill the top row
        dp[0][0] = true; 
        for(int j = 1; j <= n ; j++){
            //zero case
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; // two step back
            }
        }
        //starting from 1st row and col as 1st column will be false as pattern = '-'
        for(int i = 1 ; i <= m ; i++)
        {
            for(int j = 1 ; j <= n ; j++){
                //normal char
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        //diagup
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        //of zero or one case
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }

        return dp[m][n];

    }
}

//approach 2 :

// TC: O(m*n)
// SC: O(n) 

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;

        int m = s.length(); int n = p.length();
        boolean[] dp = new boolean[n+1]; //taking one extra element in both row and col

        //fill the top row
        dp[0] = true; 
        for(int j = 1; j <= n ; j++){
            //zero case
            if(p.charAt(j-1) == '*'){
                dp[j] = dp[j-2]; // two step back
            }
        }
        dp[0] = false; // after first row it will be false
        //starting from 1st row and col as 1st column will be false as pattern = '-'
        for(int i = 1 ; i <= m ; i++)
        {
            boolean diagUp =  false;
            if(i == 1){
                diagUp = true;
            }
            for(int j = 1 ; j <= n ; j++){
                boolean temp = dp[j]; // as it will be overwritten but prev value will be used as diagup
  
                //normal char
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        //diagup
                        dp[j] = diagUp;
                    }else{
                        dp[j] = false;
                    }
                }
                else{
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        //of zero or one case
                        dp[j] = dp[j] || dp[j-2];
                    }
                    else{
                        dp[j] = dp[j-2];
                    }
                }
                diagUp = temp;
            }

        }

        return dp[n];

    }
}