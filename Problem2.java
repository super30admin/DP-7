// class Solution {
//     public boolean isMatch(String s, String p) {
//         boolean matrix[][] = new boolean[p.length()+1][s.length()+1];

//         matrix[0][0] = true;

//         for(int i=1;i<=p.length();i++){
//             for(int j=0;j<=s.length();j++){
//                 if(j==0)
//                     matrix[i][j] = p.charAt(i-1)=='*'?matrix[i-2][j]:false;
//                 else if(p.charAt(i-1) == s.charAt(j-1))
//                     matrix[i][j] = matrix[i-1][j-1];
//                 else if(p.charAt(i-1) == '*'){
//                     if(matrix[i-2][j] || matrix[i-1][j])
//                         matrix[i][j] = true;
//                     else if(matrix[i][j-1])
//                         matrix[i][j] = p.charAt(i-2) == '.' || p.charAt(i-2) == s.charAt(j-1);
//                 }
//                 else if(p.charAt(i-1) == '.')
//                     matrix[i][j] = matrix[i-1][j-1];
//                 else
//                     matrix[i][j] = false;
//             }
//         }
//         return matrix[p.length()][s.length()];
//     }


// }

class Solution {
    public boolean isMatch(String s, String p) {
        boolean matrix[] = new boolean[p.length()+1];

        matrix[0] = true;

        boolean prev = true;
        boolean temp;
        for(int i = 1;i<=p.length();i++)
            matrix[i] = p.charAt(i-1) == '*'?matrix[i-2]:false;
        for(int i=1;i<=s.length();i++){
            matrix[0] = false;
            for(int j=1;j<=p.length();j++){
                temp = matrix[j];
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                    matrix[j] = prev;
                else if(p.charAt(j-1) == '*'){
                    if(matrix[j-1] || matrix[j-2])
                        matrix[j] = true;
                    else if(s.charAt(i-1) != p.charAt(j-2) && p.charAt(j-2)!='.')
                        matrix[j] = false;
                }
                else
                    matrix[j] = false;

                prev = temp;
            }
            prev = false;
        }
        return matrix[p.length()];
    }
}