// Time Complexity : O(n*m) where n is the size of String 1 and m is the size of string 2 == Size of the DP matrix
// Space Complexity : O(n*m)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No

// Approach
// In recursive approach, from last char(can be started from first char as well) of each string we haev 3 operation to make - Insert, Delete and Replace. 
// Let i be the last char in Word 1 and j be the last char in word 2 --> 
//          For Insert, do j-- as we will insert only in first word with the intend to make second word. So keep i as it is and move j forward
//          For Delete, do i-- as we remove a letter from word 1, i has to be moved forward.
//          For Replace, do i--, j-- replace the word in word 1 and start comparing next char from both the words.
//        Finally, Min(Inser, Delete, Replace)+1 for each Char will be the result. Add one for current operation as all INSER, DEL & REPLACE take one.

// DP Solution
// Min operation to reach destination for char of WOrd 1 and Word 2 can be calculated and Memoized for further processing
// word1 --> horse & word 2 --> ros
//    ""  r    o   s 
// ""  0  1    2   3
// h   1 0+1  1+1 2+1 
// o   2
// r   3
// s   4
// e   5
// First ROW gives the cost of Insert operation
// First COL gives the cost of Delete Operation
// Left Diagonal value gives the cost of REPLACE Operation 
// Minimum of these three values gves the cost of operation for each letter combination among the words.

class Solution {
    public int minDistance(String word1, String word2) {
     
        int n = word1.length();
        int m = word2.length();
        int[][] distanceMatrix = new int[n+1][m+1];
        
        // distanceMatrix[0][0] = 0;
        for(int i=0; i<= m;i++){
            distanceMatrix[0][i]  = i;
        }
        for(int i=0; i<= n;i++){
            distanceMatrix[i][0]  = i;
        }
        
        for(int i=1; i<= n; i++){
            for(int j=1; j<= m; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){  // When both letters in Word 1 an word 2 are same, NO operation needed, Just retain the operations performed so far.
                    distanceMatrix[i][j] = distanceMatrix[i-1][j-1];
                }
                else{
                    distanceMatrix[i][j] = 1 + Math.min(Math.min(distanceMatrix[i][j-1], 
                                                     distanceMatrix[i-1][j]),
                                                        distanceMatrix[i-1][j-1]);
                }
            }
        }
        return distanceMatrix[n][m];
    }
    
    //*******Recursive
    private int minDist(String w1, String w2, int i, int j){
        
        if(i == 0 )
            return j;
        if(j == 0)
            return i;
        
        if(w1.charAt(i-1) == w2.charAt(j-1))
            return minDist(w1, w2, i-1, j-1);
        
        return 1 + Math.min(Math.min(minDist(w1, w2, i, j-1),
                                     minDist(w1, w2, i-1, j)), 
                            minDist(w1, w2, i-1, j-1));
        
    }
}
