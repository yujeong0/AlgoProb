//package Baekjoon;
//
//import java.util.Scanner;
//
//public class Baekjoon_1074_Z {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		int N = sc.nextInt();
//		int r = sc.nextInt();
//		int c = sc.nextInt();
//		
//		int[][] grid = new int[(2*N)+1][(2*N)+1];
//		int x = 1, y = 1;
//		int count = 0;
//		while(true) {
//			if(x == r-1 & y == c-1) break;
//			
//			if(x % 2 != 0 && y % 2 == 0) {
//				x += 1;	y -= 1;
//				count++;
//			}
//			else if(x % 2 == 0 && y % 2 == 0) {
//				x += 1;	y -= 1;
//				count++;
//			}
//			else if(x % 2 == 0 && y % 4 == 0) {
//				x += 1;	y -= 4;
//				count++;
//			}
//			else {
//				y += 1;
//				count++;
//			}
//			
//		}
//		System.out.println(count);
//		
//		sc.close();
//	}
//}
