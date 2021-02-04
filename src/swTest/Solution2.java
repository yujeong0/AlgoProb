package swTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
	static int W, H, MIN;
	static int[][] map;
	static List<int[]> houses;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			houses = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) houses.add(new int[] {i, j});
				}
			}
			
			int val = garo();
			if(val > 0 && val < Integer.MAX_VALUE)
				MIN = Math.min(MIN, val);
			
			val = sero();
			if(val > 0 && val < Integer.MAX_VALUE)
				MIN = Math.min(MIN, val);
			
			val = saseon1();
			if(val > 0 && val < Integer.MAX_VALUE)
				MIN = Math.min(MIN, val);
			
			val = saseon2();
			if(val > 0 && val < Integer.MAX_VALUE)
				MIN = Math.min(MIN, val);
			
			if(MIN <= 0 || MIN == Integer.MAX_VALUE) {
				sb.append("#").append(testcase).append(" -1\n");
			}
			else {
				sb.append("#").append(testcase).append(" ").append(MIN).append("\n");
			}
			
		} //tc
		
		System.out.println(sb.toString());
	} // main
	
	static int saseon1() {
		List<int[]> bridges;
		int result = Integer.MAX_VALUE;
		int x = 0, y = W-2;
		while(true) {
			if(x == H-2 && y == 0) break;
			int nx = x, ny = y;
			bridges = new ArrayList<>();
			boolean success = true;
			while(true) {
				if(!isInBound(nx, ny)) break;
				if(map[nx][ny] != 1) {
					bridges.add(new int[] {nx, ny});
					nx++;
					ny++;
				}
				else {
					success = false;
					break;
				}
			}
			if(success) {	// 다리 끝까지 지었다면
				int max = 0;
				// 모든 집과 거리 비교
				for(int[] h : houses) {
					int min = Integer.MAX_VALUE;
					for(int[] b : bridges) {
						min = Math.min(min, Math.abs(h[0]-b[0])+Math.abs(h[1]-b[1]));
					}
					max = Math.max(max, min);
				}
				result = Math.min(result, max);
			}
			
			if(y > 0) y--;
			else x++;
		}
		
		return result;
	}
	
	static int saseon2() {
		List<int[]> bridges;
		int result = Integer.MAX_VALUE;
		int x = 0, y = 1;
		while(true) {
			if(x == H-2 && y == W-1) break;
			int nx = x, ny = y;
			bridges = new ArrayList<>();
			boolean success = true;
			while(true) {
				if(!isInBound(nx, ny)) break;
				if(map[nx][ny] != 1) {
					bridges.add(new int[] {nx, ny});
					nx++;
					ny--;
				}
				else {
					success = false;
					break;
				}
			}
			if(success) {	// 다리 끝까지 지었다면
				// 모든 집과 거리 비교
				int max = 0;
				for(int[] h : houses) {
					int min = Integer.MAX_VALUE;
					for(int[] b : bridges) {
						min = Math.min(min, Math.abs(h[0]-b[0])+Math.abs(h[1]-b[1]));
					}
					max = Math.max(max, min);
				}
				result = Math.min(result, max);
			}
			
			if(y < W-1) y++;
			else x++;
		}
		
		return result;
	}
	
	static int garo() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			int count = 0;
			for (int j = 0; j < W; j++) {
				if(isInBound(i, j) && map[i][j] != 1) {
					count++;
				}
				else break;
			}
			if(count == W) {
				int max = 0;
				for(int[] p : houses) {
					max = Math.max(max, Math.abs(i-p[0]));
				}
				result = Math.min(result, max);
			}
		}
		return result;
	}
	static int sero() {
		int result = 0;
		for (int j = 0; j < H; j++) {
			int count = 0;
			for (int i = 0; i < W; i++) {
				if(isInBound(i, j) && map[i][j] != 1) {
					count++;
				}
				else break;
			}
			if(count == W) {
				int max = 0;
				for(int[] p : houses) {
					max = Math.max(max, Math.abs(j-p[0]));
				}
				result = Math.min(result, max);
			}
		}
		return result;
	}
	
	static boolean isInBound(int x, int y) {
		if(x >= H || y >= W || x < 0 || y < 0) return false;
		return true;
	}
}