package programmers.level3;

public class 정수삼각형 {
    int MAX = 0;
    int N;
	public int solution(int[][] triangle) {
		N = triangle.length;
		if(N == 1) return triangle[0][0];
		
		int[] arr;
    	for (int i = N-1; i >= 1; i--) {
    		arr = new int[triangle[i-1].length];
    		for (int j = 0; j < triangle[i].length; j++) {
				if(j-1 >= 0) {
					arr[j-1] = Math.max(arr[j-1], triangle[i][j] + triangle[i-1][j-1]);
				}
				if(j < triangle[i-1].length) {
					arr[j] = Math.max(arr[j], triangle[i][j] + triangle[i-1][j]);
				}
			}
    		for (int j = 0; j < arr.length; j++) {
				triangle[i-1][j] = arr[j];
			}
    	}
    	
        return triangle[0][0];
    }
    public static void main(String[] args) {
		new 정수삼각형().service();
	}
    public void service() {
    	System.out.println(solution(new int[][] {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}}));
    }
}
