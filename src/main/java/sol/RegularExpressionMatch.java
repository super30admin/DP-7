package sol;


public class RegularExpressionMatch {

	 public boolean isMatch(String s, String p) {
		    
	        boolean dp[][]= new boolean[s.length()+1][p.length()+1];
	        
	     dp[0][0]=true;
	            
	        
	         for( int i=1;i<dp[0].length;i++){
	            
	             if(p.charAt(i-1)=='*'){dp[0][i]=dp[0][i-2];}
	            
	            
	            
	        }
	            
	        for( int i=1;i<s.length()+1;i++){
	            
	            for( int j=1;j<p.length()+1;j++){
	                
	                char r=p.charAt(j-1);
	                char c=s.charAt(i-1);
	                
	                
	                if(r==c||r=='.'){dp[i][j]=dp[i-1][j-1];}
	                else if(r=='*'){
	                    boolean not_taken=dp[i][j-2];
	                    boolean taken=false;
	                    if((p.charAt(j-2)==c||p.charAt(j-2)=='.')&&dp[i-1][j]){
	                        
	                        taken=true;
	                    }
	                    dp[i][j]=taken||not_taken;
	                    }
	                
	            }
	        }
	        
	        
	         
	        
	        return dp[dp.length-1][dp[0].length-1];
	            
	            
	        
	        
	    }
	    
	
}
