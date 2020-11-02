package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_5644_무선충전_또풀기 {
	static class BC {
		int X,Y,C,P;

		public BC(int x, int y, int c, int p) {
			X = x;
			Y = y;
			C = c;
			P = p;
		}
	}
	
	static int M, A;
	static int[] movesA;
	static int[] movesB;
	static BC[] BCs;
	static int[][] dir = { {0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0} };	// 문제에서 원래 하던 dir이랑 달라서 뭐가 틀린지 한참 봤음.. 문제 제대로 읽기!! 
	static int[] perA = new int[2];
	static int[] perB = new int[2];
	static List<Integer> Anum = new ArrayList<>();
	static List<Integer> Bnum = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			perA[0] = 1;
			perA[1] = 1;
			perB[0] = 10;
			perB[1] = 10;
			
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());	// 이동 시간
			A = Integer.parseInt(st.nextToken());	// BC 개수
			BCs = new BC[A];
			movesA = new int[M+1];
			movesB = new int[M+1];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				movesA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				movesB[i] = Integer.parseInt(st.nextToken());
			}
			
			int x, y, p, c;
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				BCs[i] = new BC(x, y, p, c);
			}
			
			sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
		} 
		System.out.println(sb.toString());
		
	} // main
	
	private static int solve() {
		int total = 0;

		for (int m = 0; m <= M; m++) {
			Anum.clear();
			Bnum.clear();
			
			perA[0] += dir[movesA[m]][0];
			perA[1] += dir[movesA[m]][1];

			perB[0] += dir[movesB[m]][0];
			perB[1] += dir[movesB[m]][1];
			
			for (int a = 0; a < A; a++) {
				if(getDistance(BCs[a].X, BCs[a].Y, perA[0], perA[1]) <= BCs[a].C) {
					Anum.add(a);
				}
				if(getDistance(BCs[a].X, BCs[a].Y, perB[0], perB[1]) <= BCs[a].C) {
					Bnum.add(a);
				}
			}
			
			total += getMaxP();
			
		} // m
		
		return total;
	} // solve
	
	private static int getMaxP() {
		int max = 0;
		if(Anum.size() == 0) {
			for(int n : Bnum) {
				if(max < BCs[n].P) max = BCs[n].P;
			}
		}
		else if(Bnum.size() == 0){
			for(int n : Anum) {
				if(max < BCs[n].P) max = BCs[n].P;
			}
		}
		else {	// 둘 다 속한 곳이 있을 경우
			int total;
			for(int n1 : Anum) {
				for(int n2 : Bnum) {
					total = BCs[n1].P;
					if(n1 != n2)
						total += BCs[n2].P;
					
					if(max < total) max = total;
				}
			}
		}
		
		return max;
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
} // class
