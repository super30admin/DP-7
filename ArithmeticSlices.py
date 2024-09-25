#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Oct 18 10:18:38 2019

@author: tanvirkaur
"""

# time complexity = O(n)
# space complexity = O(n)
# the sequence must contain atleat three elements therefore we start iterating from the third element till the last element and store the corresponding values in the tmp array.
# it is dp because we are using the prev values to update the new values.
class Solution:
    def numberOfArithmeticSlices(self, A: List[int]) -> int:
        tmp = [0]*len(A)
        for i in range(2,len(A)):
            if A[i]-A[i-1] == A[i-1]-A[i-2]:
                tmp[i] = tmp[i-1]+1
        return sum(tmp)