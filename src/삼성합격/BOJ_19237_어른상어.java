package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_19237_어른상어 {
	static class Shark {
		int x, y, d, no;
		int[][] dirOrder = new int[5][4];

		public Shark(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
		}
	}

	static class Smell {
		int sharkNo, time;
	}

	static int N, M, k;
	static Shark[] sharks;
	static Smell[][] smell;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		smell = new Smell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				smell[i][j] = new Smell();
			}
		}
		sharks = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int no = Integer.parseInt(st.nextToken());
				if (no == 0)
					continue;
				sharks[no] = new Shark(i, j, no);
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) { // 1위, 2왼, 3아래, 4오
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < 4; j2++) {
					sharks[i].dirOrder[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
		}

		System.out.println(solve());
	} // main

	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static int solve() {

		int T = 0;
		while (true) {
			// 1번 상어만 남았다면 종료
			boolean isEnd = true;
			for (int i = 2; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark.no != 0) {
					isEnd = false;
					break;
				}
			}
			if (isEnd)
				break;
			
			// 모든 상어가 자신의 위치에 냄새를 뿌린다.
			for (int i = 1; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark.no == 0)
					continue;
				smell[shark.x][shark.y].sharkNo = shark.no;
				smell[shark.x][shark.y].time = k;
			}


			// 모든 상어가 이동한다.
			for (int i = 1; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark.no == 0)
					continue;
				int[] order = shark.dirOrder[shark.d]; // 상어의 방향에 따른 방향우선순위
				boolean isFind = false;
				for (int j = 0; j < 4; j++) {
					int nx = shark.x + dir[order[j]][0];
					int ny = shark.y + dir[order[j]][1];

					if (!isInBound(nx, ny))
						continue;
					if (smell[nx][ny].sharkNo == 0) {
						shark.x = nx;
						shark.y = ny;
						shark.d = order[j];
						isFind = true;
						break;
					}
				} // j

				if (!isFind) { // 다 냄새 있으면 내 냄새중 우선순위 높은 걸로 이동
					for (int j = 0; j < 4; j++) {
						int nx = shark.x + dir[order[j]][0];
						int ny = shark.y + dir[order[j]][1];

						if (!isInBound(nx, ny))
							continue;
						if (smell[nx][ny].sharkNo == shark.no) {
							shark.x = nx;
							shark.y = ny;
							shark.d = order[j];
							break;
						}
					} // j
				}
			}

			// 같은 칸에 있는 상어중 강한 상어만 남기고 다 없앤다.
			List<Integer>[][] grid = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = new ArrayList<>();
				}
			}
			for (int i = 1; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark.no == 0)
					continue;
				grid[shark.x][shark.y].add(shark.no);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j].size() > 1) {
						for (int n = 1; n < grid[i][j].size(); n++) {
							sharks[grid[i][j].get(n)].no = 0;
						}
					}
				}
			}

			// 이동 후 모든 냄새 time 을 줄이는데 time 만료된 냄새 없앤다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (smell[i][j].sharkNo == 0)
						continue;

					smell[i][j].time--;
					if (smell[i][j].time == 0)
						smell[i][j].sharkNo = 0;
				}
			}
			
			T++;
			if(T > 1000) return -1;
		} // while

		return T;
	} // solve

	static boolean isInBound(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;

		return true;
	}

}
