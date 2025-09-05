'''
72 Edit Distance
https://leetcode.com/problems/edit-distance/description/

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Solution:
1. Tabulation (bottom-up approach)
We use DP tabulation to track how many edits we need to make two strings the same. Let M = size(word1) , N = size(word2).
We create a DP table of size (M+1) x (N+1). The row and col sizes are 1 more than the length of the strings because we prepend word1 and word2 by an empty string "". Thus, if word1 = "horse" and word2 = "ros", then the DP table is:

    j =  0  1   2   3
 i       "  r   o   s
 0  "
 1  h
 2  o
 3  r
 4  s
 5  e
Define dp[i][j] = min no. of edits to convert word1[0...i-1] -> word2[0...j-1]
In Python notation, word1[0:i] -> word2[0:j]

For example, dp[1][2] = min no. of edits to convert `"h' to `"ro' (word1[0:1] -> word2[0:2])

This can be done in 3 ways:
a) Substitute:
    Step 1: h -> o (always replace by the last char of target string). Thus, num_edits = 1
    Step 2: Now we are left with converting o -> ro which is the same thing
            as  ""o -> ro which is the same thing as ""->r  (converting o->o doesn't need any edit)
    Step 3: To get the no. of edits for ""->r, see dp[0][1]
    Step 4: cost for substitute = num_edits + dp[0][1] = 1 + dp[0][1]

b) Insert:
    Step 1: h -> ho (always add the last char of target string). Thus, num_edits = 1
    Step 2: Now we are left with converting ho -> ro which is the same thing
            as  h -> r.
    Step 3: To get the no. of edits for h->r, see dp[1][1]
    Step 4: cost for insert = num_edits + dp[1][1] = 1 + dp[1][1]

c) Delete:
    Step 1: h -> "" (deletion removes the character). Thus, num_edits = 1
    Step 2: Now we are left with converting "" -> ro.
    Step 3: To get the no. of edits for ""->ro, see dp[0][2]
    Step 4: cost for delete = num_edits + dp[0][2] = 1 + dp[0][2]

Finally, dp[1][2] = min(cost sub, cost ins, cost del)
                  = min(1 + dp[0][1], 1 + dp[1][1], 1 + dp[0][2])
                  = 1 + min(dp[0][1], dp[1][1], dp[0][2])

In general,
dp[i][j] = 1 + min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j])

Note that if the current characters of word1 and word2 match (word[i-1] == word[j-1]), then there is no edit reqd for the curr char. So, we just try to convert the subwords (start to the prev char) of each word. That is, word1[0:i-1] vs word2[0:j-1]. But this is simply dp[i-1][j-1]

If the current characters do not match (word[i-1] != word[j-1]), we explore substitution, insertion, deletion.

The value of the dp matrix at the bottom-right (dp[-1][-1]) tells us the minimum number of operations.

https://youtu.be/o0pYLrKMJpw?t=3511
Time: O(MN), Space: O(MN) (space can be further optimized to O(N), store only 1 row 1xN in DP instead of a MxN matrix)

2. Tabulation w/ backtracking
With backtracking, we are able to trace back all edit operations that resulted in going from word1 to word2. We do this by maintaining an array for backtracking. Otherwise, this solution is almost identical to the Tabulation approach in #1.
https://youtu.be/o0pYLrKMJpw?t=5197
Time: O(MN), Space: O(MN)
'''
def minDistance(word1: str, word2: str) -> int:
    if word1 == word2:
        return 0
    elif not word1:
        return len(word2)
    elif not word2:
        return len(word1)

    M, N = len(word1), len(word2)
    # arrange chars of source string word1 vertically (along row)
    # arrange chars of destn string word2 horizontally (along col)
    dp = [[0]*(N+1) for _ in range(M+1)]

    for j in range(N+1):
        dp[0][j] = j

    for i in range(M+1):
        dp[i][0] = i

    for i in range(1, M+1):
        for j in range(1, N+1):
            if word1[i-1] == word2[j-1]:
                dp[i][j] = dp[i-1][j-1]
            else:
                replace = 1 + dp[i-1][j-1]
                add =     1 + dp[i][j-1]
                delete =  1 + dp[i-1][j]
                dp[i][j] = min(replace, add, delete)
    return dp[M][N]

def minDistanceBacktrack(word1: str, word2: str) -> int:
    if word1 == word2:
        return 0
    elif not word1:
        return len(word2)
    elif not word2:
        return len(word1)

    M, N = len(word1), len(word2)
    # arrange chars of source string word1 vertically (along row)
    # arrange chars of destn string word2 horizontally (along col)
    dp = [[0]*(N+1) for _ in range(M+1)]

    for j in range(N+1):
        dp[0][j] = j

    for i in range(M+1):
        dp[i][0] = i

    backtrack = []
    for i in range(1, M+1):
        for j in range(1, N+1):
            if word1[i-1] == word2[j-1]:
                dp[i][j] = dp[i-1][j-1]
                backtrack.append([(i, j),(i-1, j-1),'N'])
            else:
                replace = 1 + dp[i-1][j-1]
                add =     1 + dp[i][j-1]
                delete =  1 + dp[i-1][j]
                operations = [replace, add, delete]
                dp[i][j] = min(operations)

                min_index = operations.index(dp[i][j])
                if min_index == 0:
                    backtrack.append([(i, j), (i-1, j-1), 'R'])
                elif min_index == 1:
                    backtrack.append([(i, j), (i, j-1), 'A'])
                elif min_index == 2:
                    backtrack.append([(i, j), (i-1, j), 'D'])


    return dp[M][N], backtrack

def processBacktrack(backtrack, word1, word2):
    M, N = len(word1), len(word2)
    idx = len(backtrack)-1
    word = word1
    print(f"Start Backtracking ... ")
    print(f"source string = {word1}")
    for _ in range(M):
        curr_idx, src_idx, op = backtrack[idx]

        # Perform string operation on curr index (i,j) in DP matrix
        i, j =  curr_idx[0]-1, curr_idx[1]-1
        if op == "D":
            word = word[:i] + word[i+1:]
            print(f"index = {i}, char {word1[i]}: Delete {word1[i]} -> {word}")
        elif op == 'N':
            print(f"index = {i}, char {word1[i]}: Do Nothing -> {word}")
        elif op == 'R':
            word = word[:i] + word2[j] + word[i+1:]
            print(f"index = {i}, char {word1[i]}: Replace {word1[i]} by {word2[j]} -> {word}")

        # Move to the source index in DP matrix
        row, col = src_idx
        # convert 2d index of DP matrix to 1d index of backtrack array
        idx = (row - 1)*N + (col - 1)

    assert word == word2, f"Backtracking failed"
    return True

def run_minDistance():
    tests = [("horse", "ros", 3),
             ("intention", "execution", 5),
    ]
    for test in tests:
        word1, word2, ans = test[0], test[1], test[2]
        for method in ['edit_distance', 'edit_distance_with_backtrack']:
            if method == 'edit_distance':
                dist = minDistance(word1, word2)
            elif method == 'edit_distance_with_backtrack':
                dist, backtrack = minDistanceBacktrack(word1, word2)
            print(f"\nsource string = {word1}")
            print(f"target string = {word2}")
            print(f"Method {method}: Edit Distance = {dist}")
            success = (ans == dist)
            print(f"Pass: {success}")
            if method == 'edit_distance_with_backtrack':
                processBacktrack(backtrack, word1, word2)
            if not success:
                print(f"Failed")
                return

run_minDistance()