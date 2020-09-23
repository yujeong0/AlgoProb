package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10158_개미 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int w = sc.nextInt();	// 가로
		int h = sc.nextInt();	// 세로
		int p = sc.nextInt();	// 출발점 x
		int q = sc.nextInt();	// 출발점 y
		int t = sc.nextInt();	// 이동시간
		
		if( ((p+t)/w) % 2 == 0) {	// 짝수면 x흐름이 0에서 w쪽
			p = (p+t) % w;
		} else {	// 홀수면 x흐름이 w에서 0쪽
			p = w - ((p+t) % w);
		}

		if( ((q+t)/h) % 2 == 0) {	
			q = (q+t) % h;
		} else {	
			q = h - ((q+t) % h);
		}
		
		System.out.println(p + " " + q);
		sc.close();
		
	}	// end of main
	
	
//	아래처럼 풀면 t가 2억이라서 절대 시간 안에 못 품.. 그래서 위처럼 개미가 x축 y축 왔다갔다하는 성질을 이용해서 수식으로 시간 줄인다... 
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		int w = sc.nextInt();	// 가로
//		int h = sc.nextInt();	// 세로
//		int p = sc.nextInt();	// 출발점 x
//		int q = sc.nextInt();	// 출발점 y
//		int t = sc.nextInt();	// 이동시간
//		
//		int[][] mode = {	// {x증가량, y증가량}
//				{1, 1},
//				{-1, -1},
//				{+1, -1},
//				{-1, 1}
//		};
//		
//		// 모드 0으로 1초 이동
//		p += mode[0][0];
//		q += mode[0][1];
//		
//		int curMode = 0;	// 초기모드는 오른쪽 위로 가는 모드0
//		for (int time = 1; time < t; time++) {
//			if(p == w || p == 0 ||q == h || q == 0) {
//				switch(curMode) {
//				case 0:
//					if(p == w && q == h) curMode = 1;
//					else if(q == h) curMode = 2;
//					else if(p == w) curMode = 3;
//					break;
//				case 1:
//					if(p == 0 && q == 0) curMode = 0;
//					else if(q == 0) curMode = 3;
//					else if(p == 0) curMode = 2;
//					break;
//				case 2:
//					if(p == w && q == 0) curMode = 3;
//					else if(q == 0) curMode = 0;
//					else if(p == w) curMode = 1;
//					break;
//				case 3:
//					if(p == 0 && q == h) curMode = 2;
//					else if(q == h) curMode = 1;
//					else if(p == 0) curMode = 0;
//					break;
//				}
//			}
//			
//			p += mode[curMode][0];
//			q += mode[curMode][1];
//		}
//		
//		System.out.println(p + " " + q);
//		sc.close();
//	}	// end of main
}
