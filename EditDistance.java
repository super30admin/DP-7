//time complexity is O(m*n)
//space complexity is o(m*n) 
//idea is dynamic programming. check if characters between both words are same then check value at earlier character and update it in dp array.
//otherwise update the value in dp array with 1+min value between diagonally left value, left side value and value above it.
public class EditDistance {
	    public int minDistance(String word1, String word2) {
	        int[][] dp = new int[word1.length()+1][word2.length()+1];
	        char[] wordChar1 = word1.toCharArray();
	        char[] wordChar2 = word2.toCharArray();
	           
	        for(int i=0; i < dp[0].length; i++){
	            dp[0][i] = i;
	        }
	        
	        for(int i=0; i < dp.length; i++){
	            dp[i][0] = i;
	        }
	        for(int i = 1 ;i< dp.length; i++){
	            for(int j=1 ;j<dp[0].length;j++){
	                if(wordChar1[i-1] == wordChar2[j-1]){
	                    dp[i][j] = dp[i-1][j-1];
	                }else{
	                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
	                }
	                
	            }
	        }
	        return dp[word1.length()][word2.length()];
	    }
}
