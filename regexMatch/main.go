func isMatch(s string, p string) bool {
    // p = cols
    // s = rows
    m := len(s)
    n := len(p)
    dp := make([][]bool, m+1)
    for idx, _ := range dp {dp[idx] = make([]bool, n+1)}
    dp[0][0] = true
    for j := 1; j < len(dp[0]); j++ {
        if p[j-1] == '*' {
            dp[0][j] = dp[0][j-2]
        }
    }
    
    for i := 1; i < len(dp); i++ {
        for j := 1; j < len(dp[0]); j++ {
            if p[j-1] != '*' {
                if p[j-1] == s[i-1] || p[j-1] == '.' {
                    dp[i][j] = dp[i-1][j-1]
                }
            } else {
                dp[i][j] = dp[i][j-2]
                if p[j-2] == s[i-1] || p[j-2] == '.' {
                    dp[i][j] = dp[i][j] || dp[i-1][j]
                } 
            }
        }
    }
    return dp[len(dp)-1][len(dp[0])-1]
}
