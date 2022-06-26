// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


public bool IsMatch(string s, string p) {
        
    if(s == p)
        return true;
    
    int m = s.Length +1;
    int n = p.Length + 1;
    bool[,] dp = new bool[m,n];
    
    dp[0,0] = true;
    
    for(int i = 1; i < n; i++)
    {
        //first row, if we encounter *, we take value from 2 step back 
        //because it is 0 case, where we dont consider prceeding element from *, so result from 2 step back
        if(p[i-1] == '*')
        {                
            dp[0,i] = dp[0,i-2];
        }
    }

    for(int i = 1; i < m; i++)
    {
        for(int j = 1; j < n; j++)
        {
            if(p[j-1] != '*')
            {
                //if character dont match, it should be false, by default all values are false
                //if character matches, we need to take above diagonal value
                if(p[j-1] == s[i-1] || p[j-1] == '.')
                    dp[i,j] = dp[i-1,j-1];                        
            }
            else
            {
                //we take 0 case
                dp[i,j] = dp[i,j-2];
                //we check if 1 case is avaialbe to us, where we choose the element
                //if character matches with two step back, then we take OR of current and value one above
                if(p[j-2] == s[i-1] || p[j-2] == '.')
                    dp[i,j] = dp[i,j] || dp[i-1,j];
            }                
        }
    }

    return dp[m-1,n-1];
}