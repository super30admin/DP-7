//Problem 1 Edit Distance
// Time Complexity : O(mn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//At every given character, we have to take all possibilities of operations and check the distance, if any of it has less distance than other, select that.
//O(mn) //O(n)
class Solution {
    //edit case-> diagonal UP+1
    //ADD case -> one step up + 1
    //delete case -> immediate left +1
    //No change -> Diagonal UP
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m=word1.length();
        int n=word2.length();
        int[] dp=new int[n+1];
        for(int j=1; j<=n;j++){
            dp[j]=j;
        }
        int diagUp=0;
        for(int i=1; i<=m;i++){
            for(int j=0; j<=n;j++){
                int temp=dp[j];
                if(j==0)
                    dp[j]=i;
                else{
                    if(word1.charAt(i-1)==word2.charAt(j-1)){
                        dp[j]=dp[j-1]; //diagUp
                    }else{
                        dp[j]=1+Math.min(dp[j], Math.min(dp[j-1], diagUp));
                    }
                }
                diagUp=temp;
            }
        }
        return dp[n];

    }
}


//Problem 2 Regular expression matching
// Time Complexity : O(mn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Ignore '.', only check for * as it can have multiple cases, if the previous character of star is matching with previous of source string, only then we have a multiple case, else just 0 case.
class Solution {
    //incoming character matches, diagonal up left
    // * -> availablility of 0 case -> 2 steps back
    // * -> availablility of 1 case if previous character matches-> take true or false from 2 steps back and one step above
    // . -> matches with any character not -
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m=s.length(), n=p.length();
        // boolean[][] dp=new boolean[m+1][n+1];
        // dp[0][0]=true;
        // //top row
        // for(int j=1;j<=n;j++){
        //     char c=p.charAt(j-1);
        //     if(c=='*')
        //         dp[0][j]=dp[0][j-2];
        // }

        //rest of matrix
        // for(int i=1;i<=m;i++){
        //     for(int j=1;j<=n;j++){
        //         char pChar=p.charAt(j-1);
        //         if(pChar!='*'){
        //             if(pChar==s.charAt(i-1) || pChar=='.'){
        //                 dp[i][j]=dp[i-1][j-1];
        //             }else{ //not required
        //                 dp[i][j]=false;
        //             }
        //         }else{
        //             if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
        //                 dp[i][j]=dp[i-1][j] || dp[i][j-2];
        //             }else{
        //                 dp[i][j]=dp[i][j-2];
        //             }
        //         }
        //     }
        // }
        // return dp[m][n];


        //space
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        //top row
        for(int j=1;j<=n;j++){
            char c=p.charAt(j-1);
            if(c=='*')
                dp[j]=dp[j-2];
        }

        //rest of matrix
        for(int i=1;i<=m;i++){
            boolean diagUp=dp[0];
            for(int j=0;j<=n;j++){
                boolean temp=dp[j];
                if(j==0){
                    dp[j]=false;
                    continue;
                }
                char pChar=p.charAt(j-1);
                if(pChar!='*'){
                    if(pChar==s.charAt(i-1) || pChar=='.'){
                        dp[j]=diagUp;
                    }else{ //not required
                        dp[j]=false;
                    }
                }else{
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
                        dp[j]=dp[j] || dp[j-2];
                    }else{
                        dp[j]=dp[j-2];
                    }
                }
            diagUp=temp;
            }
        }
        return dp[n];
        
    }
}