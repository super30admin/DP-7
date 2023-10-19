// Time Complexity : O(m*n)
// Space Complexity : O(n) // only one row is used

// Approach:
// 1. Create a dp matrix of size m+1 and n+1
// 2. Fill the first row and first column
// 3. If the char is not a star, then check if the char is a dot or if the char matches with the string char
// 4. If the char is a star, then check if the preceding char matches with the string char or if the preceding char is a dot
// 5. If the preceding char matches with the string char or if the preceding char is a dot, then check for one and zero case
// 6. If the preceding char does not match with the string char or if the preceding char is not a dot, then check for zero case
// 7. Return the last element of the dp matrix

public class RegularExperssion {
    public static void main(String[] args) {
        RegularExperssion obj = new RegularExperssion();
        String s = "aab";
        String p = "c*a*b";
        System.out.println(obj.isMatch(s, p));
        System.out.println(obj.isMatch1D(s, p));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j<=n; j++){
            if(p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j-2];
        }
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(p.charAt(j-1) == '*'){
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
                else
                {
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                        dp[i][j] = dp[i-1][j-1];
                    else
                        dp[i][j] = false;

                }

            }
        }
        return dp[m][n];
        }


        public boolean isMatch1D(String s, String p) {
            int m = s.length();
            int n = p.length();
    
            boolean[] dp = new boolean[n+1];
            dp[0] = true;
            for(int j = 1; j<=n; j++){
                if(p.charAt(j-1) == '*')
                    dp[j] = dp[j-2];
            }
            //boolean diagUp = true;
            for(int i=1; i<=m; i++){
                boolean diagUp = dp[0];
                dp[0] = false;
    
                for(int j=1; j<=n; j++)
                {
                    boolean temp = dp[j];
                    if(p.charAt(j-1) == '*'){
                        if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                            dp[j] = dp[j-2] || dp[j];
                        }else{
                            dp[j] = dp[j-2];
                        }
                    }
                    else
                    {
                        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            dp[j] = diagUp;
                        else
                            dp[j] = false;
    
                    }
                    diagUp = temp;
                }
            }
            return dp[n];
    
        }
}