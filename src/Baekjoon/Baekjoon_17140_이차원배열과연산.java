//package Baekjoon;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class Baekjoon_17140_이차원배열과연산 {
//
//	static int r, c, k, result = 0;
//	static int[][] arr = new int[100][100];
//	static int[] countingNum = new int[100];
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		r = Integer.parseInt(st.nextToken());
//		c = Integer.parseInt(st.nextToken());
//		k = Integer.parseInt(st.nextToken());
//		
//		for (int i = 0; i < 3; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < 3; j++) {
//				arr[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		
//		solve();
//		System.out.println(result);
//		
//	}
//	
//	private static void solve() {
//		List<Integer> list = new ArrayList<>();
//		
//		while(true) {
//			if(arr[r][c] == k) return;
//			if(true) {	// R연산
//				for (int i = 0; i < 100; i++) {
//					for (int j = 0; j < 100; j++) {
//						if(arr[i][j] == 0) break;
//						countingNum[arr[i][j]]++;
//					}
//					makeArr();
//				}
//				
//			}
//			else {
//				
//			}
//			
//			
//		}
//	}
//	
//	private static int[] makeArr() {	// 정렬된 결과 반환
//		List<int[]> list = new ArrayList<>();
//		for (int i = 0; i < 100; i++) {
//			if(countingNum[i] == 0) continue;
//			
//			list.add(new int[] {i, countingNum[i]});
//		}
//		
//		// 정렬
//		Collections.sort(list, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				if(o1[1] != o2[1]) return o1[1] - o2[1];
//				return o1[0] - o2[0];
//			}
//		});
//		
//		
//		List<Integer> sortedList = new ArrayList<>();
//		for(int[] tmp:list) {
//			sortedList.add(tmp[0]);
//			sortedList.add(tmp[1]);
//		}
//		return sortedList.toArray(sortedList);
//	}
//	
////	private static void count() {	// 행, 열 크기 비교
////		int maxR = Integer.MAX_VALUE;
////		for (int i = 0; i < 100; i++) {
////			if(maxR < arr[i].length) maxR = arr[i].length;
////		}
////		int maxC = Integer.MAX_VALUE;
////		for (int j = 0; j < 100; j++) {
////			for (int i = 0; i < 100; i++) {
////				if(maxC < arr[i][j].length) maxC = arr[i].length;
////				
////			}
////		}
////	}
//}
