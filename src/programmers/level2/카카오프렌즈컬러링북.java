package programmers.level2;

import java.util.Arrays;

public class 카카오프렌즈컬러링북 {
    public int[] solution(int m, int n, int[][] picture) {
    	M = m;
    	N = n;
        int numberOfArea = 0;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if(!visited[i][j] && picture[i][j] != 0) {
        			numberOfArea++;
        			cnt = 1;
        			visited[i][j] = true;
        			dfs(i, j, picture);
        	    	if(MAX < cnt) MAX = cnt;
        		}
			}
		}
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = MAX;
        return answer;
    }
    
    boolean[][] visited;
    int MAX = 0, M, N, cnt;
    int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
    void dfs(int x, int y, int[][] picture) {
    	
    	int nx, ny;
    	for (int d = 0; d < 4; d++) {
			nx = x + dir[d][0];
			ny = y + dir[d][1];
			if(nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[nx][ny] && picture[nx][ny] == picture[x][y]) {
				visited[nx][ny] = true;
				cnt++;
				dfs(nx, ny, picture);
			}
    	}
    }
    public static void main(String[] args) {
		new 카카오프렌즈컬러링북().service();
	}
    void service() {
    	System.out.println(Arrays.toString(solution(6, 4, 
    			new int[][] { 	{1, 1, 1, 0}, 
    							{1, 2, 2, 0}, 
    							{1, 0, 0, 1}, 
    							{0, 0, 0, 1}, 
    							{0, 0, 0, 3}, 
    							{0, 0, 0, 3} })));
    }
}
