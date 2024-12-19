class Solution {
    public int minDistance(String word1, String word2) {
        int m =word1.length();
        int n = word2.length();
        int[][]memo = new int[n+1][m+1];
        return helper(word1,word2,0,0,m,n,memo);
    }
    private int helper(String word1,String word2, int i, int j, int m, int n,int[][] memo){
        //base
        if(i==n) return m-j;
        if(j==m) return n-i;
        //logic
        if(memo[i][j]==0){
            if(word1.charAt(j)!=word2.charAt(i)){
                int add = helper(word1,word2,i+1,j,m,n,memo);
                int delete = helper(word1,word2,i,j+1,m,n,memo);
                int edit = helper(word1,word2,i+1,j+1,m,n,memo);
                
                memo[i][j] = 1+Math.min(add,Math.min(edit,delete));
            }
            else{
                memo[i][j] = helper(word1,word2,i+1,j+1,m,n,memo);
            }
        }
        return memo[i][j];
    }
}
