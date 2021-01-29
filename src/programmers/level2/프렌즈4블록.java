package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 프렌즈4블록 {
	char[][] map;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        M = m;
        N = n;
        map = new char[m][];
        for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
		}
        
        while(true) {
        	int cnt = remove();
        	if(cnt == 0) return answer;
        	answer += cnt;
        	move();
        }
        
    }
    
    int M,N;
    List<int[]> pos;
    int remove() {
    	pos = new ArrayList<>();
    	for (int i = 0; i < M-1; i++) {
			for (int j = 0; j < N-1; j++) {
				if(map[i][j] == '*') continue;
				if(map[i][j] == map[i+1][j] && map[i][j] == map[i+1][j+1] && map[i][j] == map[i][j+1]) {
					pos.add(new int[] {i,j});
					pos.add(new int[] {i+1,j});
					pos.add(new int[] {i,j+1});
					pos.add(new int[] {i+1,j+1});
				}
			}
		}
    	
    	int cnt = 0;
    	for (int[] p : pos) {
    		if(map[p[0]][p[1]] == '-') continue;
			map[p[0]][p[1]] = '-';
    		cnt++;
    	}
    	
    	return cnt;
    }
    
    void move() {
    	for (int j = 0; j < N; j++) {
    		for (int i = M-1; i >= 0; i--) {
    			if(map[i][j] != '-') continue;
    			int cnt = 0;
    			if(map[i][j] == '-') {
    				cnt++;
    				int idx = i-1;
    				while(true) {
    					if(idx < 0) break;
    					if(map[idx--][j] != '-') break;
    					cnt++;
    				}
    				idx = i;
					while(true) {
						if(idx-cnt < 0) {
							while(idx >= 0) {
								map[idx--][j] = '*';
							}
							break;
						}
						map[idx][j] = map[idx-cnt][j];
						idx--;
					}
    			}
			}
		}
    } // move
    
    public static void main(String[] args) {
		System.out.println(new 프렌즈4블록().solution(4, 5, new String[] {"CCBDE", "AAADE", "AAABF", "CCBBF"}));
	}
}
