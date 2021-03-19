package SWexpert.모의역량210317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution1 {
	static int N, maxCore;
	static List<int[]> coreList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			coreList = new ArrayList<>();
			answerList = new ArrayList<>();
			maxCore = 0;
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) coreList.add(new int[] {i, j});
				}
			}
			
			solve(0, map, 0, 0);
			
			Collections.sort(answerList, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1]-o2[1];
				}
			});
			
			sb.append("#").append(testcase).append(" ").append(answerList.get(0)[1]).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int[][] dir = new int[][] {{-1, 0},{0, 1},{1, 0},{0, -1}};
	static List<int[]> answerList;
	static void solve(int idx, int[][] map, int connectCore, int lengthLine) {
		if(idx == coreList.size()) {
			if(maxCore == connectCore) {
				answerList.add(new int[] {maxCore, lengthLine});
			}
			else if(maxCore < connectCore) {
				answerList.clear();
				maxCore = connectCore;
				answerList.add(new int[] {maxCore, lengthLine});
			}
			return;
		}
		
		solve(idx+1, map, connectCore, lengthLine);
		
		int[] pos = coreList.get(idx);
		for (int d = 0; d < 4; d++) {
			if(canGo(pos[0], pos[1], d, map)) {
				int[][] copyMap = new int[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						copyMap[i][j] = map[i][j];
					}
				}
				
				int nx = pos[0], ny = pos[1];
				int cnt = 0;
				while(true) {
					nx += dir[d][0];
					ny += dir[d][1];
					if(nx >= N || nx < 0 || ny >= N || ny < 0) break;
					
					copyMap[nx][ny] = 2;
					cnt++;
				}
				solve(idx+1, copyMap, connectCore+1, lengthLine+cnt);
			}
		}
	}
	
	static boolean canGo(int x, int y, int D, int[][] map) {
		while(true) {
			x += dir[D][0];
			y += dir[D][1];
			if(x >= N || x < 0 || y >= N || y < 0) break;
			if(map[x][y] == 2 || map[x][y] == 1) return false;
		}
		
		return true;
	}
	
	static void printMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
