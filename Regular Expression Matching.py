class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        def ism(i,j):
            if j==len(p):
                return i==len(s)
            # base case
            if i<len(s):
                first_char_match=p[j] in (s[i],'.')
            else:
                first_char_match=False
            if j+1<len(p) and p[j+1]=='*':
                # if we have a * we can have a zero or more occurence of the same charachter
                zero_occr=ism(i,j+2)
                # if first charachter matched previously we can have repeats
                if first_char_match:
                    repeat=ism(i+1,j)
                    return zero_occr or repeat
                return zero_occr
            # no * but the first charachter matched we can move ahead and check the rest of the string
            elif first_char_match:
                return ism(i+1,j+1)
            return False