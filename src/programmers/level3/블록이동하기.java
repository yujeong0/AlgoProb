package programmers.level3;

import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기 {
    static class Position {
    	int x1, y1, x2, y2, cnt, dir;	// dir0은 가로, dir1은 세로
		public Position(int x1, int y1, int x2, int y2, int cnt, int dir) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
			this.dir = dir;
		}
    }
	
    static int[][] dir = { 
    		// 이동
    		{-1, 0, 0, 0},
    		{0, 1, 0, 0},
    		{1, 0, 0, 0},
    		{0, -1, 0, 0},
    		// 회전
    		// 가로
    		{0, 0, 1, -1},	// x1y1 가만히, 시계방향 회전
    		{0, 0, -1, -1},	// x1y1 가만히, 반시계방향 회전
    		{-1, 1, 0, 0},	// x2y2 가만히, 시계방향 회전
    		{1, 1, 0, 0},	// x2y2 가만히, 반시계방향 회전
    		// 세로
    		{0, 0, -1, -1},	// x1y1 가만히, 시계방향 회전
    		{0, 0, -1, 1},	// x1y1 가만히, 반시계방향 회전
    		{1, 1, 0, 0},	// x2y2 가만히, 시계방향 회전
    		{-1, -1, 0, 0},	// x2y2 가만히, 반시계방향 회전
    };

    static int N;
    static boolean[][][][] visited;
    
	public int solution(int[][] board) {
		N = board.length;
		visited = new boolean[N][N][N][N];
    	Queue<Position> q = new LinkedList<>();
    	q.offer(new Position(0,0,0,1,0, 0));
    	visited[0][0][0][1] = true;
    	
    	Position p;
    	while(!q.isEmpty()) {
    		p = q.poll();
    		
    		int x1, y1, x2, y2, nd;
    		for (int d = 0; d < 12; d++) {
    			if(p.dir == 0 && d >= 8) break;
    			if(p.dir == 1 && d >= 4 && d <= 7) continue;
    			nd = p.dir;
    			if(d >= 4) nd *= -1;
    			
				x1 = p.x1 + dir[d][0];
				y1 = p.y1 + dir[d][1];
				x2 = p.x2 + dir[d][0];
				y2 = p.y2 + dir[d][1];
				
				if(isInBound(x1, x2, y1, y2) && board[x1][y1] == 0 && board[x2][y2] == 0 && !visited[x1][y1][x2][y2]) {
					if(d >= 8) {	//세로 상태에서 가로로 회전할 때
						
					}
					else if(4 <= d && d <= 7) {	// 가로상태에서 세로로 회전할 때
						
					}
					if(x1 == N-1 || y1 == N-1 || x2 == N-1 || y2 == N-1) return p.cnt+1;
					visited[x1][y1][x2][y2] = true;
					q.offer(new Position(x1,y1,x2,y2, p.cnt+1, nd));
				}
			}
    		
    	}
    	
    	return -1;
    }	
	public boolean isInBound(int x1, int x2, int y1, int y2) {
		if(x1 < 0 || x2 < 0 || x1 >= N || x2 >= N || y1 < 0 || y2 < 0 || y1 >= N || y2 >= N)
			return false;
		
		return true;
	}
	public static void main(String[] args) {
		new 블록이동하기().service();
	}
	public void service() {
		System.out.println(solution(new int[][] {
			{0, 0, 0, 1, 1},
			{0, 0, 0, 1, 0},
			{0, 1, 0, 1, 1},
			{1, 1, 0, 0, 1},
			{0, 0, 0, 0, 0}
		}));
	}
}
