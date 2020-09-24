//package Baekjoon;
//
//import java.util.Scanner;
//
//public class Baekjoon_10157_자리배정 {
//	이거 좌표 엉망인 코드다!! v2 좌표로 가야됨
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		int C = sc.nextInt();	// 가로크기
//		int R = sc.nextInt();	// 세로크기
//		int K = sc.nextInt();	// 대기번호
//		
//		sc.close();
//
//		if(K > R*C) System.out.println("0");
//		else {
//			int[][] grid = new int[R+1][C+1];
//			
//			int count = 0, x = 1, y = 1;
//			while(true) {
//				for (; x <= R; x++) {
//					if(y < 1) y++;
//					if(grid[x][y] != 0) {
//						x--;
//						break;
//					}
//					grid[x][y] = ++count;
//					if(count == K) {
//						System.out.println(x + " " + y);
////						printGrid(R,C,grid);
//						return;
//					}
//				}
//				y++;
//				for (; y <= C; y++) {
//					if(x > R) x--;
//					if(grid[x][y] != 0) {
//						y--;
//						break;
//					}
//					grid[x][y] = ++count;
//					if(count == K) {
//						System.out.println(x + " " + y);
////						printGrid(R,C,grid);
//						return;
//					}
//				}
//				x--;
//				for (; x >= 1; x--) {
//					if(y > C) y--;
//					if(grid[x][y] != 0) {
//						x++;
//						break;
//					}
//					grid[x][y] = ++count;
//					if(count == K) {
//						System.out.println(x + " " + y);
////						printGrid(R,C,grid);
//						return;
//					}
//				}
//				y--;
//				for (; y >= 1; y--) {
//					if(x < 1) x++;
//					if(grid[x][y] != 0) {
//						y++;
//						break;
//					}
//					grid[x][y] = ++count;
//					if(count == K) {
//						System.out.println(x + " " + y);
////						printGrid(R,C,grid);
//						return;
//					}
//				}
//				x++;
//				
//			}	// end of while
//			
//		}
//	}	// end of main
//	
////	static void printGrid(int R, int C, int[][] grid) {
////		for (int i = 1; i <= R; i++) {
////			for (int j = 1; j <= C; j++) {
////				System.out.print(grid[i][j] + " ");
////			}
////			System.out.println();
////		}
////		System.out.println();
////	}
//	
//}
