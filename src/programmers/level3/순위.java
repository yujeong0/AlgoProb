package programmers.level3;

import java.util.ArrayList;
import java.util.List;

public class 순위 {
    public int solution(int n, int[][] results) {
    	int[][] adjMatrix = new int [n+1][n+1];
    	for (int i = 0; i < results.length; i++) {
			adjMatrix[results[i][0]][results[i][1]] = 1;
			adjMatrix[results[i][1]][results[i][0]] = 2;
		}
    	
    	int answer = 0;
    	List<Integer> complete = new ArrayList<>();
    	for (int i = 1; i <= n; i++) {
    		int zeroCnt = 0;
    		for (int j = 1; j <= n; j++) {
				if(i == j) continue;
				if(adjMatrix[i][j] == 0) zeroCnt++;
			}
    		if(zeroCnt == 1) {
    			answer++;
    			complete.add(i);
    		}
    	}
    	
    	for (int i = 1; i <= n; i++) {
    		boolean con = false;
    		for(int num : complete) {
    			if(num == i) {
    				con = true;
    				break;
    			}
    		}
    		if(con) continue;
    		
    		for(int num : complete) {
    			if(adjMatrix[i][num] != 0) {
    				int lose =  0;
    				for (int j = 1; j <= n; j++) {
						if(adjMatrix[num][j] == 2) lose++;
					}
    				if(adjMatrix[i][num] == 2 && n-1-lose == 1) {
    					answer++;
    				}
    				if(adjMatrix[i][num] == 1 && lose == n-2) {
    					answer++;
    				}
    			}
    		}
    	}
    	
        return answer;
    }
    
    public static void main(String[] args) {
		new 순위().service();
	}
    public void service() {
    	System.out.println(solution(5, new int[][] {{4,3},{4,2},{3,2},{1,2},{2,5}}));
    }
}
