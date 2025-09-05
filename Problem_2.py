'''
10 Regular Expression Matching
https://leetcode.com/problems/regular-expression-matching/description/

Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Constraints:
1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.

Solution:
1. Recursion
https://youtu.be/o0pYLrKMJpw?t=330
Time: O(exponential), Space: O(exponential)

2. Tabulation (bottom-up approach)
We use DP tabulation to track if there is match between the string s and pattern p.

Let M = size(s) , N = size(p).

We create a DP table of size (M+1) x (N+1). The row and col sizes are 1 more than the length of the s/p because we prepend s and p by an empty string "".
Thus, if s = "aab" and p = "c*a*b", then the DP table is:

    j =  0  1   2   3   4   5
 i       "  c   *   a   *   b
 0  "    T  F   T   F   T   F
 1  a    F  F   F   T   T   F
 2  a    F  F   F   F   T   F
 3  b    F  F   F   F   F   T


Define dp[i][j] = is there a match between string[0...i-1] and pattern[0...j-1]
In Python notation, string[0:i] -> [0:j]

For example, dp[1][3] = match(s[:1], p[:3]) = match("a, "c*a)

We discuss the 3 possible cases - A, B0, B1
Case A: curr chars of s and p match (s[i-1] == p[j-1])
For dp[1][3], the curr chars of s and p are s[0] (a) and p[2] (a).
Since they match, we are left with match(","c*). This is already available in
dp[0][2].

In general,
if s[i-1] == p[j-1]:
   dp[i][j] = dp[i-1][j-1] (top left diagonal)

If p[j-1] = ".", then it can match any char present in s[i-1]
Hence, we modify the original condition as:
if s[i-1] == p[j-1] or p[j-1] == ".":
   dp[i][j] = dp[i-1][j-1] (top left diagonal)

Case B: curr chars of s and p don't match (s[i-1] != p[j-1])
The mismatch might be due to
a) p[j-1] = * and s[i-1] = letter
or
b) p[j-1] = letter and s[i-1] = some other letter

In case of (b), a mismatch is guaranteed. Hence,
dp[i][j] = match(s[:i], p[:j]) = False
For eg, dp[1][5] = match(s[:1], p[:5])
                 = match("a, c*a*b)
                 = False (since the last chars dont match, a != b)

In case of (a), p[j-1] = *, we consider two possibilities
Case 0:  * matches 0 instances of preceding char
Case 1:  * matches >= 1 instances of preceding char

Case 0:
From the pattern, remove the * and the preceding char  and match the remaining subpattern with the substring
eg., dp[0][2] = match(","c*)
              = match(",")  (remove c* from "c* -> pattern becomes ")
              = dp[0][0]
              = True

     dp[1][2] = match("a,"c*)
              = match("a,")
              = dp[1][0]
              = False

     dp[2][4] = match("aa, "c*a*)
              = match("aa, "c*)
              = dp[2][2]

     In general,
     if p[j-1] == *:
        case_0 = dp[i][j-2]

Case 1:
Check if the preceding char (prior to *) of the pattern matches the last char of the substring
eg., dp[2][4] = match("aa, "c*a*) (last char f substring "aa = a, char prior to
                                  * in pattern "c*a* = a)
Since this is Case 1, assume a* expands 1 or 2 or 3 instances of 'a'
Thus, c*a* = c*a|c*aa|c*aaa
           = c* '' a | c* a a| c* aa a
           = c* a* a | c* a* a| c* a* a
           = c*a*a
Thus, match("aa, "c*a*) = match("aa, "c*a*a)
                        = match("a, "c*a*)  (removing the last 'a' from both)
                        = dp[1][4]

Thus, dp[2][4] = match("aa, "c*a*)
               = match("a, "c*a*)
               = dp[1][4]


Case 0 always happens (eg. dp[0][2], dp[1][2], dp[2][4])
Case 1 only happens when char prior to * in pattern matches the last char in substring (eg. dp[2][4])

If the conditions for Case 1 is satisified, then
dp[i][j] = Case 0 || Case 1
eg. dp[2][4] = dp[2][2]  || dp[1][4]

In general,
if p[j-1] == "*":
    case_0 = dp[i][j-2]

    if s[i-1] == p[j-2] or p[j-2] == ".":
        case_1 = dp[i-1][j]

    dp[i][j] = case_0 or case_1

We fill the dp matrix to check if a substring matches a subpattern using the rules described in A, B0, B1.
https://youtu.be/o0pYLrKMJpw?t=588
Time: O(MN), Space: O(MN)

'''
from pprint import pprint

def isMatch(s: str, p: str) -> bool:
    if s == p:
        return True

    M, N = len(s), len(p)
    dp = [[False]*(N+1) for _ in range(M+1)]
    # set first column cells as follows:
    # dp[0][0] = True
    # dp[*>0][0] = False
    dp[0][0] = True

    # fill the 1st row (row index = 0)
    for j in range(1, N+1):
        if p[j-1] == "*":
            dp[0][j] = dp[0][j-2]
        else: # match (letter or ., "") = False
            dp[0][j] = False # False during init but do it anyways for clarity

    # fill all subsequent rows (row index > 0)
    for i in range(1, M+1):
        for j in range(1, N+1):
            # if the curr chars of s and p match
            if s[i-1] == p[j-1] or p[j-1] == ".":
                dp[i][j] = dp[i-1][j-1]
            else: # dont match
                if p[j-1] == "*":
                    # 0-case
                    case_0 = dp[i][j-2]
                    case_1 = False # dummy
                    if s[i-1] == p[j-2] or p[j-2] == ".":
                        case_1 = dp[i-1][j]
                    dp[i][j] = case_0 or case_1
                else:
                    dp[i][j] = False

    # return the entire dp matrix (instead of last element dp[M][N]) for sanity checking
    return dp

def run_isMatch():
    tests = [("aab", "c*a*b",
              [[True, False, True, False, True, False],
               [False, False, False, True, True, False],
               [False, False, False, False, True, False],
               [False, False, False, False, False, True]]
             ),
             ("aab", "c*.*b",
              [[True, False, True, False, True, False],
               [False, False, False, True, True, False],
               [False, False, False, False, True, False],
               [False, False, False, False, True, True]]
             ),
             ("aa", "a",
              [[True, False],
               [False, True],
               [False, False]]
             ),
             ("aa", "a*",
              [[True, False, True],
               [False, True, True],
               [False, False, True]]
             ),
             ("ab", ".*",
              [[True, False, True],
               [False, True, True],
               [False, False, True]]
             ),
    ]
    for test in tests:
        s, p, ans = test[0], test[1], test[2]
        dp = isMatch(s,p)
        print(f"\nstring = {s}")
        print(f"pattern = {p}")
        print(f"Regex match = {dp[-1][-1]}")
        print(f"DP:")
        pprint(dp)
        success = (ans == dp)
        print(f"Pass: {success}")
        if not success:
            print(f"Failed")
            return

run_isMatch()