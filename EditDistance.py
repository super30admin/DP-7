class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        """DP Array Implementation
        Time complexity-O(m*n) where m is the length of word2 and n is the length of word1
        Space complexity-O(n) as created dparr"""
        dparr=[i for i in range(len(word1)+1)]
        for i in range(1, len(word2)+1):
            temp=dparr[0]
            dparr[0]=i
            for j in range(1, len(word1)+1):
                temp2=dparr[j]
                if word2[i-1]==word1[j-1]:
                    dparr[j]=temp
                else:
                    dparr[j]=min(dparr[j-1], min(temp, dparr[j]))+1
                temp=temp2
        return dparr[len(word1)]
        """DP Matrix Implementation
        Time complexity-O(m*n) where m is the length of word2 and n is the length of word1
        Space complexity-O(m*n) as created dparr"""
        # dparr=[[0 for _ in range(len(word1)+1)] for _ in range(len(word2)+1)]
        # for i in range(len(word2)+1):
        #     dparr[i][0]=i
        # for j in range(len(word1)+1):
        #     dparr[0][j]=j
        # for i in range(1, len(word2)+1):
        #     for j in range(1, len(word1)+1):
        #         if word2[i-1]==word1[j-1]:
        #             dparr[i][j]=dparr[i-1][j-1]
        #         else:
        #             dparr[i][j]=min(dparr[i-1][j], min(dparr[i-1][j-1], dparr[i][j-1]))+1
        # return dparr[len(word2)][len(word1)]
                