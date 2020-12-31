// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
create a dp 2d array with 1 extra row and column after lengths of strings
dp[0][0] is true as we can reach from blank(-) to blank(-)
fill top row start from index 1 if p[i-1] (-1 because we have extra col) == * then dp[0][i] = dp[0][i-2] as value of element before star
first column will be all false except 0,0

loop over 2d array starting at i, j =1
if p[j-1]!=* then check if p and s indexes match or if p[j-1] is . then dp[i][j] =dp[i-1][j-1] get from previous

if its a * then we have
0 case not selected dp[i][j]=dp[i][j-2]
1 case selected  p and s indexes match or if p[j-1] is . then if dp[i-1][j] is true then dp[i][j] = true so its an or between element above and element 2 spots to left

return dp[sl][pl]
*/
package main

import "fmt"

func isMatch(s string, p string) bool {
	pl := len(p)
	sl := len(s)

	dp := make([][]bool, sl+1)
	for i := 0; i <= sl; i++ {
		dp[i] = make([]bool, pl+1)
	}

	//top row
	dp[0][0] = true
	for i := 1; i <= pl; i++ {
		if p[i-1] == '*' { //i-1 because we have an extra - initially
			dp[0][i] = dp[0][i-2]
		}
	}

	for i := 1; i <= sl; i++ {
		for j := 1; j <= pl; j++ {
			//normal
			if p[j-1] != '*' {
				if p[j-1] == s[i-1] || p[j-1] == '.' {
					dp[i][j] = dp[i-1][j-1]
				}
			} else { // its a *
				//0 case
				dp[i][j] = dp[i][j-2]
				//1 case if applicable (match previous char)
				if p[j-2] == s[i-1] || p[j-2] == '.' {
					if dp[i-1][j] {
						dp[i][j] = true //so its an or between element above and element 2 spots to left
					}
				}
			}
		}
	}
	return dp[sl][pl]
}

func MainRegularExpression() {
	fmt.Println(isMatch("aab", "c*a*b")) //expected true
}
