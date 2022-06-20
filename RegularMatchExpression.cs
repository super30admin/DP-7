using System;
using System.Collections.Generic;
using System.Text;

namespace DynamicProgramming
{
    /*
     * T.C: O(m*N) since we are traversing both the strings
     * S.C: O(m*n) since Dp contains m * n elements
     */
    public class RegularMatchExpression
    {
        public bool IsMatch(string s, string p)
        {

            int m = s.Length + 1;
            int n = p.Length + 1;

            bool[,] dp = new bool[m, n];
            dp[0, 0] = true;

            for (int i = 1; i < n; i++)
            {
                if (p[i - 1] == '*')
                {
                    dp[0, i] = dp[0, i - 2];
                }
            }


            for (int i = 1; i < m; i++)
            {
                for (int j = 1; j < n; j++)
                {
                    if (p[j - 1] != '*')
                    {
                        if (p[j - 1] == s[i - 1] || p[j - 1] == '.')
                        {
                            dp[i, j] = dp[i - 1, j - 1];
                        }
                    }
                    else
                    {
                        dp[i, j] = dp[i, j - 2];

                        if (p[j - 2] == s[i - 1] || p[j - 2] == '.')
                        {
                            dp[i, j] = dp[i, j] || dp[i - 1, j];
                        }
                    }

                }
            }

            return dp[m - 1, n - 1];

        }
    }
}
