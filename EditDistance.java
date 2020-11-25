/*
TC: O(M * N)
SC: O(M * N)

1. At each step we can do either of three operations:
Remove / Add / Update

Convertion of abcde -> fghij can be done in 3 ways.
Update
1. If we know abcd -> fgh, then replace e with i. dp[i-1][j-1] + 1.

Delete
2. If we know abcd -> fghi, then delete e. dp[i][j-1] + 1.

Add
3. If we know abcde -> fgh, then add i. dp[i-1][j] + 1.

We need to choose the min of these operations. 
*/


public class EditDistance{

    public int editDistance(String word1, String word2){

        int c = word1.length(), r = word2.length();

        int[][] dp = new int[r + 1][c + 1];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j < dp[0].length; j++){
              dp[0][j] = j;  
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(word1.charAt(j - 1) == word2.charAt(i - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args){
        EditDistance ed = new EditDistance();
        System.out.println(ed.editDistance("horse", "ros"));
    }



}