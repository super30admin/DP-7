import java.util.Arrays;

public class EditDistance {

    //bottom up DP using tabulation
    //Time complexity: O(m * n) where m is the length of word1 and n is the length of word2
    //Space complexity: O(m * n)
    //Did this run on Leetcode: yes
    public int minDistance(String word1, String word2) {
        if(word1 == null || word1.length() == 0){
            return word2.length();
        }
        if(word2 == null || word2.length() == 0){
            return word1.length();
        }

        int[][] dp = new int[ word2.length() + 1 ][ word1.length() + 1 ];
        for(int j = 1; j <= word1.length(); j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= word2.length(); i++){
            dp[i][0] = i;
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                char sourceChar = word1.charAt(j - 1);
                char targetChar = word2.charAt(i - 1);
                if(sourceChar == targetChar){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[ word2.length() ][ word1.length() ];
    }



    //Top down recursion with memoization
    //Time complexity: O(m * n) where m is the length of word1 and n is the length of word2
    //Space complexity: O(m * n)
    //Did this run on Leetcode: yes
    public int minDistanceMem(String word1, String word2) {
        if(word1 == null || word1.length() == 0){
            return word2.length();
        }
        if(word2 == null || word2.length() == 0){
            return word1.length();
        }
        int[][] cache = new int[ word1.length() + 1][ word2.length() + 1];
        for(int[] row : cache){
            Arrays.fill(row, -1);
        }
        return editMem(word1, 0, word2, 0, cache);
    }

    private int editMem(String source, int sourceIdx, String target, int targetIdx, int[][] cache){
        if(sourceIdx == source.length() && targetIdx == target.length()){
            cache[sourceIdx][targetIdx] = 0;
            return 0;
        }
        if(sourceIdx == source.length()){
            cache[sourceIdx][targetIdx] = target.length() - targetIdx;
            return target.length() - targetIdx;
        }
        if(targetIdx == target.length()){
            cache[sourceIdx][targetIdx] = source.length() - sourceIdx;
            return source.length() - sourceIdx;
        }

        if( cache[sourceIdx][targetIdx] != -1 ) {
            return cache[sourceIdx][targetIdx];
        }

        char sourceChar = source.charAt(sourceIdx);
        char targetChar =  target.charAt(targetIdx);
        if(sourceChar == targetChar){
            return editMem(source, sourceIdx + 1, target, targetIdx + 1, cache);
        }

        //insert
        int caseInsert = editMem(source, sourceIdx, target, targetIdx + 1, cache);
        int caseDelete = editMem(source, sourceIdx + 1, target, targetIdx, cache);
        int caseReplace = editMem(source, sourceIdx +1, target, targetIdx + 1, cache);

        cache[sourceIdx][targetIdx] =  1 + Math.min(caseInsert, Math.min(caseReplace, caseDelete));
        return cache[sourceIdx][targetIdx];
    }

    //Time complexity: O(4 ^ (m + n)) where m is the length of word1 and n is the length of word2
    //Space complexity: O(m + n) - the recursive stack space
    //Did this run on Leetcode: no, gives TLE
    //Brute Force TLE
     public int minDistanceBruteForce(String word1, String word2) {
         if(word1 == null || word1.length() == 0){
             return word2.length();
         }
         if(word2 == null || word2.length() == 0){
             return word1.length();
         }
         return editBruteForce(word1, 0, word2, 0);
     }

     private int editBruteForce(String source, int sourceIdx, String target, int targetIdx){
         if(sourceIdx == source.length() && targetIdx == target.length()){
             return 0;
         }
         if(sourceIdx == source.length()){
             return target.length() - targetIdx;
         }
         if(targetIdx == target.length()){
             return source.length() - sourceIdx;
         }

         char sourceChar = source.charAt(sourceIdx);
         char targetChar =  target.charAt(targetIdx);
         if(sourceChar == targetChar){
             return editBruteForce(source, sourceIdx + 1, target, targetIdx + 1);
         }

         //insert
         int caseInsert = editBruteForce(source, sourceIdx, target, targetIdx + 1);
         int caseDelete = editBruteForce(source, sourceIdx + 1, target, targetIdx);
         int caseReplace = editBruteForce(source, sourceIdx +1, target, targetIdx + 1);

         return 1 + Math.min(caseInsert, Math.min(caseReplace, caseDelete));
     }
}
