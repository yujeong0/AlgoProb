package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
40 2
0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4 
0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1 
5 2 4 140
8 3 3 490
60 4
0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4 
1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1 
6 9 1 180
9 3 4 260
1 4 1 500
1 3 1 230
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
100 8
2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1 
4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4 
3 4 2 340
10 1 1 430
3 10 4 10
6 3 4 400
7 4 1 80
4 5 1 420
4 1 2 350
8 4 4 300



#1 1200
#2 3290
#3 16620
#4 40650
#5 52710
 */
public class SW_5644_무선충전 {
	
	static class BC {
		int x, y, c, p;
		BC(int x, int y, int c, int p){
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}
		
	}
	static int M, A;
	static int[][] users = new int[2][105];	// 사용자 이동 방향
	static BC[] bc = new BC[9];
	static int[][] dir = { {0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0} };	// 이동x, 상, 우, 하, 좌
	static int[][] userPos = new int[2][2];	// 사용자 위치
	static List<Integer> user1 = new ArrayList<>();	// user1위치에 해당되는 BC
	static List<Integer> user2 = new ArrayList<>();	// user2위치에 해당되는 BC
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int x, y, c, p;
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());	// 총 이동 시간
			A = Integer.parseInt(st.nextToken());	// BC 개수

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {	// 사용자1의 이동
				users[0][i] = Integer.parseInt(st.nextToken());	
			}
			users[0][0] = 0;	// 0초 때 안 움직이는 것 때문에 넣음
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {	// 사용자2의 이동
				users[1][i] = Integer.parseInt(st.nextToken());
			}
			users[1][0] = 0;	// 0초 때 안 움직이는 것 때문에 넣음
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());	// 위치 x
				y = Integer.parseInt(st.nextToken());	// 위치 y
				c = Integer.parseInt(st.nextToken());	// 충전범위
				p = Integer.parseInt(st.nextToken());	// 성능
				
				bc[i] = new BC(x, y, c, p);
			}	// end of input
			
			userPos[0][0] = 1;
			userPos[0][1] = 1;
			userPos[1][0] = 10;
			userPos[1][1] = 10;

			sb.append("#" + testcase + " " + solve() + "\n");
			
		}	// end of tc
		
		System.out.println(sb.toString());
		
	}	// end of main
	
	private static int solve() {
		int result = 0, MAX;
		int sum = 0;
		for (int time = 0; time <= M; time++) {	// M시간까지 두 명을 동시에 옮기기
			MAX = 0;
			userPos[0][0] += dir[users[0][time]][0];
			userPos[0][1] += dir[users[0][time]][1];
			
			userPos[1][0] += dir[users[1][time]][0];
			userPos[1][1] += dir[users[1][time]][1];
			
			for (int i = 0; i < A; i++) {	// 모든 BC와 사용자위치 비교
				if(getDistance(userPos[0][0], userPos[0][1], bc[i].x, bc[i].y) <= bc[i].c) // 범위에 포함되면 리스트에 넣기
					user1.add(i);
				if(getDistance(userPos[1][0], userPos[1][1], bc[i].x, bc[i].y) <= bc[i].c) // 범위에 포함되면 리스트에 넣기
					user2.add(i);
			}
			
			if(user1.size() == 0) {
				for(int num2 : user2) {
					sum = bc[num2].p;
					if(MAX < sum) MAX = sum;
				}
			}
			else if(user2.size() == 0) {
				for(int num1 : user1) {
					sum = bc[num1].p;
					if(MAX < sum) MAX = sum;
				}
			}
			else {
				for(int num1 : user1) {
					for(int num2 : user2) {
						sum = 0;
						if(num1 == num2) {
							sum += bc[num1].p;
						}else {
							sum += bc[num1].p;
							sum += bc[num2].p;
						}
						if(MAX < sum) MAX = sum;
					}
				}
			}
			user1.clear();
			user2.clear();
			
			result += MAX;
		}
		
		return result;
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}	// end of class
