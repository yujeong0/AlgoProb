package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5650_핀볼게임 {

	static int N;
	static int[][] map = new int[101][101];
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int MAX;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			MAX = 0;
			// 모든 점을 출발지로 시도
			int score;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {	// 모든 출발지를 모든 방향으로 시도
							score = game(i, j, d);
							if(MAX < score) {
								MAX = score;
							}
						}
					}
				}
			}
			
			sb.append("#" + testcase + " " + MAX + "\n");
		}	// end of tc
		System.out.println(sb.toString());
		
		
	}// end of main
	
	private static int game(int x, int y, int d) {
		int curX = x + dir[d][0], curY = y + dir[d][1], score = 0;
		while(true) {
			if(!isInBound(curX, curY)) {	// 경계 넘어가면 방향 반대
				score++;	// 벽에 부딪혀서 점수 +1
				if(d == 0) d = 2;
				else if(d == 1) d = 3;
				else if(d == 2) d = 0;
				else if(d == 3) d = 1;
				
				curX += dir[d][0];
				curY += dir[d][1];
			} else {
				if(curX == x && curY == y) break;	// 출발지면 게임끝
				
				if(map[curX][curY] == -1) break;	// 블랙홀이면 게임끝
				
				if(map[curX][curY] == 0) {
					curX += dir[d][0];
					curY += dir[d][1];
				}
				
				// 블록이면
				else if(map[curX][curY] >= 1 && map[curX][curY] <= 5) {
					score++;
					switch(map[curX][curY]) {	// 블록형태마다 반사방향 다르게
					case 1:
						if(d == 0) d=2;
						else if(d==1) d=3;
						else if(d==2) d=1;
						else if(d==3) d=0;
						
						curX += dir[d][0];
						curY += dir[d][1];
						break;
					case 2:
						if(d == 0) d=1;
						else if(d==1) d=3;
						else if(d==2) d=0;
						else if(d==3) d=2;
						
						curX += dir[d][0];
						curY += dir[d][1];
						break;
					case 3:
						if(d == 0) d=3;
						else if(d==1) d=2;
						else if(d==2) d=0;
						else if(d==3) d=1;
						
						curX += dir[d][0];
						curY += dir[d][1];
						break;
					case 4:
						if(d == 0) d=2;
						else if(d==1) d=0;
						else if(d==2) d=3;
						else if(d==3) d=1;
						
						curX += dir[d][0];
						curY += dir[d][1];
						break;
					case 5:
						if(d == 0) d = 2;
						else if(d == 1) d = 3;
						else if(d == 2) d = 0;
						else if(d == 3) d = 1;
						
						curX += dir[d][0];
						curY += dir[d][1];
						break;
					}
				}
				
				// 웜홀이면
				else if(map[curX][curY] >= 6 && map[curX][curY] <= 10) {
					int[] warmhole = whereIsWarm(curX, curY, map[curX][curY]);
					curX = warmhole[0];
					curY = warmhole[1];
					
					curX += dir[d][0];
					curY += dir[d][1];
				}
			}
		}	// end of while
		
		return score;
	}
	
	private static int[] whereIsWarm(int x, int y, int num) {	// 웜홀번호로 다른 웜홀 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == x && j == y) continue;
				if(map[i][j] == num) {
					return new int[] {i, j};
				}
			}
		}
		
		return null;
	}
	
	private static boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y < 0 || y >= N)
			return false;
		
		return true;
	}
}
