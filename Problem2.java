package DP-7;
// TC: O(m*n)
// SC: O(n)
public class Problem2 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n+1];
        dp[0]=true;
        for(int j =1;j<=n;j++){
            if(p.charAt(j-1)=='*') dp[j] = dp[j-2]; 
        }
        
        for(int i=1;i<=m;i++){
            boolean diagUp=dp[0];
            dp[0]=false;
            for(int j=1;j<=n;j++){
                boolean temp = dp[j];
                char pChar=p.charAt(j-1);
                if(pChar=='*'){
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) =='.'){
                        dp[j] = dp[j-2] || dp[j];
                    }else{
                        dp[j] = dp[j-2];
                    }
                }else{
                    if(pChar==s.charAt(i-1) || pChar=='.'){
                        dp[j] = diagUp;
                    }else{
                        dp[j] = false;
                    }
                }
                diagUp=temp;
            }
        }

        return dp[n];
    }
}
