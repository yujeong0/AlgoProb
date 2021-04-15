package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 삼성2020하반기_BOJ_20058_마법사상어와파이어스톰 {
	static int N=1, Q, blobSize, MAX = 0;
	static int[][] grid;
	static int[] LArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		LArr = new int[Q];
		
		for (int i = 0; i < n; i++) {
			N *= 2;
		}
		
		grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			LArr[i] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		System.out.println(getSumIce());
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(grid[i][j] > 0) {
					visited = new boolean[N][N];
					blobSize = 0;
					getBlobMaxSize(i, j);
					if(MAX < blobSize) MAX = blobSize;
				}
			}
		}
		System.out.println(MAX);
		
	} // main
	
	static void solve() {
		
		for(int l : LArr) {
			int L = 1;
			for (int i = 0; i < l; i++) {
				L *= 2;
			}
			int[] numArr = new int[L*L];
			for (int i = 0; i <= N-L; i+=L) {
				for (int j = 0; j <= N-L; j+=L) {
					int idx = 0;
					int r = i+L-1, c = j;
					while(true) {
						numArr[idx++] = grid[r][c];
						if(r == i && c == j+L-1) break;
						r--;
						if(r < i) {
							r = i+L-1;
							c++;
						}
					}
					
					idx = 0;
					for (int i2 = i; i2 < i+L; i2++) {
						for (int j2 = j; j2 < j+L; j2++) {
							grid[i2][j2] = numArr[idx++];
						}
					}
					
				} // j
			} // i
			// 회전끝
			
			minusIce();
		} // for
		
	} // solve
	
	static int[][] dir = new int[][] { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static void minusIce() {
		List<int[]> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int adjCnt = 4;
				for (int d = 0; d < 4; d++) {
					int x = i+dir[d][0], y = j+dir[d][1];
					if(!isInBound(x, y)) adjCnt--;
					else if(grid[x][y] < 1) adjCnt--;
				} // d
				if(adjCnt <= 2) list.add(new int[] {i, j});
			} // j
		} // i
		
		for(int[] pos : list) {
			if(grid[pos[0]][pos[1]] > 0)
				grid[pos[0]][pos[1]]--;
		}
	} // minusIce
	
	static boolean[][] visited;
	static void getBlobMaxSize(int x, int y) {
		visited[x][y] = true;
		if(grid[x][y] > 0) blobSize++;
		else return;
		
		for (int d = 0; d < 4; d++) {
			int nx = x+dir[d][0], ny = y+dir[d][1];
			if(isInBound(nx, ny) && !visited[nx][ny])
				getBlobMaxSize(nx, ny);
		}
		
	} // getBlobMaxSize
	
	static boolean isInBound(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
	
	static int getSumIce() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += grid[i][j];
			}
		}
		return sum;
	}
	
	static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
