package com.example.problems;

import java.util.Arrays;
//Time Complexity : O(N^2)
//Space Complexity : O(N^2)
//Did this code successfully run on Leetcode : YES
//Any problem you faced while coding this : NO


//Your code here along with comments explaining your approach

/*
 * DP Algo for Solving Edit Distance Problem
 * 	1. Let m = size of first word,n = size of second word
 *  2. Create DP Matrix of Size (m + 1) and (n + 1)
 *  3. Set First Row and Column of DP Matrix to its position
 *  4. Now start traversing words and compare respective characters  
 *  	Now two cases arises for characters comparsion
 *  	a.) Character matches --> cache the index of matching character in both words.Let index be i,j
 *  		i.) Look for index i,j in dp matrix.Let this value be dp[i][j]
 *  		ii.) Looking for upper,left adjacent and upper left cell of dp[i][j].
 *  			Let values be upper_left_cell = dp[i - 1][j - 1]
 *  						  upper_cell = dp[i - 1][j]
 *  						   left_adjacent_cell = dp[i][j - 1
 *  		iii.) Find minimum of upper_left,upper and left_adjancent_Cell and add 1 to it and update the value of dp[i][j]
 *    				  dp[i][j] = min(upper_left_cell,upper_cell,left_adjacent_cell) + 1
 *    
 *  	b.) Character mismatches --> cache the index of mismatching characters in both words.Let index be i,j;
 *  		Now copy the value of dp[i - 1][j - 1] into dp[i][j]
 *  			dp[i][j] = dp[i - 1][j - 1];
 *   
 *   5. Repeat Steps 4 until all characters are processed and return min value
 */
public class EditDistance {

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int dp[][] = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= n; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					
				}
			}
		}
		System.out.println(Arrays.deepToString(dp));
		return dp[m][n];
	}

	public static void main(String args[]) {
		String s1 = "plasma";
		String s2 = "altruism";
		System.out.println(new EditDistance().minDistance(s1, s2));
	}
}
