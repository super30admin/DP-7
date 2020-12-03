class Solution {
public:
    int minDistance(string s1, string s2) {
        if (s1.length()!=s2.length())
        return 0;
    int row=s1.length();
    int col=s2.length();
    
    int matrix[row+1][col+1]; 
    memset(matrix,0,sizeof(matrix));
    
    for (int i=1;i<=row;i++) {
        for (int j=1;j<=col;j++) {
            if (s1[i-1]==s2[j-1]) {
                matrix[i][j]=matrix[i-1][j-1]+1;
            } else {
                matrix[i][j]=max(matrix[i-1][j],max(matrix[i][j-1],matrix[i-1][j-1]));
            }
        }
    }
    return matrix[row][col];
    }
};