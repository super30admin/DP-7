//EDIT Distance
//O(mn),O(mn)
// idea is to derive pattern between current and word to be transformed by creating matrix betwwn each letter.
//The pattern is distance will be minimum of (left and right +1 ) or diagonal
public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = 1 + Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]);
                }
            }
        }
        return f[n][m];
    
    }
}