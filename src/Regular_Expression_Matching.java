// Time Complexity : O(m*n), O(length of string * length of pattern)
// Space Complexity : O(m*n), O(length of string * length of pattern)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class Regular_Expression_Matching {
	public boolean isMatch(String s, String p) {
		int s1 = s.length();
		int p1 = p.length();
		boolean[][] dp = new boolean[s1 + 1][p1 + 1];   // boolean dp matrix with extra row and col
		//fill the top row
		dp[0][0] = true;
		for(int i=1; i<dp[0].length; i++){
			//if * is found at pattern, go 2 steps back
			if(p.charAt(i-1) == '*'){
				//zero case
				dp[0][i] = dp[0][i-2];  // go 2 steps back
			}
		}
		for(int i=1; i<dp.length; i++){
			for(int j=1; j<dp[0].length; j++){
				if(p.charAt(j-1) != '*') {   //if pattern is not *, it means either its a character / .
					if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){ // if input string and pattern are same or pattern is . , then take diagonal up value
						dp[i][j] = dp[i-1][j-1];

					}
				}
				else{
					//zero case and one case
					dp[i][j] = dp[i][j-2];  //zero case
					//if one case available, if character before * matches character at string s
					if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
						dp[i][j] = dp[i][j] || dp[i-1][j];
					}    
				}
			}       
		}
		return dp[s1][p1];
	}
}
