// TC : O(mn)
// SC : O(n)

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1==null || word2==null || word1.equals(word2)) return 0;
        int m=word1.length();
        int n= word2.length();
        int[] dp=new int[n+1];

        if(m>n){
            return minDistance(word2,word1);
        }

        for(int i=1;i<n+1;i++){
            dp[i] = i;
        }



        for(int i=0;i<m;i++){
            int prev=i;
            dp[0] = i;
            for(int j=1;j<n+1;j++){
                int temp=dp[j];
                if(word1.charAt(i)==word2.charAt(j-1)){
                    dp[j]= prev;
                }else{
                    dp[j] = 1 +  Math.min(prev, Math.min(dp[j],dp[j-1]));
                }
                prev=temp;
            }

        }
        return dp[n];
    }
}