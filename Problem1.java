//Recursive Solution
class Solution {
    public int minDistance(String word1, String word2) {
        int i = word1.length(),j = word2.length();
        return helper(i,j,word1,word2);
    }
    public int helper(int i,int j,String word1,String word2){
        if(i == 0)  return j;
        if(j == 0)  return i;
        int z = word1.charAt(i-1) == word2.charAt(j-1)?0:1;
        int y = helper(i,j-1,word1,word2);
        int x = helper(i-1,j,word1,word2);
        return Math.min(helper(i-1,j-1,word1,word2)+z,Math.min(x+1,y+1));
    }
}

//DP Solution
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] result = new int[word1.length()+1][word2.length()+1];
        for(int i = 0;i<result.length;i++)
            result[i][0] = i;
        for(int i = 0;i<result[0].length;i++)
            result[0][i] = i;
        for(int i = 1;i<result.length;i++){
            for(int j = 1;j<result[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    result[i][j] = Math.min(result[i-1][j-1],Math.min(result[i-1][j]+1,result[i][j-1]+1));
                }
                else{
                    result[i][j] = Math.min(1+result[i-1][j-1],Math.min(result[i-1][j]+1,result[i][j-1]+1));
                }
            }
        }
        return result[word1.length()][word2.length()];
    }
}
