//package Baekjoon;
//
//import java.util.Scanner;
//
//public class Baekjoon_15683_감시 {
//	static int N, M;
//	static String[][] grid;
//	static int ANSWER = Integer.MAX_VALUE;
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		N = sc.nextInt();
//		M = sc.nextInt();
//		grid = new String[N][M];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				grid[i][j] = sc.next();
//			}
//		}
//		
//		solve();
//		
//		sc.close();
//	}
//	
//	private static void solve() {
//		int x=-1,y=-1;
//		boolean flag = false;
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if(!grid[i][j].equals("0") || !grid[i][j].equals("6")) {
//					x = i;
//					y = j;
//					switch(grid[x][y]) {
//					case "1":
//						CCTV1(x, y);
//						break;
//					case "2":
//						CCTV2(x, y);
//						break;
//					case "3":
//						CCTV3(x, y);
//						break;
//					case "4":
//						CCTV4(x, y);
//						break;
//					case "5":
//						CCTV5(x, y);
//						break;
//					}
//					flag = true;
//					break;
//				}
//			}
//			if(flag) break;
//		}
//		
//	}
//	
//	private static void CCTV1(int startx, int starty) {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				
//				
//			}
//		}
//	}
//	private static void CCTV2(int startx, int starty) {
//		
//	}
//	private static void CCTV3(int startx, int starty) {
//		
//	}
//	private static void CCTV4(int startx, int starty) {
//		
//	}
//	private static void CCTV5(int startx, int starty) {
//		
//	}
//	
//}
