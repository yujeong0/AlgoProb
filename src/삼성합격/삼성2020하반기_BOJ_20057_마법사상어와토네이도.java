package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 삼성2020하반기_BOJ_20057_마법사상어와토네이도 {
	static int N;
	static int[][] grid;
	static int sum = 0;	// 바깥으로 나간 모래의 양
	static int sand;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();
		System.out.println(sum);
		
	} // main

	static int[][] dir = new int[][] { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	static void solve() {
		int x = N/2, y = N/2;
		int d = 0, k = 1, times = 1;
		while(true) {
			for (int i = 0; i < k; i++) {
				x += dir[d][0];
				y += dir[d][1];
			}
			
			if(x == 2 && y == 0) 
				System.out.println("드디어!");
			
			// 모래 옮기기
			sand = grid[x][y];
			int alpha = sand;
			// 만약 d == 0이라면 +3, 아니면 -1 은 7% 날라감. d+1도 7%
			// 양옆에서 한 번 더 +dir 한 곳은 2%
			// x-dir y-dir 에서 양옆으로 1%
			// x+dir y+dir 에서 양옆으로 10%
			// x y 에서 +dir 2번 한 것은 5%
			
			int nx = 0, ny = 0;
			double amount = 0;
			
			// 2 7 2 7 
			for (int i = 0; i < 2; i++) {
				nx = x+dir[(d==0? d+3 : d-1)%4][0];
				ny = y+dir[(d==0? d+3 : d-1)%4][1];
				amount = (sand*(i == 0? 0.07 : 0.02));
				if(amount >= 1) {
					bringSand(nx, ny, (int)amount);
					alpha -= (int)amount;
					
					nx = x+dir[(d+1)%4][0];
					ny = y+dir[(d+1)%4][1];
					bringSand(nx, ny, (int)amount);
					alpha -= (int)amount;
				}
			}

			// 1 왼쪽
			amount = (int)(sand*0.01);
			nx = x - dir[d][0] + dir[(d==0? d+3 : d-1) % 4][0];
			ny = y - dir[d][1] + dir[(d==0? d+3 : d-1) % 4][1];
			if(amount >= 1) {
				bringSand(nx, ny, (int)amount);
				alpha -= (int)amount;
			}
			
			// 1 오른쪽
			nx = x+dir[(d+1)%4][0];
			ny = y+dir[(d+1)%4][1];
			if(amount >= 1) {
				bringSand(nx, ny, (int)amount);
				alpha -= (int)amount;
			}
			
			// 10 왼쪽
			amount = (int)(sand*0.1);
			nx = x+dir[d][0] + dir[(d==0? d+3 : d-1) % 4][0];
			ny = y+dir[d][1] + dir[(d==0? d+3 : d-1) % 4][1];
			if(amount >= 1) {
				bringSand(nx, ny, (int)amount);
				alpha -= (int)amount;
			}
			
			// 10 오른쪽
			nx = x+dir[d][0] + dir[(d+1)%4][0];
			ny = y+dir[d][1] + dir[(d+1)%4][1];
			if(amount >= 1) {
				bringSand(nx, ny, (int)amount);
				alpha -= (int)amount;
			}
			
			// 5
			amount = (int)(sand*0.05);
			nx = x+(dir[d][0]*2);
			ny = y+(dir[d][1]*2);
			if(amount >= 1) {
				bringSand(nx, ny, (int)amount);
				alpha -= (int)amount;
			}

			grid[x][y] = 0;
			nx = x+dir[d][0];
			ny = y+dir[d][1];
			if(isInBound(nx, ny))
				grid[nx][ny] += alpha;
			
			d = (d+1) % 4;
			
			if(times == 0) break;
			
			switch(times) {
			case 1:
				times++;
				break;
			case 2:
				if(k == N-1) times = 0;
				else {
					times = 1;
					k++;
				}
					
				break;
			}
		} // while
		
	}// solve

	static void bringSand(int nx, int ny, int amount) {
		if(isInBound(nx, ny))
			grid[nx][ny] += amount;
		else sum += amount;
	}
	
	static boolean isInBound(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
}
