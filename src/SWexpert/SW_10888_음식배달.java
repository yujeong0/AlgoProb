package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9

#1 22
#2 44
#3 74
 */
public class SW_10888_음식배달 {
	static class Position {
		int x, y;
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static int[][] map = new int[22][22];
	static List<Position> stores = new ArrayList<>();
	static int MIN;
	static boolean[] selectedStores = new boolean[12];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			M = 0;	// 배달가게 수
			stores.clear();
			MIN = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 2) {
						stores.add(new Position(i, j));
						M++;
					}
				}
			} // end of input
			
			subset(0);
			
			sb.append("#" + testcase + " " + MIN + "\n");
		}	// end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
	private static void subset(int cnt) {	// 어떤 음식배달점 선택할지
		if(cnt == M) {
			boolean isSame = false;
			if(!selectedStores[0]) {
				isSame = true;
				for (int st = 1; st < M; st++) {	
					if(selectedStores[st]) {
						isSame = false;
						break;
					}
				}
			}
			if(isSame) return;
			
			MIN = Math.min(MIN, solve());
			
			return;
		}
		
		selectedStores[cnt] = true;
		subset(cnt+1);

		selectedStores[cnt] = false;
		subset(cnt+1);
	}
	
	private static int solve() {	
		int total = 0;
		int min;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					min = Integer.MAX_VALUE;
					for (int st = 0; st < M; st++) {	// 선택되어있는 배달점 중 가장 가까운 거리 찾아라
						if(selectedStores[st]) {
							min = Math.min(min, getDistance(stores.get(st).x, stores.get(st).y, i, j));
						}
					}
					
					total += min;
				}
				
			} // j
		} // i
		for (int st = 0; st < M; st++) {
			if(selectedStores[st]) {
				total += map[stores.get(st).x][stores.get(st).y];
			}
		}
		
		return total;
	} // solve
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
} // end of class
