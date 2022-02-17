class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || s.length() == 0) return false; //base case where if s is null or the length of s is 0, we return 0
        if(s.equals(p)) return true; //if s is equal to p, we return true
        int m = s.length(); // m stores the length of s
        int n = p.length(); // n stores the length of p
        boolean[][] dp = new boolean[m + 1][n + 1]; //boolean array of size m + 1 and n + 1
        dp[0][0] = true; //we mark the first location which is (0,0) as true
        for(int j = 1; j < n + 1; j++) { //we go through all the elements in the cols to compute the first row
            if(p.charAt(j - 1) == '*') { //if p is having a * at j - 1
                dp[0][j] = dp[0][j - 2]; //we bring the value of j - 2 to j
            }
        }
        for(int i = 1; i < dp.length; i++) { //we go through all the elements in the row starting from 1
            for(int j = 1; j < dp[0].length; j++) { //we go through all the elements in the col starting from 1
                if(p.charAt(j - 1) != '*') { //if the location at j is having a *
                    if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') { //if charat j - 1 for p and s are same or charat p is .
                        dp[i][j] = dp[i - 1][j - 1]; //we are updating the boolean at i and j
                    }
                }
                else {
                    //zero case
                    dp[i][j] = dp[i][j - 2];
                    //one case
                    if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n]; //in the end, we return dp[m][n]
    }
}
//tc and sc - O(m * n)