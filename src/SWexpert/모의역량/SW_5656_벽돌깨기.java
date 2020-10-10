package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SW_5656_벽돌깨기 {

	static int N, W, H;
	static int[][] grid = new int[16][13];
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int count;
	
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
			
			int MIN = Integer.MAX_VALUE;
			// 첫번째 구슬을 모든 열에서 처음 만나는 블록들에 떨어뜨려본다.
			int[][] arr = new int[H][];
			
			for (int j = 0; j < W; j++) {	// 모든 열에 대해 시도하여 최소값 찾아내야해!
				for (int i = 0; i < H; i++) {	// 배열 초기화
					arr[i] = Arrays.copyOf(grid[i], grid[i].length);
				}
				
				for (int i = 0; i < H; i++) {
					if(grid[i][j] != 0) {
						solve(N, arr);
						break;
					}
				}	// 행
				
				// 구슬 N번 다 떨어뜨린 상태니까 남은 블록 검사
				if(MIN > count) MIN = count;
			}	// 열
			
			
			sb.append("#" + testcase + " " + MIN + "\n");
		}	// end of tc
		
		System.out.println(sb.toString());
	}	// end of main
	
	private static void solve(int NthMarble, int[][] arr) {
		if(NthMarble == 0) {	// 구슬 N번 해봤으면 종료
			count = countBlock(arr);
//			printArr(arr);
			
			return;
		}
		
		boolean[][] visited = new boolean[H][W];
		Set<int[]> crushedBlocks = new HashSet<>();	// 구슬 한 번 놓았을 때 터지는 블록들 저장
		
		for (int j = 0; j < W; j++) {	// 모든 열에 대해 시도하여 최소값 찾아내야해!
			crushedBlocks.clear();
			for (int i = 0; i < H; i++) {
				if(arr[i][j] != 0) {
					crushedBlocks.add(new int[] {i, j});
					visited[i][j] = true;
					crush(i, j, arr[i][j]-1, arr, visited, crushedBlocks);
					break;
				}
			}	// 행
			
			// 한 번 구슬 떨어뜨린 걸로 깰 수 있는 블록 다 저장했으니 해당 블록들 제거한다.
			for(int[] pos : crushedBlocks) {
				arr[pos[0]][pos[1]] = 0;
			}
			
			// 배열 떙기기~
			for (int col = 0; col < W; col++) {
				for (int row = H-1; row >= 0; row--) {
					if(arr[row][col] == 0) {
						for(int x = row; x > 0; x--) {
							arr[x][col] = arr[x-1][col];
						}
					}
				}
			}

			solve(NthMarble-1, arr);	// 다음 구슬 던지러 가기~
			
		}	// 열
		
	}
	
	private static void crush(int startx, int starty, int distance, int[][] arr, boolean[][] visited, Set<int[]> crushedBlocks) {	// distance=사방으로갈수있는거리(블록에 적혀있는 수-1)
		
		int x, y;
		for (int d = 0; d < 4; d++) {
			x = startx;
			y = starty;
			
			for (int dist = 1; dist <= distance; dist++) {
				x += dir[d][0];
				y += dir[d][1];
				
				if(x >= 0 && x < H && y >= 0 &&  y < W && !visited[x][y]) {
					crushedBlocks.add(new int[] {x, y});
					visited[x][y] = true;
					crush(x, y, arr[x][y]-1, arr, visited, crushedBlocks);	// 영향 받는 블록에 대해 다시 crush~
				}
				
			}
		}
		
	} // crush
	
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
