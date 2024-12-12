class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(); // Length of word1
        int m = word2.length(); // Length of word2
        
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) prev[i] = i;

        // Compute minimum operations
        for (int i = 1; i <= n; i++) {
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    int insert = 1 + curr[j - 1];
                    int replace = 1 + prev[j - 1];
                    int delete = 1 + prev[j];
                    curr[j] = Math.min(insert, Math.min(replace, delete));
                }
            }
            prev = curr.clone();
        }
        
        return prev[m];  // Minimum operations to convert word1 to word2
    }
}
