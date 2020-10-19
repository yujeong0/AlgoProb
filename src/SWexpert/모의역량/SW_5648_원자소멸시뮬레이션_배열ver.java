package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간초과
public class SW_5648_원자소멸시뮬레이션_배열ver {
	static class Atom {
		int x, y; 
		int d, energy;
		
		Atom(int x, int y, int d, int energy) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.energy = energy;
		}
		
	}
	
	// { x, y } 상/하/좌/우
	static int[][] dir = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
	static Atom[] atoms = new Atom[1002];
	static int[][] visited = new int[4002][4002];
	static boolean[][] collided = new boolean[4002][4002];
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int x, y, d, energy;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
//			for (int a = 0; a < 4001; a++) {
//				Arrays.fill(visited[a], 0);
//				Arrays.fill(collided[a], false);
//			}

			N = Integer.parseInt(br.readLine());	// 원자의 개수
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				energy = Integer.parseInt(st.nextToken());
				
				x += 1000;
				y += 1000;	// 음수 좌표 때문에 +1000
				
				atoms[i] = new Atom(x*2, y*2, d, energy);	// 정수값 아닌데서 충돌하는 경우 때문에 좌표2배!!!
			}	// end of input
			
			sb.append("#"+ testcase + " " + solve() +"\n");
		}	// end of tc
		
		System.out.println(sb.toString());
		
	}	// end of main
	
	private static int solve() {
		int total = 0;
		// 모든 원자의 맨 처음 위치를 1로 설정
		for (int a = 0; a < N; a++) {
			visited[atoms[a].x][atoms[a].y] = 1;
		} // i
		
		for (int i = 0; i < 4001; i++) {	// 최대 4000초..? 제일 작은게 0이니까 그럴듯
			
			for (int a = 0; a < N; a++) {	// 모든 원자들 1씩 이동
				if(atoms[a].x == -1) continue;	// 이미 소멸한 원자는 안 봄
				
				visited[atoms[a].x][atoms[a].y]--;
				
				atoms[a].x += dir[atoms[a].d][0];
				atoms[a].y += dir[atoms[a].d][1];
				
				if(atoms[a].x < 0 || atoms[a].x > 4000 || atoms[a].y < 0 || atoms[a].y > 4000) {
					atoms[a].x = -1;	// x가 -1이면 그냥 끝난걸로
				}
				else {
					visited[atoms[a].x][atoms[a].y]++;
					
					if(visited[atoms[a].x][atoms[a].y] > 1)
						collided[atoms[a].x][atoms[a].y] = true;
				}
			} // a
			
			total += whereCollided();	// 충돌한 원자들 에너지 더해준다.
			
		}
		
		return total;
	} // solve
	
	private static int whereCollided() {
		int sum = 0;
		for (int a = 0; a < N; a++) {
			if(atoms[a].x == -1) continue;	// 이미 소멸한 원자는 안 봄
			
			if(collided[atoms[a].x][atoms[a].y]) {
				if(visited[atoms[a].x][atoms[a].y] < 2)
					collided[atoms[a].x][atoms[a].y] = false;
				
				sum += atoms[a].energy;
				visited[atoms[a].x][atoms[a].y]--;	// 방문된 카운트도 줄임
				atoms[a].x = -1;	// 충돌하면 소멸하니까 소멸처리
			}
		}
		return sum;
	}
	
} // end of class
