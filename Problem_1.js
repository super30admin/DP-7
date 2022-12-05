// Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

// TC: O(mn)
// SC: O(mn)

// 1D
// SC: O(min(m,n))
/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
var minDistance = function (word1, word2) {
    if (word1 === null || word2 === null || word1 === word2)
        return 0;
    let m = word1.length;
    let n = word2.length;
    if (n > m) {
        // Ensure the shorter word is columns
        return minDistance(word2, word1)
    }

    // 1D Array
    let dp = new Array(n + 1);
    for (let i = 0; i < dp.length; i++) {
        dp[i] = i;
    }

    for (let i = 1; i < m + 1; i++) {

        let prev = dp[0];
        dp[0] = i;

        for (let j = 1; j < n + 1; j++) {
            // Is same char, we take diagonal value
            let temp = dp[j];
            if (word1[i - 1] === word2[j - 1]) {
                dp[j] = prev;
            } else {
                let minimum = Math.min(dp[j - 1], dp[j]);
                minimum = Math.min(minimum, prev);
                dp[j] = minimum + 1;
            }
            prev = temp;
        }
    }
    return dp[n]

    // 2D Array
    // let dp = new Array(m+1);
    // for(let i=0; i<dp.length; i++){
    //     dp[i] = new Array(n+1);
    //     dp[i].fill(0);
    // }
    // // Fill 1st column
    // for(let i=0; i<dp.length; i++){
    //     dp[i][0] = i;
    // }
    // // Fill 1st row
    // for(let j=0; j<dp[0].length; j++){
    //     dp[0][j] = j;
    // }

    // for(let i=1; i<dp.length; i++){
    //     for(let j=1;j<dp[0].length; j++){
    //         // Is same char, we take diagonal value
    //         if(word1[i-1] === word2[j-1]){
    //             dp[i][j] = dp[i-1][j-1];
    //         } else {
    //             let minimum = Math.min(dp[i-1][j-1], dp[i][j-1]);
    //             minimum = Math.min(minimum, dp[i-1][j]);
    //             dp[i][j] = minimum+1; 
    //         }

    //     }
    // }
    // return dp[m][n];
};
