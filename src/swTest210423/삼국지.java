package swTest210423;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 삼국지 {
	static int N;
	static int[][] TEAM, NOW, ADD;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			TEAM = new int[N][N];
			NOW = new int[N][N];
			ADD = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					TEAM[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					NOW[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ADD[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			
			int SUM = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					SUM += NOW[i][j];
				}
			}
			
			System.out.println("#" + testcase + " " + SUM);
		} //tc
		
	} //main

	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static class Move{
		int x, y, value; // value는 더할 값(마이너스도 가능)

		public Move(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Move [x=" + x + ", y=" + y + ", value=" + value + "]";
		}
		
	}
	
	private static void solve() {
		List<Move> list = new ArrayList<>();
		
		int myTeam = 0;
		while(true) {
			if(isEnd()) break;
			
			myTeam++;
			if(myTeam == 4) myTeam = 1;
			
			boolean go = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(TEAM[i][j] == myTeam) {
						go = true;
						break;
					}
				}
			}
			if(!go) continue;
			
			// 공격
			List<int[]> ground = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(TEAM[i][j] == myTeam || TEAM[i][j] == 0) continue;
					
					int sum = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dir[d][0];
						int ny = j + dir[d][1];
						
						if(isInBound(nx, ny) && TEAM[nx][ny] == myTeam) {
							sum += NOW[nx][ny];
						}
					} // d
					if(sum > NOW[i][j]*5) {
						int mySum = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dir[d][0];
							int ny = j + dir[d][1];
							
							if(isInBound(nx, ny) && TEAM[nx][ny] == myTeam) {
								mySum += NOW[nx][ny] / 4;
								list.add(new Move(nx, ny, -(NOW[nx][ny] / 4)));
							}
						} // d
						list.add(new Move(i, j, mySum - (NOW[i][j]*2)));
						ground.add(new int[] {i, j});
					}
					
				} // j
			} // i
			
			for(int[] pos : ground) {
				TEAM[pos[0]][pos[1]] = myTeam;
			}
			ground.clear();
			// 이동정보 토대로 공격
			for(Move m : list) {
				NOW[m.x][m.y] += m.value;
			}
			list.clear();

			// 지원
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(TEAM[i][j] != myTeam || TEAM[i][j] == 0) continue;
					
					boolean isAllMine = true;
					for (int d = 0; d < 4; d++) {
						int nx = i + dir[d][0];
						int ny = j + dir[d][1];
						
						if(!isInBound(nx, ny)) continue;
						if(TEAM[nx][ny] != 0 && TEAM[nx][ny] != myTeam) {
							isAllMine = false;
							break;
						}
					} // d
					
					if(isAllMine) { // 나의 인접한 땅이 다 내 땅이면 인접땅들에게 1/5씩 주기
						int cnt = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dir[d][0];
							int ny = j + dir[d][1];
							
							if(!isInBound(nx, ny) || TEAM[nx][ny] == 0) continue;
							cnt++;
							list.add(new Move(nx, ny, NOW[i][j] / 5));
						} // d
						list.add(new Move(i, j, -((NOW[i][j]/5) * cnt)));
					}
					else { // 내 땅이 다른 땅과 인접해있는데 
						for (int d = 0; d < 4; d++) {
							int nx = i + dir[d][0];
							int ny = j + dir[d][1];
							
							if(!isInBound(nx, ny) || TEAM[nx][ny] == 0) continue;

							// 내가 인접한 내 땅 5배 넘으면
							if(TEAM[nx][ny] == myTeam) {
								if(NOW[i][j] > NOW[nx][ny] * 5) {
									list.add(new Move(nx, ny, NOW[i][j] / 5));
									list.add(new Move(i, j, -(NOW[i][j] / 5)));
								}
							}
						} // d
					}
					
					
				} // j
			} // i
			
			// 이동정보 토대로 병력 지원
			for(Move m : list) {
				NOW[m.x][m.y] += m.value;
			}
			list.clear();
			
			// 보충
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					NOW[i][j] += ADD[i][j];
				} // j
			} // i
			

		} //while
		
	} //solve
	
	static boolean isInBound(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	} 
	
	static boolean isEnd() {
		int t = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(TEAM[i][j] == 0) continue;
				if(t == -1) {
					t = TEAM[i][j];
					continue;
				}
				if(t != TEAM[i][j]) 
					return false;
			}
		}
		
		return true;
	}
	
}
