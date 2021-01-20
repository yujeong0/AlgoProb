package programmers.level2;

public class 행렬의곱셈 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for (int i = 0; i < arr1.length; i++) {
        	for (int t = 0; t < arr2[0].length; t++) {
        		int sum = 0;
        		for (int j = 0; j < arr1[0].length; j++) {
        			sum += arr1[i][j] * arr2[j][t];
				}
        		answer[i][t] = sum;
			}
        }
        
        return answer;
    }
    public static void main(String[] args) {
//    	int[][] arr = new 행렬의곱셈().solution(
//    			new int[][] {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}}, new int[][] {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}});
    	int[][] arr = new 행렬의곱셈().solution(new int[][] {{1, 4}, {3, 2}, {4, 1}}, new int[][] {{3, 3}, {3, 3}});
    	for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
    }
}
