//  tc : O(m*n)
//  sc : O(m*n)

class Solution {
    String word1, word2;
    int m, n ;
    int[][] memo;
    public int minDistance(String word1, String word2) {
        
        this.word1 = word1;
        this.word2 = word2;
        this.m = word1.length();
        this.n = word2.length();
        if(m<n) return minDistance(word2,word1);
        memo = new int[m][n];
        for(int [] mem : memo) Arrays.fill(mem,m+1);
        return helper(0,0);
    }


    private int helper(int i1 , int i2){
        if(i1==m) return n-i2;
        if(i2==n) return m-i1;
        if(memo[i1][i2]!=m+1) return memo[i1][i2];
        if(word1.charAt(i1)==word2.charAt(i2)) return helper(i1+1, i2+1);

        //insert
        int insert = 1+helper(i1,i2+1);
        //delete 
        int delete = 1+helper(i1+1,i2);
        //replace 
        int replace = 1+helper(i1+1,i2+1);

        memo[i1][i2]=Math.min(insert, Math.min(delete,replace));
        return memo[i1][i2];
    }
}
