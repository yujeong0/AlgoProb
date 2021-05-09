package summercoding2021;

import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {
    public int solution(int[][] maps, int p, int r) {
        int answer = 0;
        N = maps.length;
        while(r > 1) {
        	r /= 2;
        	distance++;
        }
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int sum = solve(i+attack[d][0], j+attack[d][1], d, maps, p);
					if(answer < sum) answer = sum;
				}
			}
		}
        
        return answer;
    }
    
    int N;
    int distance = 0;
    int[][] attack = { {-1, -1},{0, -1},{-1, 0}, {0,0}  };
    int[][][] attD = {
    		{{-1,0},{0,-1}},
    		{{1,0},{0,-1}},
    		{{-1,0},{0,1}},
    		{{1,0},{0,1}},
    };
    
    class Position {
    	int x, y, cnt;
		public Position(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
		
    }
    int solve(int x, int y, int dir, int[][] maps, int p) {
    	int sum = 0;
    	if(!isInBound(x, y)) return sum;
    	
    	boolean[][] visited = new boolean[N][N];
    	Queue<Position> q = new LinkedList<>();
    	q.offer(new Position(x, y, 0));
    	visited[x][y] = true;
    	
    	while(!q.isEmpty()) {
    		Position pos = q.poll();
    		if(pos.cnt < distance) {
    			if(maps[pos.x][pos.y] <= p) sum++;
    		}
    		else {
    			if(maps[pos.x][pos.y] <= p/2) sum++;
    			continue;
    		}
    		
    		for (int d = 0; d < 2; d++) {
    			int nx = pos.x + attD[dir][d][0];
    			int ny = pos.y + attD[dir][d][1];
    			if(!isInBound(nx, ny) || visited[nx][ny]) continue;
    			q.offer(new Position(nx, ny, pos.cnt+1));
    			visited[nx][ny] = true;
			}
    	}
    	
    	return sum;
    }
    
    boolean isInBound(int x, int y) {
    	if(x < 0 || x >= N || y < 0 || y >= N) return false;
    	
    	return true;
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution3().solution(new int[][] 
		{
			{1, 28, 41, 22, 25, 79, 4}, 
			{39, 20, 10, 17, 19, 18, 8}, 
			{21, 4, 13, 12, 9, 29, 19}, 
			{58, 1, 20, 5, 8, 16, 9}, 
			{5, 6, 15, 2, 39, 8, 29},
			{39, 7, 17, 5, 4, 49, 5}, 
			{74, 46, 8, 11, 25, 2, 11}
		}, 19, 6));
		
	}
}
