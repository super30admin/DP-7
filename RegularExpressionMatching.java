public class RegularExpressionMatching {

    //Bottom up DP using tabulation
    //Time Complexity: O(s * p) where s is the length of the source string and p is the length of the pattern string
    //Space complexity: O( s * p)
    //Did this run on leetcode: yes
    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }

        if(s.equals(p)){
            return true;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;

        for(int j = 1; j <= p.length(); j++){
            char patternChar = p.charAt(j - 1 );
            if(patternChar == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                char patternChar = p.charAt(j - 1);
                char sourceChar = s.charAt(i - 1);

                if(patternChar == '*'){
                    boolean skipCase = dp[i][j-2];
                    boolean useCase = false;
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == sourceChar){
                        useCase = dp[i-1][j];
                    }
                    dp[i][j] = skipCase || useCase;
                }else{
                    if(patternChar == '.' || patternChar == sourceChar){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }





    //Top down DP with memoization
    //Time Complexity: O(s * p) where s is the length of the source string and p is the length of the pattern string
    //Space complexity: O( s * p)
    //Did this run on leetcode: yes
     public boolean isMatch(String s, String p) {
         if(s == null || p == null){
             return false;
         }
         if(s.equals(p)){
             return true;
         }
         int[][] cache = new int[ s.length() + 1 ][ p.length() + 1];
         for(int[] row : cache){
             Arrays.fill(row, -2);
         }
         return matchPattern(s, p, 0, 0, cache);
     }


     private boolean matchPatternMem(String source, String pattern, int sourceIdx, int patternIdx, int[][] cache){
         //base
         if(cache[sourceIdx][patternIdx] != -2){
             return cache[sourceIdx][patternIdx] == 1;
         }

         if(sourceIdx >= source.length() && patternIdx >= pattern.length()){
             cache[sourceIdx][patternIdx] = 1;
             return true;
         }

         //consider the example s= "a" and p = "a*b*"
         //just because sIdx >= 0 does not mean we can say anything conclusively.

         if(patternIdx >= pattern.length()){
             cache[sourceIdx][patternIdx] = -1;
             return false;
         }

         //check if there is a '*' coming next
         char currentChar = pattern.charAt(patternIdx);
         boolean isMatch = sourceIdx < source.length() && (currentChar == '.' || currentChar == source.charAt(sourceIdx));
         if(patternIdx + 1 < pattern.length() && pattern.charAt(patternIdx + 1) == '*'){
             //use the *
             if(isMatch && matchPatternMem(source, pattern, sourceIdx + 1, patternIdx, cache)){
                 cache[sourceIdx][patternIdx] = 1;
                 return true;
             }
             //don't use the *
             if(matchPatternMem(source, pattern, sourceIdx, patternIdx + 2, cache)){
                 cache[sourceIdx][patternIdx] = 1;
                 return true;
             }

         }else if(isMatch && matchPatternMem(source, pattern, sourceIdx + 1, patternIdx + 1, cache)){
             cache[sourceIdx][patternIdx] = 1;
             return true;
         }
         cache[sourceIdx][patternIdx] = -1;
         return false;
     }


    //Brute force
    //Time Complexity: O(2 ^ (p + s)) where s is the length of the source string and p is the length of the pattern string
    //Space complexity: O(p)
    //Did this run on leetcode: yes
     public boolean isMatch(String s, String p) {
         if(s == null || p == null){
             return false;
         }
         if(s.equals(p)){
             return true;
         }
         return matchPatternBruteForce(s, p, 0, 0);
     }

     private boolean matchPatternBruteForce(String source, String pattern, int sourceIdx, int patternIdx){
         //base
         if(sourceIdx >= source.length() && patternIdx >= pattern.length()){
             return true;
         }

         if(patternIdx >= pattern.length()){
             return false;
         }

         //check if there is a '*' coming next
         char currentChar = pattern.charAt(patternIdx);
         boolean isMatch = sourceIdx < source.length() && (currentChar == '.' || currentChar == source.charAt(sourceIdx));
         if(patternIdx + 1 < pattern.length() && pattern.charAt(patternIdx + 1) == '*'){
             //use the *
             if(isMatch && matchPatternBruteForce(source, pattern, sourceIdx + 1, patternIdx)){
                 return true;
             }
             //don't use the *
             if(matchPatternBruteForce(source, pattern, sourceIdx, patternIdx + 2)){
                 return true;
             }

         }else if(isMatch && matchPatternBruteForce(source, pattern, sourceIdx + 1, patternIdx + 1)){
             return true;
         }
         return false;
     }
}
