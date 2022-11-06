/*
Time Complexity: O(TP)
Space Complexity: O(TP)
*/
class Solution {
    Map<String, Boolean> dp;
    public boolean isMatch(String s, String p) {
        dp=new HashMap<>();
        return util(s, p, 0, 0);
    }
    
    public boolean util(String s, String p, int i, int j) {
        if(i>=s.length() && j>=p.length()) return true; 
        else if(i<s.length() && j>=p.length()) return false; 
        else if(i>=s.length() && j<p.length()) { 
            if(j+1<p.length() && p.charAt(j+1)=='*') {
                return util(s,p,i,j+2);
            }else {
                return false;
            }
        }else {
            String key=i+"|"+j;
            
            if(dp.containsKey(key)) return dp.get(key);
            
            boolean ret;
            
            if(j+1<p.length() && p.charAt(j+1)=='*') { 
                if(s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.') {
                    ret=util(s,p,i,j+2); 
                }else {
                    ret=util(s, p, i, j+2) || util(s, p, i+1, j);
                }
            }else {
                if(s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.') {
                    ret=false;
                }
                else {
                    ret=util(s, p, i+1, j+1);
                }
            }
            
            dp.put(key, ret);
            return ret;
        }
    }
}