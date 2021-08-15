class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        """
        TC:O(n^4)
        SC:O((n))
        """
        sett=set()
        for word in wordDict:
            sett.add(word)
        
        dp = [False]*(len(s)+1)
        dp[0]=True
        for i in range(1, len(s)+1):
            for j in range(0,i):
                if dp[j] and s[j:i] in sett:
                    dp[i]=True
                    break
        return dp[-1]
              
        
#         """
#         TC:O(n^n)
#         SC:O(n)
#         """
#         if not s: return False
#         sett=set()
#         for word in wordDict:
#             sett.add(word)
#         return self.helper(s, sett)
    
#     def helper(self, s, sett):
#         # base
#         if not s: return True
        
#         # logic
#         for i in range(1,len(s)+1):
#             if s[0:i] in sett and self.helper(s[i:], sett):
#                 return True
              
        
#         dp=[0]*len(s)
        
#         for i in range(len(dp)):
#             for j in range(i):
#                 w2check=s[j:i+1]
#                 if w2check in wordDict:
#                     if j>0:
#                         dp[i]+=dp[j-1]
#                     else:
#                         dp[i]+=1
        
#         print(dp)
#         return dp[len(dp)-1]>0
    
