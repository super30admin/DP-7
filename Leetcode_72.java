//Dp with memoization
//Use memoization array to avoid repetetion
//As there are two variables word1, word2- use 2D memo
//Start dfs with start index of word1 and word2
//If character at the index is same for both word1 and word2 - do i+1, j+1
//If character at the index is not same for both word1 and word2 - do add , del, upd and get minimum of them
//Add an element: i; j+1
//upd an element: i+1, j+1
//del an element: i+1, j
//TC: m*n
//SC: m*n
class Solution {
    public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();

        int[][] memo=new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            Arrays.fill(memo[i],-1);
        }

        return dfs(word1, word2, memo, 0, 0);
    }

    private int dfs(String word1, String word2, int[][] memo, int i, int j){
        //base case
        if(i==word1.length()) return word2.length()-j;
        if(j==word2.length()) return word1.length()-i;

        if(memo[i][j]!=-1) return memo[i][j];


        //logic
        int result;
        if(word1.charAt(i)==word2.charAt(j)){
            result= dfs(word1, word2, memo, i+1, j+1);
        }else
        {
            int add = 1+dfs(word1, word2, memo, i, j+1);
            int del = 1+dfs(word1, word2, memo, i+1, j);
            int upd = 1+dfs(word1, word2, memo, i+1, j+1);

            result= Math.min(add, Math.min(del, upd));
            
        }

        memo[i][j]=result;
        return result;
    }


}

