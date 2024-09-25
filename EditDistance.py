#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Oct 17 16:16:21 2019

@author: tanvirkaur
"""

class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        time complexity = O(nXm)
        Space complexity = 0(n+m)
        """
        m = len(word1)
        n = len(word2)
        dp  = [[0 for _ in range(n+1)] for _ in range(m+1)]
        for i in range(m+1):
            dp[i][0] = i
        for i in range(n+1):
            dp[0][i] = i
        for i in range(m):
            for j in range(n):
                if word1[i] == word2[j]: #eg. (ho = ro) last i and j are same so the number of operations needed = prev no. of operations 
                    dp[i+1][j+1] = dp[i][j]
                else:  
                    dp[i+1][j+1] = min(dp[i+1][j], dp[i][j+1] ,dp[i][j]) + 1
        return dp[-1][-1]