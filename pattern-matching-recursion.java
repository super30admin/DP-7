/*

Did it run on leetcode: TLE
Time Complexity: Exponential
Space Complexity: Exponential


Algorithm:
- If a star is present then it is always present at index=1 and we have to consider two cases, either
to include it or ignore it. If we get a match from the future then its a match, because current state is matching.
*/


class Solution {
    public boolean isMatch(String s, String p) {
        
        System.out.println("text remaining to be matched:"+s);
        System.out.println("pattern remaining to be matched:"+p);
        
        
        if(p.length()==0){
            return s.length()==0;
        }
        
        boolean match = false;
        
        if(s.length()!=0){
            match = s.charAt(0)==p.charAt(0) || p.charAt(0)=='.';
        }
        
        
        if(p.length()>=2 && p.charAt(1)=='*'){
            
            boolean matchAfterStar = isMatch(s,p.substring(2));
            boolean matchWithStar = match && isMatch(s.substring(1),p);
            
            return matchAfterStar || matchWithStar;
            
        }else{
            return match && isMatch(s.substring(1),p.substring(1));
        }
        
    }
}