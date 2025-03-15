# tc O(m * n), sc O(m * n).
m, n = len(s), len(p)
        
# preprocessing
if n > 0:
    result = [p[0]]
    for idx in range(1, n):
        if p[idx] == "*":
            if result[-1] == ".":
                result[-1] = "*"
            else:
                result[-1] = result[-1].upper()
        else:
            result.append(p[idx])
    p = "".join(result)

m, n = len(s), len(p)
table = [[False for _ in range(n+1)] for _ in range(2)]
table[0][0] = True
for column in range(1, n+1):
    if p[column-1].islower() or p[column-1] == ".":
        break
    table[0][column] = True

for row in range(1, m+1):
    if row > 1:
        table[0][0] = False
    for column in range(1, n+1):
        if s[row-1] == p[column-1] or p[column-1] == ".":
            table[row%2][column] = table[(row-1)%2][column-1]
        elif p[column-1] == "*":
            table[row%2][column] = table[(row-1)%2][column] or table[row%2][column-1]
        elif p[column-1].isupper():
            if s[row-1] == p[column-1].lower():
                table[row%2][column] = table[(row-1)%2][column] or table[row%2][column-1]
            else:
                table[row%2][column] = table[row%2][column-1]
        else:
            # if p[column-1] is lower case and s[row-1] != p[column-1], its answer remains False.
            table[row%2][column] = False

return table[m%2][n]