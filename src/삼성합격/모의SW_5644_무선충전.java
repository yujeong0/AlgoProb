package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_5644_무선충전 {
	static class BC {
		int x, y, C, P;

		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			C = c;
			P = p;
		}
	}
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static int M, A;	// M : 이동시간, A : BC 개수
	static int[] moveA, moveB;
	static BC[] bcArr;
	static Pos posA, posB;
	static int[][] dir = new int[][] { {0, 0},{-1, 0},{0, 1},{1, 0},{0, -1} };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			posA = new Pos(1,1);
			posB = new Pos(10,10);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			moveA = new int[M+1];
			moveB = new int[M+1];
			bcArr = new BC[A];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				bcArr[i] = new BC(y, x, C, P);	//  x y 바꿔서 넣음
			}
			
			sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
		} //tc
		
		System.out.println(sb.toString());
				
	}
	
	static int solve() {
		int SUM = 0;
		for (int t = 0; t <= M; t++) {
			posA.x += dir[moveA[t]][0];
			posA.y += dir[moveA[t]][1];
			posB.x += dir[moveB[t]][0];
			posB.y += dir[moveB[t]][1];
			
			List<Integer> bcA = new ArrayList<>();
			List<Integer> bcB = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				int distanceA = Math.abs(posA.x - bcArr[i].x) + Math.abs(posA.y - bcArr[i].y);
				int distanceB = Math.abs(posB.x - bcArr[i].x) + Math.abs(posB.y - bcArr[i].y);
				if(distanceA <= bcArr[i].C) bcA.add(i);
				if(distanceB <= bcArr[i].C) bcB.add(i);
			}
			
			if(bcA.size() == 0) {
				int max = 0;
				for(int num : bcB) {
					if(max < bcArr[num].P) max = bcArr[num].P;
				}
				SUM += max;
			}
			else if(bcB.size() == 0) {
				int max = 0;
				for(int num : bcA) {
					if(max < bcArr[num].P) max = bcArr[num].P;
				}
				SUM += max;
			}
			else if(bcA.size() >= 1 && bcB.size() >= 1) {
				int max = 0;
				for(int numB : bcB) {
					for(int numA : bcA) {
						int sum = bcArr[numB].P;
						if(numB != numA) {
							sum += bcArr[numA].P;
						}
						if(max < sum) max = sum;
					}
				}
				SUM += max;
			}

		} // t
		
		return SUM;
	} //solve
	
} //class
