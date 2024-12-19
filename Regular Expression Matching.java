class Solution {
    public boolean isMatch(String s, String p) {
        int m = p.length();
        int n = s.length();
        int[][] memo=new int[n+1][m+1];
        return helper(s,p,0,0,m,n,memo);
    }
    private boolean helper(String s, String p, int i, int j, int m, int n,int[][]memo){
        //base
        if(j==m) return i==n;
        //logic
        boolean result;
        if(memo[i][j]==0){
            if(j<m-1 && p.charAt(j+1)=='*'){
                if(i<n && (p.charAt(j)==s.charAt(i) || p.charAt(j)=='.')){
                    result=helper(s,p,i+1,j,m,n,memo) || helper(s,p,i,j+2,m,n,memo);
                }
                else{
                    result=helper(s,p,i,j+2,m,n,memo);
                }
            }
            else if(i<n && (p.charAt(j)==s.charAt(i) || p.charAt(j)=='.')){
                result=helper(s,p,i+1,j+1,m,n,memo);
            }
            else{
                result=false;
            }
            memo[i][j]=result?1:2;
        }
        return memo[i][j]==1;
    }
    
}
