//Bottom Up
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0)
            return word2.length();
        else if(word2.length() == 0)
            return word1.length();

        if(word2.length() > word1.length())
            return minDistance(word2, word1);

        int[] matrix = new int[word2.length()+1];
        for(int i=0;i<=word2.length();i++)
            matrix[i] = i;
        int prev = 0;
        int temp;
        for(int i=0;i<=word1.length();i++){
            prev = 0;
            for(int j=0;j<=word2.length();j++){
                temp = matrix[j];
                if(i==0)
                    matrix[j] = j;
                else if(j==0)
                    matrix[j] = i;
                else
                if(word2.charAt(j-1) == word1.charAt(i-1))
                    matrix[j] = min(prev, matrix[j-1]+1,matrix[j]+1);
                else
                    matrix[j] = min(matrix[j], matrix[j-1], prev)+1;

                prev = temp;
            }
        }
        return matrix[word2.length()];
    }

    public int min(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }
}

