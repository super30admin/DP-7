package DynamicProgramming8;

import java.util.List;

public class Triangle {
    /* Created by palak on 8/16/2021 */

    /**
     Brute Force:
     Maintain a list at every level.
     Start from the last level. We can store the minimim elements at each level.
     But then we can also store the indices instead of elements.

     Bottom Up approch: Take the minimum from the pair and add it to the previous level value
     Until you reach the first element.

     Time complexity: O(n)
     Space Complexity: O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.get(0).size() == 0 || triangle.size() == 0) return 0;
        int n = triangle.size();
        int dp[] = new int[n];
        for(int i = 0 ; i < dp.length ; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        for(int i = n - 2 ; i >= 0 ; i--) {
            //i = triangle.get(i).size()
            for(int j = 0 ; j <= i ; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {

    }
}
