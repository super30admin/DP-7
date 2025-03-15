# tc O(mn), sc O(n).
word1len, word2len = len(word1), len(word2)
dp = [[0 for _ in range(word2len+1)] for _ in range(2)]
for column in range(word2len+1):
    dp[0][column] = column

for row in range(1, word1len+1):
    dp[row%2][0] = row
    for column in range(1, word2len+1):
        if word1[row-1] == word2[column-1]:
            dp[row%2][column] = dp[(row-1)%2][column-1]
        else:
            dp[row%2][column] = 1 + min(dp[row%2][column-1], dp[(row-1)%2][column-1], dp[(row-1)%2][column])

return dp[word1len%2][word2len]