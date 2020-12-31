// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
create a dp 2d array with 1 extra row and column after lengths of strings
fill top row set dp[0][i] = i
fill first column dp[i][0] = i

loop over 2d array starting at i, j =1
check if word1 and word2 indexes match then dp[i][j] =dp[i-1][j-1] get from previous diagonal up
else dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]))
//update is diagonal left up
//delete is left of curr
//add is above of curr

return dp[m][n]
*/
package main

import "fmt"

func minDistance(word1 string, word2 string) int {
	m := len(word2)
	n := len(word1)

	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
	}

	//fill first row
	for i := 1; i <= n; i++ {
		dp[0][i] = i
	}
	//fill first col
	for i := 1; i <= m; i++ {
		dp[i][0] = i
	}
	//update is diagonal left up
	//delete is left of curr
	//add is above of curr
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if word1[j-1] == word2[i-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]))
			}
		}
	}
	return dp[m][n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func MainMinDistance() {
	fmt.Println(minDistance("horse", "ros")) //expected 3
}
