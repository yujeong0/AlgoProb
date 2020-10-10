package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5656_벽돌깨기2 {

	static int N, W, H;
	static int[][] grid = new int[16][13];
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int count;
	static int MIN;
	static boolean[][] visited = new boolean[16][13];
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input
			
			MIN = Integer.MAX_VALUE;

			for (int j = 0; j < W; j++) {
				if(j == 9)
					System.out.println();
				for (int i = 0; i < H; i++) {
					if(grid[i][j] != 0) {
						int[][] arr = new int[H][W];
						for (int k = 0; k < H; k++) {
							for (int k2 = 0; k2 < W; k2++) {
								arr[k][k2] = grid[k][k2];
							}
						}
						solve(arr, i, j, N);
						break;
					}
				}
			}
			
			sb.append("#" + testcase + " " + MIN + "\n");
			
		}	// end of tc
		
		System.out.println(sb.toString());
	}	// end of main
	
	private static void solve(int[][] arr, int startx, int starty, int marble) {
		if(marble == 0) {
			count = countBlock(arr);
//			if(count == 26) {
//				System.out.println(count);
//				printArr(arr);
//			}
			if(MIN > count) MIN = count;
			
			return;
		}
		
		for (int i = 0; i < H; i++)
			Arrays.fill(visited[i], false);	
					
		q.clear();
		int[][] copy = new int[H][W];
		for (int k = 0; k < H; k++) {
			for (int k2 = 0; k2 < W; k2++) {
				copy[k][k2] = arr[k][k2];
			}
		}
		bfs(startx, starty, copy);	// 구슬 하나 떨어뜨리기~
		
		// 배열 떙기기~
		for (int col = 0; col < W; col++) {
			for (int row = H-1; row > 0; row--) {
				if(copy[row][col] == 0) {
					for(int x = row; x > 0; x--) {
						copy[x][col] = copy[x-1][col];
					}
					copy[0][col] = 0;
				}
			}
		}
		
		// 그 다음 구슬 던지러 go
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if(copy[i][j] != 0) {
					int[][] copyArr = new int[H][W];
					for (int k = 0; k < H; k++) {
						for (int k2 = 0; k2 < W; k2++) {
							copyArr[k][k2] = copy[k][k2];
						}
					}
					solve(copyArr, i, j, marble-1);
					break;
				}
			}	
		}

		
	} // solve
	
	private static void bfs(int startx, int starty, int[][] arr) {

		q.offer(new int[] {startx, starty});
		visited[startx][starty] = true;
		
		int[] pos;
		int x, y, distance;
		while(!q.isEmpty()) {
			pos = q.poll();
			distance = arr[pos[0]][pos[1]];
			arr[pos[0]][pos[1]] = 0;
			
			for (int d = 0; d < 4; d++) {
				x = pos[0];
				y = pos[1];
				
				for (int dis = 0; dis < distance-1; dis++) {	// 제거되는 범위까지만
					x += dir[d][0];
					y += dir[d][1];
					
					if(x >= 0 && x < H && y < W && y >= 0) {
						if(!visited[x][y] && arr[x][y] != 0) {
							visited[x][y] = true;
							q.offer(new int[] {x, y});
						}
					}
					else break;
				}
				
			}
			
		}
		
	}
	
	private static int countBlock(int[][] arr) {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(arr[i][j] != 0) sum++;
			}
		}
		
		return sum;
	}
	
	private static void printArr(int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

} // end of class
