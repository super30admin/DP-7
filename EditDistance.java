// 72.
// time - O(length of word1 * length of word2)
// space - O(length of word1 * length of word2)
class Solution {
    public int minDistance(String word1, String word2) {
        int w1 = word1.length();
        int w2 = word2.length();
        int[][] result = new int[w1 + 1][w2 + 1];
        
        //base
        result[0][0] = 0; //minimum operations to convert '' to '' is 0
        for(int i = 1; i < result[0].length; i++)
        {
            result[0][i] = i; //word2 is empty in 1st row -> so delete chars in word1 to convert it to word2
        }
        for(int i = 1; i < result.length; i++)
        {
            result[i][0] = i; //word1 is empty in 1st col -> so delete chars in word2 to convert it to word1
        }
        
        for(int i = 1; i < result.length; i++)
        {
            for(int j = 1; j < result[0].length; j++)
            {
                char wOne = word1.charAt(i - 1); //-1 because of 0 indexing
                char wTwo = word2.charAt(j - 1);
                if(wOne == wTwo)
                {
                    //same as number of opns to convert word1 to word2 excluding the current
                    result[i][j] = result[i - 1][j - 1];
                }
                else
                {
                    //1 + min(update, delete, add)
                    result[i][j] = 1 + Math.min(result[i - 1][j - 1], Math.min(result[i - 1][j], result[i][j - 1]));
                }
            }
        }
        
        return result[w1][w2];
    }
}
