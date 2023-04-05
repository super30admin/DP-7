class Solution {

    
    //Bottom Up Approach 3
    //Time Complexity: O(length(s) * length(p))
    //Space Complexity: O(length(p))

    public boolean isMatch(String s, String p) {

        if(s.equals(p)) return true;

        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int j = 1; j<=n; j++){

            if(p.charAt(j-1) == '*'){
                dp[j] = dp[j-2];
            }
        }
        
        for(int i=1; i<=m; i++){
            
            boolean diagUp = dp[0];
            dp[0] = false;
            char sChar = s.charAt(i-1);
            
            for(int j=1; j<=n; j++){

                boolean temp = dp[j];
                char pChar = p.charAt(j-1);

                if(pChar != '*'){
                    
                    if(sChar == pChar || pChar == '.'){
                        dp[j] = diagUp;
                    }
                    else{
                        dp[j] = false;
                    }
                }
                else{
                    
                    char prevPChar = p.charAt(j-2);

                    dp[j] = dp[j-2];                        //not consider case, means we are not repeating the character which is previous to the *

                    if(sChar == prevPChar || prevPChar == '.'){     //consider case, means we are considering the character which is previous to the * if the that character is matching with current sChar or that character is '.'
                        dp[j] = dp[j-2] || temp;
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }


    
    
    //Bottom Up Approach 2
    //Time Complexity: O(length(s) * length(p))
    //Space Complexity: O(lengh(s)* length(p))
    
    // public boolean isMatch(String s, String p) {

    //     if(s.equals(p)) return true;

    //     int m = s.length();
    //     int n = p.length();

    //     boolean[][] dp = new boolean[m+1][n+1];
    //     dp[0][0] = true;

    //     for(int j = 1; j<=n; j++){

    //         if(p.charAt(j-1) == '*'){
    //             dp[0][j] = dp[0][j-2];
    //         }
    //     }

    //     for(int i=1; i<=m; i++){

    //         char sChar = s.charAt(i-1);

    //         for(int j=1; j<=n; j++){

    //             char pChar = p.charAt(j-1);

    //             if(pChar != '*'){
                    
    //                 if(sChar == pChar || pChar == '.'){
    //                     dp[i][j] = dp[i-1][j-1];
    //                 }
    //             }
    //             else{
                    
    //                 char prevPChar = p.charAt(j-2);

    //                 dp[i][j] = dp[i][j-2];                       //not consider case, means we are not repeating the character which is previous to the *

    //                 if(sChar == prevPChar || prevPChar == '.'){          //consider case, means we are considering the character which is previous to the * if the that character is matching with current sChar or that character is '.'
    //                     dp[i][j] = dp[i][j-2] || dp[i-1][j];
    //                 }
    //             }
    //         }
    //     }
    //     return dp[m][n];
    // }
    
    
    
    
    
    //Bottom Up Approach 1
    //Time Complexity: O(length(s) * length(p))
    //Space Complexity: O(lengh(s)* length(p))
    
    //   public boolean isMatch(String s, String p) {
        
    //       //making as a 1 index to avoid confusion
    //       s = " "+ s;
    //       p = " "+ p;
          
    //       int m = s.length();
    //       int n = p.length();
          
    //       boolean[][] dp = new boolean[m][n];
    //       dp[0][0] = true;
    //       dp[0][1] = false; //first character in p is always a character, not * 
          
    //       for(int j=2; j<p.length(); j++){
              
    //           if(p.charAt(j) == '*'){
    //               dp[0][j] = dp[0][j-2];
    //           }
    //       }
          
    //       //System.out.println(Arrays.toString(dp[0]));
    //       for(int i=1; i<s.length(); i++){
              
    //           char sChar = s.charAt(i);
              
    //           for(int j=1; j<p.length(); j++){
                  
    //               char pChar = p.charAt(j);
                  
    //               boolean equal = sChar == pChar || pChar == '.';
                  
    //               if(pChar == '*'){
                      
    //                   char prevPChar = p.charAt(j-1);
    //                   boolean prevEquals = sChar == prevPChar || prevPChar == '.';
                      
    //                   boolean consider = prevEquals && dp[i-1][j];
                      
    //                   // boolean consider = dp[i][j-1] && dp[i-1][j]; // can't write these
    //                   boolean notConsider = dp[i][j-2];
                      
    //                   dp[i][j] = consider || notConsider;    
    //               }
    //               else{
    //                   dp[i][j] = equal && dp[i-1][j-1];   
    //               }
    //           }
    //       }
    //       return dp[m-1][n-1];
    // }
    
    
    //Bottom Up Approach 2, only do with 2 row at a time, we do not need entire matrix, check between s and p, which one is larger and smaller, and take smaller one as a column and change the logic accordingly
    
    //Time Complexity: O(length(s) * length(p))
    //Space Complexity: O(length(p))
    
    
    
    
    //Memoization Approach, Top Down Approach
    //Time Complexity: O(length(s) * length(p))
    //Space Complexity: O(lengh(s)* length(p))
    


    //Recursive Approach
    //Time Complexity: O(2^length(s) + length(p))
    //Space Complexity: O(max(lengh(s), length(p))
    
//     public boolean isMatch(String s, String p) {
        
//         return isMatch(s, 0, p, 0);       
//     }
    
//     public boolean isMatch(String s, int i, String r, int j){
        
//         //base
//         if(j==r.length()){
//             return i==s.length();
//         }

//         //recurse
        
//         boolean ans = false;
        
//         //equal
//         boolean equal = (i != s.length()) && (s.charAt(i) == r.charAt(j) || r.charAt(j) == '.'); 
        
//         //if next character has star
//         boolean hasStar = j+1 < r.length() && r.charAt(j+1) == '*';
        
//         if(hasStar){
            
//             boolean consider = equal && isMatch(s, i+1, r, j);
            
//             boolean notConsider = isMatch(s, i, r, j+2);
            
//             ans = consider || notConsider;
//         }
//         else{
//             ans = equal && isMatch(s, i+1, r, j+1);
//         }
//         return ans;
//     }
}
