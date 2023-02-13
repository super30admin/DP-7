import java.util.Arrays;

//Time O(M.N) where M,N is the length of given words
// Space O(M.N) for DP 

public class EditDistance {
	 int[][] dp;
	    public int minDistance(String word1, String word2) {   
	        if (word1 == null || word1.length() == 0) return word2.length();
	        if (word2 == null || word2.length() == 0) return word1.length();
	         dp = new int[word1.length()][word2.length()];
	         Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
	        return helper(word1,word2,0,0);
	    }

	    private int helper(String word1, String word2,int index1, int index2){

	        // base 
	       if (word1.length() == index1) {
	            return word2.length() - index2;
	        }
	        if (word2.length() == index2) {
	            return word1.length() - index1;
	        }

	        if(dp[index1][index2] !=-1)
	            return dp[index1][index2];

	        //logic
	        if(word1.charAt(index1)==word2.charAt(index2)){
	            dp[index1][index2] = helper(word1,word2,index1+1,index2+1);
	            return dp[index1][index2];
	        }
	        int delete = helper(word1,word2,index1+1,index2);
	     
	        int replace = helper(word1, word2,index1+1,index2+1);

	       
	        int insert = helper(word1,word2,index1,index2+1);

	        dp[index1][index2]= 1+Math.min(insert,Math.min(replace,delete));
	        return dp[index1][index2];
	    }
}
