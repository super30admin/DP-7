// time complexity is o(m*n)
//space complexity is o(m*n)
// idea is to check if current element is matching with pattern or current index is '.' if yes then check if all privious string matching with pattern .
//in dp update value of current idex with value at diagonal left index.
//then check if current element is '*' then check two cases without that character is string matching the pattern. if yes then mach pattern and also check
// if character before '*' in pattern	 is equal to current input  character if yes then update the value to true and check next characters
public class RegularExpressionMatching {
	//recursion
	    public boolean isMatch(String s, String p) {
	        if(p.isEmpty()) return s.isEmpty();
	        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
	   
	        if(p.length()>1 && p.charAt(1) == '*'){
	            if ((firstMatch && isMatch(s.substring(1),p)) || isMatch(s,p.substring(2))) return true;
	        }else{
	            return firstMatch && isMatch(s.substring(1),p.substring(1));
	        }
	        return false;
	    }
	
	    //dp solution
	        public boolean isMatchDp(String s, String p) {
	            boolean[][] dp = new boolean[s.length()+1][p.length()+1];
	            char[] input = s.toCharArray();
	            char[] pattern = p.toCharArray();
	            dp[0][0]= true;
	            //for case where pattern = a* and input = aa
	            for(int i=1; i<dp[0].length;i++){
	                if(pattern[i-1] == '*'){
	                    dp[0][i] = dp[0][i-2];
	                }
	            }
	            for(int i = 1; i< dp.length; i++){
	                for(int j=1; j< dp[0].length;j++){
	                    if(pattern[j-1] == input[i-1] || pattern[j-1]=='.'){
	                         dp[i][j] = dp[i-1][j-1]; //string is equal to pattern or pattern has '.'
	                    }else if(pattern[j-1] == '*'){
	                        dp[i][j] = dp[i][j-2];   //0 occurance of character 
	                        if(pattern[j-2] == '.' || pattern[j-2] == input[i-1]){
	                            dp[i][j] = dp[i][j] || dp[i-1][j];  // if pattern[j-2]== str[i-1] || pattern[j-2]== '.'
	                        }
	                    }else{
	                        dp[i][j] = false;
	                    }
	                    
	                 
	                }
	            }
	            return dp[s.length()][p.length()];
	        }
	    

}
