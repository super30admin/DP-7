/**
Time Complexity - O(m * n) where "m" is the length of word1 and "n" is the length of word2. 
Space Complexity - O(m * n).
 */
class Solution {
    public int minDistance(String word1, String word2) {
        
        if(word1.length() == 0 && word2.length() == 0)
            return 0;
        if(word1.length() == 0)
            return word2.length();
        if(word2.length() == 0)
            return word1.length();

        int len1 = word1.length(), len2 = word2.length();
        int[][] dist = new int[len1 + 1][len2 + 1];

        for(int i = 0; i <= len1; i++) 
            dist[i][0] = i;
        
        for(int i = 0; i <= len2; i++) 
            dist[0][i] = i;

        for(int i = 0; i < len1; i++)
            for(int j = 0; j < len2; j++) 
                if(word1.charAt(i) == word2.charAt(j))
                    dist[i + 1][j + 1] = dist[i][j];
                else
                    dist[i + 1][j + 1] = 1 + Math.min(dist[i][j], Math.min(dist[i + 1][j], dist[i][j +1]));
        
        return dist[len1][len2];
    }
}
