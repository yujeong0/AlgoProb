package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SW_5653_줄기세포배양 {
	static class Cell {
		int x, y, life, state;

		public Cell(int life) {
			this.life = life;
			this.state = -1 * life;
		}
		
		public Cell(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.state = -1 * life;	// state 가 음수이면 비활성, 양수이면 활성, life < state 이면 죽은 상태
		}

		public Cell(int x, int y, int life, int state) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.state = state;	
		}
		
		@Override
		public String toString() {
			return "Cell [x=" + x + ", y=" + y + ", life=" + life + ", state=" + state + "]";
		}
		
	}
	
	static int N, M, K;
	static Cell[][] grid = new Cell[400][400];	// 동시에 번식하는 경우 생명력 비교를 위해 있는 배열
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static Queue<Cell> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int l;
		for (int testcase = 1; testcase <= T; testcase++) {
			q.clear();
			
			for (int i = 0; i < 400; i++) {
				Arrays.fill(grid[i], null);
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 초기 세로 크기
			M = Integer.parseInt(st.nextToken());	// 초기 가로 크기
			K = Integer.parseInt(st.nextToken());	// 배양 시간
			
			for (int i = 185; i < N+185; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 185; j < M+185; j++) {
					l = Integer.parseInt(st.nextToken());
					if(l == 0) continue;
					
					q.offer(new Cell(i, j, l));	// 큐1에 초기 세포들 넣기
					grid[i][j] = new Cell(l);		// 배열에 생명력 넣기
				}
			}	// end of input
			
			sb.append("#" + testcase + " " + solve() +"\n");
		} // end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
	private static int solve() {
		
		Cell cell;
		int x, y, size, count;
		Set<int[]> pos = new HashSet<>();	// 새로 추가된 위치 넣어놓기
		
		for (int time = 0; time < K; time++) {
			size = q.size();
			if(size == 0) return 0;
			pos.clear();
			count = 0;
			while(count < size) {	// 해당 시간 떄 비활성 혹은 활성 상태의 세포 개수만큼만 poll 한다. -> 중간에 offer 때문에 세는 거임
				cell = q.poll();
				if(cell.state < 0) {	// 비활성상태라면 시간하나 올리고 다시 큐에 넣기
					q.offer(new Cell(cell.x, cell.y, cell.life, cell.state+1));
					grid[cell.x][cell.y].state++;
				}
				else if(cell.state >= 0) {	// 활성상태면 상하좌우 번식
					grid[cell.x][cell.y].state++;
					for (int d = 0; d < 4; d++) {
						x = cell.x + dir[d][0];
						y = cell.y + dir[d][1];
						
						// 동시에 번식한 세포가 없거나 동시에 번식한 세포가 있고 내 생명력이 더 강하면 해당 셀은 내거!
						if(grid[x][y] == null || (grid[x][y] != null && pos.contains(new int[] {x,y}) && grid[x][y].life < cell.life) )	{
							grid[x][y] = new Cell(cell.life);
							pos.add(new int[] {x,y});	// 변화가 있는 위치 넣어놓기
//							q.offer(new Cell(x, y, cell.life)); -> 이놈때문에 테케좀이상하게 나옴
						}
					}
					if(cell.state+1 < cell.life) // 아직 활성상태면
						q.offer(new Cell(cell.x, cell.y, cell.life, cell.state+1));
				}
				count++;
			}	// end of while
			
			for(int[] p : pos) {
				q.offer(new Cell(p[0], p[1], grid[p[0]][p[1]].life));
			}
			
		}	// end of for
		
		return q.size();
		
	}
	
} // end of class
