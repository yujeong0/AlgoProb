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
	static int[][] grid;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int count;
	static List<int[]> crushedList = new ArrayList<>();
	static int MIN;
	static boolean[][] visited;
	
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
			grid = new int[H][W];
			visited = new boolean[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input
			
			visited = new boolean[H][W];
			MIN = Integer.MAX_VALUE;

			int[][] arr = new int[H][];
			
			for (int j = 0; j < W; j++) {
				for (int i = 0; i < H; i++) {
					if(grid[i][j] != 0) {
						for (int k = 0; k < H; k++) {
							arr[k] = Arrays.copyOf(grid[k], W);
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
//			System.out.println(count);
//			printArr(arr);
			if(MIN > count) MIN = count;
			
			return;
		}
		
		for (int i = 0; i < H; i++)
			Arrays.fill(visited[i], false);	
		
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				
				if(c == starty && r == startx) {
//					visited[r][c] = true;
//					crushedList.add(new int[] {r, c});
					
					int[][] copy = new int[H][];
					for (int k = 0; k < H; k++) {
						copy[k] = Arrays.copyOf(arr[k], W);
					}
//					dfs(r, c, copy);
//					for(int[] pos : crushedList) {
//						arr[pos[0]][pos[1]] = 0;	// 블록제거하기
//					}
//					crushedList.clear();
					
					bfs(r, c, arr);
					
					
					// 배열 떙기기~
					for (int col = 0; col < W; col++) {
						for (int row = H-1; row > 0; row--) {
							if(arr[row][col] == 0) {
								for(int x = row; x > 0; x--) {
									arr[x][col] = arr[x-1][col];
								}
								arr[0][col] = 0;
							}
						}
					}
					
					// 그 다음 구슬 던지러 go
					for (int j = 0; j < W; j++) {
						for (int i = 0; i < H; i++) {
							if(arr[i][j] != 0) {
								int[][] copyArr = new int[H][];
								for (int k = 0; k < H; k++) {
									copyArr[k] = Arrays.copyOf(arr[k], W);
								}
								solve(copyArr, i, j, marble-1);
								break;
							}
						}	
					}
			
					break;
				} // if
				
			} // r
		} // c
		
	} // solve
	
	private static void bfs(int startx, int starty, int[][] arr) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {startx, starty});
		visited[startx][starty] = true;
		crushedList.add(new int[] {startx, starty});
		
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
					
					if(x >= 0 && x < H && y < W && y >= 0 && !visited[x][y] && arr[x][y] != 0) {
						crushedList.add(new int[] {x, y});
						visited[x][y] = true;
						if(arr[x][y] != 1)	// 1이면 자기만 제거되니까 dfs 안들어감
							q.offer(new int[] {x, y});
					}
				}
				
			}
			
		}
		
	}
	
	private static void dfs(int startx, int starty, int[][] arr) {
		int x, y;
		int distance = arr[startx][starty];
		
		for (int d = 0; d < 4; d++) {
			x = startx;
			y = starty;
			
			for (int dis = 0; dis < distance-1; dis++) {	// 제거되는 범위까지만
				x += dir[d][0];
				y += dir[d][1];
				
				if(x >= 0 && x < H && y < W && y >= 0 && !visited[x][y] && arr[x][y] != 0) {
					crushedList.add(new int[] {x, y});
					visited[x][y] = true;
					if(arr[x][y] != 1)	// 1이면 자기만 제거되니까 dfs 안들어감
						dfs(x, y, arr);
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
