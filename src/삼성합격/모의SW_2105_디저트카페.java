package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의SW_2105_디저트카페 {
	static int N, MAX;
	static int[][] map;
	static int startx, starty;
	static boolean[] selected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int maxVal = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(maxVal < map[i][j]) maxVal = map[i][j];
				}
			}
			selected = new boolean[maxVal+1];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startx = i;
					starty = j;
					solve(i, j, 0, 0);
				}
			}
			
			System.out.println("#" + testcase  + " " + (MAX == 0 ? -1 : MAX));
		} //tc
		
	} //main
	
	static int[][] dir = new int[][] { {1, 1},{1, -1},{-1, -1},{-1, 1} };
	static void solve(int x, int y, int cnt, int curD) {
		if(x < 0 || x >= N || y < 0 || y >= N) return;

		if(cnt > 1 && x == startx && y == starty) {
			if(MAX < cnt) MAX = cnt;
			return;
		}
		
		for (int d = curD; d <= curD+1; d++) {
			if(d > 3) break;
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && !selected[map[nx][ny]]) {
				selected[map[nx][ny]] = true;
				solve(nx, ny, cnt+1, d);
				selected[map[nx][ny]] = false;
			}
		}
	} //solve
}
