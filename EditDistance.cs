using System;
using System.Collections.Generic;
using System.Text;

namespace DynamicProgramming
{
    public class EditDistance
    {
        /*
         * T.C: O(M * N) where M is length of word1 and N is word of length 2
         * S.C: O(M *N) as dp array is of length m *n
         */
        public int MinDistance(string word1, string word2)
        {
            if (word1.Equals(word2)) return 0;

            int n = word1.Length;
            int m = word2.Length;

            int[,] dp = new int[m + 1, n + 1];


            if (m > n) MinDistance(word2, word1);

            for (int i = 0; i < n + 1; i++)
            {
                dp[0, i] = i;
            }

            for (int i = 0; i < m + 1; i++)
            {
                dp[i, 0] = i;
            }

            for (int i = 1; i < m + 1; i++)
            {
                for (int j = 1; j < n + 1; j++)
                {
                    if (word2[i - 1] == word1[j - 1])
                    {
                        dp[i, j] = dp[i - 1, j - 1];
                    }
                    else
                    {
                        dp[i, j] = Math.Min(dp[i - 1, j], Math.Min(dp[i - 1, j - 1], dp[i, j - 1])) + 1;
                    }
                }
            }

            return dp[m, n];
        }


        /*
         * T.C: O(m*n)
         * S.C: O(n)
         * 
         */

        public int MinDistance1(string word1, string word2)
        {
            if (word1.Equals(word2)) return 0;

            int n = word1.Length;
            int m = word2.Length;

            int[] dp = new int[n + 1];


            if (m > n) MinDistance1(word2, word1);

            for (int i = 0; i < n + 1; i++)
            {
                dp[i] = i;
            }


            for (int i = 1; i < m + 1; i++)
            {
                int temp = dp[0];
                dp[0] = i;
                for (int j = 1; j < n + 1; j++)
                {
                    int prev = dp[j];
                    if (word2[i - 1] == word1[j - 1])
                    {
                        dp[j] = temp;
                    }
                    else
                    {
                        dp[j] = Math.Min(dp[j - 1], Math.Min(temp, dp[j])) + 1;
                    }
                    temp = prev;
                }
            }

            return dp[n];
        }
    }
}
