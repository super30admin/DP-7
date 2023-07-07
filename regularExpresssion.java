// tc : O(m*n)
// sc : O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] memo = new boolean[s.length()+1][p.length()+1];
        memo[0][0] = true;
        //deal with empty s and given p with *;
        for(int i = 2; i < p.length()+1; i++) if(p.charAt(i-1)=='*') memo[0][i]=memo[0][i-2];

        //logic;
        for(int i =0; i < s.length() ; i++){
            for(int j =0 ; j < p.length() ; j++){
                char s1 = s.charAt(i);
                char p1 = p.charAt(j);
                if(s1==p1 || p1=='.') memo[i+1][j+1] = memo[i][j];
                else if(p1=='*'){
                    p1 = p.charAt(j-1);
                    if(s1==p1 || p1=='.'){
                        memo[i+1][j+1] = (memo[i][j+1]  || memo[i+1][j-1]);
                    }
                    else {
                        memo[i+1][j+1] = memo[i+1][j-1];
                    }
                }
            }
        }
        return memo[s.length()][p.length()];
    }
}
