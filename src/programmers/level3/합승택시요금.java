package programmers.level3;

public class 합승택시요금 {
    static final int INFINITY = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
    	int[][] adjMatrix = new int[n+1][n+1];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			if(i == j) adjMatrix[i][j] = 0;
    			else adjMatrix[i][j] = INFINITY;
    		}
    	}
    	
    	for (int i = 0; i < fares.length; i++) {
    		adjMatrix[fares[i][0]][fares[i][1]] = fares[i][2];
    		adjMatrix[fares[i][1]][fares[i][0]] = fares[i][2];
    	}

    	for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if(i == j || i == k || j == k) continue;
					if(adjMatrix[j][i] == INFINITY || adjMatrix[i][k] == INFINITY) continue;
					adjMatrix[j][k] = Math.min(adjMatrix[j][k], adjMatrix[j][i] + adjMatrix[i][k]);
				}
			}
		}
    	
		int answer = INFINITY;
		answer = Math.min(answer, adjMatrix[s][a] + adjMatrix[s][b]);
		answer = Math.min(answer, adjMatrix[s][a] + adjMatrix[a][b]);
		answer = Math.min(answer, adjMatrix[s][b] + adjMatrix[b][a]);
    	for (int i = 1; i <= n; i++) {
    		if(i == s) continue;
    		answer = Math.min(answer, adjMatrix[s][i] + adjMatrix[i][a] + adjMatrix[i][b]);
    	}
    	
        return answer;
    }
    public static void main(String[] args) {
		System.out.println(
			new 합승택시요금().solution(6, 4, 6, 2, 
						new int[][] {
					{4, 1, 10}, 
					{3, 5, 24}, 
					{5, 6, 2}, 
					{3, 1, 41}, 
					{5, 1, 24}, 
					{4, 6, 50}, 
					{2, 4, 66}, 
					{2, 3, 22}, 
					{1, 6, 25}
				})
		);
    }
}
