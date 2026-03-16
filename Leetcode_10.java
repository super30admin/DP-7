//DP with memoization
//Use memo array to avoid repetetions
//Logic: check s string char at ith index and p string char at jth index. 
//Check if j+1th index is * or not. If * we have 2 options. Choose and No choose. If we don't choose, make j to j+2. If we choose, make j to j and i to i+1
//If j+1th index is not *, make regular call i.e., i-> i+1 and j to j+1
//TC: O(m)*O(n)
//SC: O(m)*O(n)
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
       // this.memo=new Boolean[s.length()+1][p.length()+1];
       this.memo = new Boolean[s.length() + 1][p.length() + 1];
        return  match(s,p,0,0);
         
        
    }

    private boolean match(String s, String p, int i, int j){
        //base case
        if(i==s.length() && j==p.length()){
            return true;
           // return;
        }

        if(j==p.length()){
            return false;
        } 

        if(memo[i][j]!=null) return memo[i][j];

        //logic
        
        boolean firstMatch=(i<s.length()) && ((s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'));

        boolean result=false;

        if((j+1)<p.length() && p.charAt(j+1)=='*'){
            result=match(s,p,i,j+2) || (firstMatch && match(s,p,i+1,j)) ;           
        }
        else{
            if (firstMatch) {
                result = match(s, p, i + 1, j + 1);
            }
        }

        memo[i][j]=result;
        return result;
    }
}
