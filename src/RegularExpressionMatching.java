// Time Complexity:  O(m*n)
// Space Complexity: O(n)

class Solution {
    boolean[] dp;
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        dp = new boolean[n+1];
        dp[0] = true;
        for(int j=1; j<=n; j++) {
            if(p.charAt(j-1)=='*')
                dp[j] = dp[j-2];
        }
        for(int i=1; i<=m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j=1; j<=n; j++) {
                boolean temp = dp[j];
                if(p.charAt(j-1)!='*') {
                    if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.') 
                        dp[j] = diagUp;
                    else
                        dp[j] = false;
                }
                else {
                    if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.')
                        dp[j] = dp[j-2] || temp;
                    else
                        dp[j] = dp[j-2];
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}





// // Time Complexity:  O(m*n)
// // Space Complexity: O(m*n)

// class Solution {
//     boolean[][] dp;
//     public boolean isMatch(String s, String p) {
//         if(s.equals(p)) return true;
//         int m = s.length();
//         int n = p.length();
//         dp = new boolean[m+1][n+1];
//         dp[0][0] = true;
//         for(int j=1; j<=n; j++) {
//             if(p.charAt(j-1)=='*')
//                 dp[0][j] = dp[0][j-2];
//         }
//         for(int i=1; i<=m; i++) {
//             for(int j=1; j<=n; j++) {
//                 if(p.charAt(j-1)!='*') {
//                     if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.') 
//                         dp[i][j] = dp[i-1][j-1];
//                 }
//                 else {
//                     if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.')
//                         dp[i][j] = dp[i][j-2] || dp[i-1][j];
//                     else
//                         dp[i][j] = dp[i][j-2];
//                 }
//             }
//         }
//         return dp[m][n];
//     }
// }





// // ******************** DP approach (Memoization) ********************
// // 
// // Time Complexity:  O(n*m)
// // Space Complexity: O(n*m)

// class Solution {
//     String s, p;
//     Map<Pair<Integer, Integer>, Boolean> map;
//     public boolean isMatch(String s, String p) {
//         this.s = s;
//         this.p = p;
//         this.map = new HashMap<>();
//         return dfs(0, 0);
//     }
//     private boolean dfs(int i, int j) {

//         if(map.containsKey(new Pair(i, j))) {                                 // same case already occurs
//             return map.get(new Pair(i, j));
//         }

//         if(i>=s.length() && j>=p.length())                                    // if string, pattern both parsed
//             return true;
//         if(j>=p.length())                                                     // if pattern parsed, but string not
//             return false;
        
//         boolean match = i<s.length() &&                                       // check if both characters matche
//                         (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');

//         if(j+1<p.length() && p.charAt(j+1)=='*') {                            // if next character in pattern is '*'
//             boolean chooseNotChoose = dfs(i, j+2) || (match && dfs(i+1, j));  // not choose, choose
//             map.put(new Pair(i, j), chooseNotChoose);
//             return chooseNotChoose;                                           
//         }

//         if(match) {                                                           // if both characters match simply
//             boolean matchCase = dfs(i+1, j+1);
//             map.put(new Pair(i, j), matchCase);
//             return matchCase;                                   
//         }

//         map.put(new Pair(i, j), false);
//         return false;

//     }
// }






// // ******************** No DP, Just Recursion ********************
// // 
// // Time Complexity:  O(2^n)
// // Space Complexity: O(1)

// class Solution {
//     String s, p;
//     public boolean isMatch(String s, String p) {
//         this.s = s;
//         this.p = p;
//         return dfs(0, 0);
//     }
//     private boolean dfs(int i, int j) {

//         if(i>=s.length() && j>=p.length())                                    // if string, pattern both parsed
//             return true;
//         if(j>=p.length())                                                     // if pattern parsed, but string not
//             return false;
        
//         boolean match = i<s.length() &&                                       // check if both characters matche
//                         (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');

//         if(j+1<p.length() && p.charAt(j+1)=='*') {                            // if next character in pattern is '*'
//             return dfs(i, j+2) || (match && dfs(i+1, j));                     // not choose, choose
//         }

//         if(match) {                                                           // if both characters match simply
//             return dfs(i+1, j+1);                                             
//         }

//         return false;

//     }
// }
