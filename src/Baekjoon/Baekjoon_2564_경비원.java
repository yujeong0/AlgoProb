//package Baekjoon;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Baekjoon_2564_경비원 {
//
//	static int sum  = 0, N, M;
//	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
//	static boolean[][] visited;
//	static int[] dongpos;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		M = sc.nextInt();	// 가로길이
//		N = sc.nextInt();	// 세로길이
//		int[][] grid = new int[N][M];
//		
//		List<int[]> list = new ArrayList<>();
//		int numStore = sc.nextInt();	// 상점 개수
//		int d, p;
//		// 동근이의 위치는 numStore로 표시됨
//		for (int i = 0; i <= numStore; i++) {
//			d = sc.nextInt();	// 방향
//			p = sc.nextInt();	// 거리
//			
//			switch(d) {
//			case 1:
//				grid[0][p] = i;
//				list.add(new int[] {0, p});
//				break;
//			case 2:
//				grid[N-1][p] = i;
//				list.add(new int[] {N-1, p});
//				break;
//			case 3:
//				grid[p][0] = i;
//				list.add(new int[] {p, 0});
//				break;
//			case 4:
//				grid[p][M-1] = i;
//				list.add(new int[] {p, M-1});
//				break;
//			}
//		}
//		
//		sc.close();
//		
//		dongpos = list.remove(numStore);	// 리스트의 마지막 위치가 동근이 위치
//		for(int[] pos: list) {
//			// 같은 방향에 존재하는 경우
////			if((dongpos[0] == 0 && pos[0] == 0) || (dongpos[0] == N-1 && pos[0] == N-1)) {	// 둘다 북/남
////				sum += Math.abs(dongpos[1] - pos[1]);
////			}
//////			else if(dongpos[0] == N-1 && pos[0] == N-1) {	// 남
//////				sum += Math.abs(dongpos[1] - pos[1]);
//////			}
////			else if((dongpos[1] == 0 && pos[1] == 0) || (dongpos[1] == M-1 && pos[1] == M-1)) {		// 둘 다 서/동
////				sum += Math.abs(dongpos[0] - pos[0]);
////			}
//////			else if(dongpos[1] == M-1 && pos[1] == M-1) {	//동
//////				sum += Math.abs(dongpos[0] - pos[0]);
//////			}
//			
//			// 다른 방향에 존재하는 경우
////			else 
//				sum += getMinDistance(pos);
//		}
//		
//		System.out.println(sum);
//		
//	}	// end of main
//	
//	private static int getMinDistance(int[] target) {
//		if((dongpos[0] == 0 && target[0] == N-1) || (dongpos[0] == N-1 && target[0] == 0)) {	// 남북으로 마주보고 있을 때
//			return Math.min(dongpos[1]+N+target[1], M-dongpos[1]+N+M-target[1]);
//		}
//		else if((dongpos[1] == 0 && target[1] == M-1) || (dongpos[1] == M-1 && target[1] == 0)) {	// 동서로 마주보고 있을 때
//			return Math.min(dongpos[0]+M+target[0], N-dongpos[0]+M+N-target[0]);
//		}
//		else {	// 마주보고있지 x
//			return Math.abs(dongpos[0]-target[0]) + Math.abs(dongpos[1]-target[1]);
//		}
//	}
//	
//	
////	private static int getMinDistance(int[] target) {
////		int left, right;
////		if(dongpos[0] == 0 || dongpos[0] == N-1) {	// 북 or 남
////			// 왼쪽
////			left = dongpos[1];
////			if(target[1] == 0) {	// 서쪽
////				left += target[0];
////			}
////			else if(target[0] == N-1) {	// 남
////				left += N + target[1];
////			}
////			else if(target[1] == M-1) {	// 동
////				left += N + M + (N - target[1]);
////			}
////			// 오른쪽
////			right = M - dongpos[1];
////			if(target[1] == 0) {	// 서쪽
////				right += N + M + (N - target[1]);
////			}
////			else if(target[0] == N-1) {	// 남
////				right += N + (M-target[1]);
////			}
////			else if(target[1] == M-1) {	// 동
////				right += target[0];
////			}
////			
////			return left > right? right : left;
////		}
////		else { //if(dongpos[1] == 0 || dongpos[1] == M-1) {	// 서 or 동
////			// 왼쪽
////			left = dongpos[0];
////			if(target[0] == 0) {	// 북
////				left += target[1];
////			}
////			else if(target[0] == N-1) {	// 남
////				left += M + N + (M-target[1]);
////			}
////			else if(target[1] == N-1) {	// 동
////				left += M + target[0];
////			}
////			// 오른쪽
////			right = N - dongpos[0];
////			if(target[0] == N-1) {	// 남
////				right += target[1];
////			}
////			else if(target[1] == N-1) {	// 동
////				right += M + (N-target[0]);
////			}
////			else if(target[0] == N-1) {	// 북
////				right += M + N + (M-target[0]);
////			}
////			
////			return left > right? right : left;
////		}
////	}
////	
////	private boolean isInBound(int x, int y) {
////		if(x >= N || x < 0 || y < 0 || y >= M)
////			return false;
////		
////		return true;
////	}
//	
//}
