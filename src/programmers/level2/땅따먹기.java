package programmers.level2;

public class 땅따먹기 {
    int solution(int[][] land) {
    	int N = land.length;
    	
    	int[][] score = new int[N][4];
    	for (int j = 0; j < 4; j++) {		
			score[0][j] = land[0][j];
		}
    	
    	for (int i = 1; i < land.length; i++) {
    		for (int j = 0; j < 4; j++) {
    			for (int j2 = 0; j2 < 4; j2++) {
    				if(j == j2) continue;
    				score[i][j] = Math.max(score[i][j], score[i-1][j2] + land[i][j]);
				}
    		}
		}
    	
    	int max = 0;
    	for (int j = 0; j < 4; j++) {
			if(max < score[N-1][j]) max = score[N-1][j];
		}
		return max;
	}
    

    
    public static void main(String[] args) {
    	System.out.println(new 땅따먹기().solution(new int[][] {{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
	}
}
