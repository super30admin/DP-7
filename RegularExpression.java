
//Time O(M.N)
//Space O(M.N)

public class RegularExpression {
	 Boolean[][] dp;
	    public boolean isMatch(String s, String p) {
	        dp = new Boolean[s.length()+1][p.length()];
	        return isMatch(0,s,0,p);
	    }
	    private boolean isMatch(int i, String s, int j, String p) { 
	        int sn = s.length(), pn = p.length();
	        if(j==pn) { // since * in p can match 0 of previous char, so empty string(i==sn) may match p
	            return i==sn;    
	        }
	        if(dp[i][j]!=null) {
	            return dp[i][j];
	        }
	        char pj = p.charAt(j);
	        if(j+1<pn && p.charAt(j+1)=='*') { //match *, needs to look at the next char to repeat current char
	            if(isMatch(i,s,j+2,p)) { // case when you pick 0 char before * to match
	                return dp[i][j]=true;
	            }
	            if(i<sn && (pj == '.'||pj==s.charAt(i))) { // keep match s char at i with char before * in string p 
	                if(isMatch(i+1,s,j,p)) {
	                    return dp[i][j]=true;
	                }
	            }
	        } else if(i<sn && (s.charAt(i) == pj ||    //match char
	                   pj=='.')) {              //match dot
	            return dp[i][j]=isMatch(i+1, s, j+1, p);
	        }
	        return dp[i][j]=false;
	    }
}
