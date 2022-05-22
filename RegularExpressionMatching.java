import java.util.Arrays;
//Time Complexity : O(m*n); where m and n are lengths of input strings s and p.
//Space Complexity : O(n)
public class RegularExpressionMatching {    
    /**Approach1: DP Matrix | Time O(m*n) | Space O(m*n)**/	
	/*public boolean isMatch(String s, String p) {
        int m= s.length();
        int n= p.length();        
        boolean[][] dp= new boolean[m+1][n+1];
        dp[0][0] = true;
        //fill top row
        for(int j=1; j<=n; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j]= dp[0][j-2];
            }
        }
        //fill rest dp matrix
        for(int i=1; i<dp.length; i++){        	
            for(int j=1; j<dp[0].length; j++){            	
                //Char is not '*'
                if(p.charAt(j-1) != '*'){
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){ 
                        dp[i][j] = dp[i-1][j-1]; //diagonal up
                    }                    
                }//Char is '*'
                else{
                    //zero case => 2 step back
                    dp[i][j] = dp[i][j-2];
                    
                    //One case => Up                    
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }              
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }*/
	
	/**Approach2: DP Array | Time O(m*n) | Space O(n)**/	
	public boolean isMatch(String s, String p) {
        int m= s.length();
        int n= p.length();        
        boolean[] dp= new boolean[n+1];
        dp[0] = true;
        //fill top row
        for(int j=1; j<=n; j++){
            if(p.charAt(j-1) == '*'){
                dp[j]= dp[j-2];
            }
        }
        //fill each dp row for s characters
        for(int i=1; i<=m; i++){
            boolean diaognal = dp[0];
            for(int j=0; j<dp.length; j++){                
            	if(j==0) {
                    diaognal = dp[j];
            		dp[j] = false;
            		continue;
            	}  
                
            	boolean up = dp[j];                  
                //Char is not '*'
                if(p.charAt(j-1) != '*'){
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){ 
                        dp[j] = diaognal; //diagonal up
                    }else{
                        dp[j] = false;
                    }                    
                }//Char is '*'
                else{
                    //zero case => 2 step back                	
                    dp[j] = dp[j-2];
                    
                    //One case => Up                    
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[j] = dp[j] || up;
                    }
                } 
                diaognal = up;
            }            
        }
        return dp[n];
    }
    
	// Driver code to test above
	public static void main (String[] args) {	
		RegularExpressionMatching ob  = new RegularExpressionMatching();				
		String s= "mississippi";//"aab";
		String p= "mis*is*p*.";//"c*a*b";
		System.out.println("Is regular expression match the string? "+ob.isMatch(s,p));
	}
}
