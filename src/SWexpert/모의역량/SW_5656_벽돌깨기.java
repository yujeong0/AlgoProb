package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5656_벽돌깨기 {

	static int N, W, H;
	static int[][] grid = new int[20][20];
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int count;
	static int MIN;
	static boolean[][] visited = new boolean[20][20];
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
			
			for (int i = 1; i <= H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input
			
			// 처음부터 블럭이 0개일 경우엔 바로 MIN을 0으로
			boolean isEnd = true;
			for (int k = H; k >= 1; k--) {
				for (int k2 = 1; k2 <= W; k2++) {
					if(grid[k][k2] != 0) {
						isEnd = false;
						break;
					}
				}
			}
			if(isEnd) {
				MIN = 0;
			}
			else {
				MIN = Integer.MAX_VALUE;
	
			out:for (int j = 1; j <= W; j++) {
					for (int i = 1; i <= H; i++) {
						if(grid[i][j] != 0) {
							int[][] arr = new int[H+1][W+1];
							for (int k = 1; k <= H; k++) {
								for (int k2 = 1; k2 <= W; k2++) {
									arr[k][k2] = grid[k][k2];
								}
							}
							solve(arr, i, j, N);
							if(MIN == 0) break out;	// 이미 최소이면 바로 끝내버리기
							break;
						}
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
			if(MIN > count) MIN = count;
			
			return;
		}
		
		int[][] copy = new int[H+1][W+1];
		for (int k = 1; k <= H; k++) {
			for (int k2 = 1; k2 <= W; k2++) {
				copy[k][k2] = arr[k][k2];
			}
		}
		bfs(startx, starty, copy);	// 구슬 하나 떨어뜨리기~
		
		// 이미 배열이 다 0일 경우 바로 종료해버리기
		boolean isEnd = true;
		for (int k = H; k >= 1; k--) {
			for (int k2 = 1; k2 <= W; k2++) {
				if(copy[k][k2] != 0) {
					isEnd = false;
					break;
				}
			}
		}
		if(isEnd) {
			MIN = 0;
			return;
		}
		
		// 배열 떙기기~
		for (int col = 1; col <= W; col++) {
			for (int row = H; row > 1; row--) {
				if(copy[row][col] == 0) {
					// 기준 행 위로 다 0 이면 종료하고 아니면 그대로 진행
					boolean isZero = true;
					for (int x = row-1; x >= 1; x--) {
						if(copy[x][col] != 0) {
							isZero = false;
							break;
						}
					}
					if(isZero) break;
					
					// 배열 한 칸씩 내림
					for(int x = row; x > 1; x--) {
						copy[x][col] = copy[x-1][col];
					}
					copy[1][col] = 0;
					
					row++;	// 다시 자신부터 0인지 확인해야하기때문에 +1 해줌
					
				}	// if
				
			}	// row
		}	// col
		
		// 그 다음 구슬 던지러 go
		for (int j = 1; j <= W; j++) {
			for (int i = 1; i <= H; i++) {
				if(copy[i][j] != 0) {
					int[][] copyArr = new int[H+1][W+1];
					for (int k = 1; k <= H; k++) {
						for (int k2 = 1; k2 <= W; k2++) {
							copyArr[k][k2] = copy[k][k2];
						}
					}
					solve(copyArr, i, j, marble-1);	// 다음 구슬
					if(MIN == 0) return;
					
					break;
				}
			}
		}
		
	} // solve
	
	private static void bfs(int startx, int starty, int[][] arr) {	// 구슬떨어뜨렸을 떄 제거되는 것들 0으로 만들기
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				visited[i][j] = false;	
			}
		}
					
		q.clear();
		
		q.offer(new int[] {startx, starty});
		visited[startx][starty] = true;
		
		int[] pos;
		int x, y, distance;
		while(!q.isEmpty()) {
			pos = q.poll();
			distance = arr[pos[0]][pos[1]];
			arr[pos[0]][pos[1]] = 0;
			
			if(distance <= 1) continue;	// 제거거리 1밑은 어차피 나만 제거됨
			
			for (int d = 0; d < 4; d++) {
				x = pos[0];
				y = pos[1];
				
				for (int dis = 0; dis < distance-1; dis++) {	// 제거되는 범위까지만
					x += dir[d][0];
					y += dir[d][1];
					
					if(x < 1 || x >= H+1 || y >= W+1 || y < 1) continue;
					
					if(!visited[x][y] && arr[x][y] != 0) {
						visited[x][y] = true;
						q.offer(new int[] {x, y});
					}
				}
				
			}
			
		}
		
	} // bfs
	
	private static int countBlock(int[][] arr) {
		int sum = 0;
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				if(arr[i][j] != 0) sum++;
			}
		}
		
		return sum;
	}
	
} // end of class
