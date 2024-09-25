#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Oct 18 10:19:14 2019

@author: tanvirkaur
"""

# time complexity = O(n), n is the number of elements the triangle
# space complexity = O(n)
# we are moving  from the second last row to the 0th row and returning the value of top of the triangle
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        A = len(triangle)
        for i in range(A-2,-1,-1):
            for j in range(0,i+1,+1):
                triangle[i][j] = triangle[i][j] + min(triangle[i+1][j], triangle[i+1][j+1])
        return triangle[0][0]
             