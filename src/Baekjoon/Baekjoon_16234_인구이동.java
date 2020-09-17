package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_16234_인구이동 {

	static int N, L, R;
	static int[][] map;
	static int sumofmoving = 0;
	static int sum;	// 연합 인구 수
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	static List<int[]> list = new ArrayList<>();	// 모든 칸 검사할 때마다 연합 가능한 (x,y) 넣기
	static boolean[][] selected;	// 연합에 포함된 친구들 저장
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		selected = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean moving;
		while(true) {
			moving = false;
			for (int k = 0; k < N; k++) {
				Arrays.fill(selected[k], false);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(selected[i][j]) continue;
					
					for (int k = 0; k < N; k++) {
						Arrays.fill(visited[k], false);
					}
					list.clear();
					
					list.add(new int[] {i, j});	// 현재 위치 연합에 포함
					sum = map[i][j];	// 연합 인구 수에 현재 위치 인구 넣어줌
					visited[i][j] = true;
					selected[i][j] = true;
					dfs(i, j);
//					printMap();
					
					if(list.size() > 1) {	// 인구 이동이 있었다면
						moving = true;
						sum /= list.size();	// 연합 인구 수를 연합구역 수로 나누기
						for(int[] pos: list) {
							map[pos[0]][pos[1]] = sum;	// 연합 지역들에게 나눈 수 update
						}
//						i = -1;			// 다시 처음부터(0,0)부터 검사
//						break;
					}
					
				}
			}
			if(!moving) break;
			sumofmoving++;	// 인구이동 횟수 +1
//			printMap();
		}
		System.out.println(sumofmoving);
		
	}
	
	private static void dfs(int startx, int starty) {
		
		int x, y;
		for (int d = 0; d < 4; d++) {
			x = startx + dir[d][0];
			y = starty + dir[d][1];
			
			if(isInBound(x, y) && !visited[x][y] && !selected[x][y] && Math.abs(map[startx][starty] - map[x][y]) >= L && Math.abs(map[startx][starty] - map[x][y]) <= R) {
				list.add(new int[] {x, y});		// 방문하지 않았고 인구 수 차이가 L~R 이라면 연합에 추가
				sum += map[x][y];				// 연합인구수에 현재위치 인구수 더하기
				visited[x][y] = true;
				selected[x][y] = true;
				dfs(x, y);
			}
		}
	}
	
	private static boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y >= N || y < 0)
			return false;
		
		return true;
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("인구이동 횟수 : " + sumofmoving);
		System.out.println();
	}
}
