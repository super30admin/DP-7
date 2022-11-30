/*approach - 1
 * this is a tree problem that has choose and not choose case only when encounter a '*'. 
 * recursive solution will be exhausted approach - with BFS and so, TC will be exponential and sc is O(h); h = height of the tree.
 * Note - here BFS wil not require to have size variable. we are not making decisions at level! end goal is to see if the pattern can be convered to string. 
 * 
 */
/*approach -2
 * DP - 
 * Recursive tree holding repeated subproblems leads to DP. 
 * 1. take 2d boolean matrix and by default it's false. 
 * 2. extra 0th row and col will hold " "empty string. and dp[0][0] - can set to true. 
 * 3. fill the 0th row as  - if char = * then go two step back and set that answer. as character can't convert to " "on row, 
 *    rest of them will be hold the default values 
 * 4.case 1 - if character is matching || char is '.' - then just take upper left diagonal value solve as answer
 * 5. case 2 - if charatcer is *; then we have choose and not choose case. 
 *      5.a Not  choose - we already solved this - go two step back [i][j-2] and that's the answer
 *      5.b choose - we solved this s well - look up; [i-1][j] and this is the naswer. 
 *      5.c perform OR operation b/w 5a ||5b
 * tc -O(m*n), sc - O(m*n)
 */

class Solution {
    public boolean isMatch(String s, String p) {
        if(p == null || p.length() ==0) return false;
        if(p.equals(s)) return true;
        
        int slen = s.length(); //row
        int plen = p.length(); //col
        
        //boolean matrix as answer is expected to be boolean
        boolean [][] dp = new boolean [slen+1][plen+1]; //1 row and col will hold empty string. 
        
        //by default the matrix holds false value. 
        
        //first entry with empty string = always hholds true. convert empty to empty 
        dp[0][0] =  true;
        
        //fill the first row
        for(int j= 1; j<plen+1; j++)
        {
            if(p.charAt(j-1) == '*')
                dp[0][j]  = dp[0][j-2]; 
        }

        
         for(int i= 1; i<slen+1; i++)
        {
             for(int j= 1; j<plen+1; j++)
             {
                 //case-1 character matches
                 if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.' ) // if char doesn't match by default it holds false. 
                     dp[i][j] = dp[i-1][j-1]; //take doagonal value
                 
                 if(p.charAt(j-1) == '*')
                 {
                     //do not choose case
                     dp[i][j] = dp[i][j-2];
                     
                     //preceding character to * matches char in s
                     if( p.charAt(j-2)=='.' ||s.charAt(i-1) == p.charAt(j-2) )
                     {
                         //choose case
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                     }
                 }
             }
        }
        return dp[slen][plen];
    }
}


/*approach-3 an be further optimized using just one row and 2 variable.
 * 1. row to hold the 1st row. 
 * 2. 1 variable holds the value two step back. 
 * 3. Diagonal holds the diagonal value
 * 4. temp holds the exisiting cells value before changing.
 * tc - O(m*n), sc = O(n) 
 */

class Solution {
    public boolean isMatch(String s, String p) {
        if(p == null || p.length() ==0) return false;
        if(p.equals(s)) return true;
        
        int slen = s.length(); //row
        int plen = p.length(); //col
        
        //boolean matrix as answer is expected to be boolean
        boolean [] dp = new boolean [plen+1]; //1 row and col will hold empty string. 
        
        //by default the matrix holds false value. 
        
        //first entry with empty string = always hholds true. convert empty to empty 
        dp[0] =  true;
        
        //fill the first row
        for(int j= 1; j<plen+1; j++)
        {
            if(p.charAt(j-1) == '*')
                dp[j]  = dp[j-2]; 
        }

         //[T,F,T,F, T, F]    
        //[F,F,T,F, T, F]
        //prev = T
         for(int i= 1; i<slen+1; i++)
        {
            boolean prev = dp[0];
            dp[0] = false;

             for(int j= 1; j<plen+1; j++)
             {
                 boolean temp = dp[j];
                 //case-1 character matches
                 if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.' ) // if char doesn't match by default it holds false. 
                     dp[j] = prev; //take doagonal value
                 else 
                     dp[j] = false;
                 
                 if(p.charAt(j-1) == '*')
                 {
                     //do not choose case
                     dp[j] = dp[j-2];
                     
                     //preceding character to * matches char in s
                     if( p.charAt(j-2)=='.' ||s.charAt(i-1) == p.charAt(j-2) )
                     {
                         //choose case
                        dp[j] = dp[j] || temp;
                     }
                 }
                 prev = temp;
             }
        }
        return dp[plen];
    }
}