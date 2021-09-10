//T.C:O(M*N);
//S.C:O(1);
//top Down Approach:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        if(triangle == null || triangle.size() == 0){
            return -1;
        }

        for(int i=1;i<triangle.size();i++){
            for(int j=0;j<triangle.get(i).size();j++){
                if(j == 0){
                    triangle.get(i).set(j,triangle.get(i).get(j)+triangle.get(i-1).get(j));
                }
                else if(j == i){
                    triangle.get(i).set(j,triangle.get(i).get(j)+triangle.get(i-1).get(j-1));
                }else{
                    triangle.get(i).set(j,triangle.get(i).get(j) + Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j)));
                }

            }
        }

        for(int i=0;i<triangle.size();i++){

            min = Math.min(min,triangle.get(triangle.size()-1).get(i));

        }
        return min;
    }
}

//T.C:O(M*N); M is size of list , N is no of element in each list
//S.C:O(1);
//Bottom up Approach:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int n = triangle.size();
        if(triangle == null || triangle.size() == 0){
            return -1;
        }

        for(int i=n-2;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                triangle.get(i).set(j,triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1)));
            }
        }

        return triangle.get(0).get(0);
    }
}

//T.C:O(M*N); M is size of list , N is no of element in each list
//S.C:O(M);
//Extra Space Bottom up Approach:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int n = triangle.size();
        if(triangle == null || triangle.size() == 0){
            return -1;
        }

        int[] minPathSum = new int[n];

        for(int i=0;i<minPathSum.length;i++){
            minPathSum[i] = triangle.get(n-1).get(i);
        }

        for(int i=n-2;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                minPathSum[j]=triangle.get(i).get(j) + Math.min(minPathSum[j],minPathSum[j+1]);
            }
        }

        return minPathSum[0];
    }
}