package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int point1 = 0, point2 = 0, sum = 0, ANS = 100001;
		while(true) {
			while(sum >= S) {	// 합이 S보다 작을 때까지 앞 포인터를 올린다.
				ANS = Math.min(ANS, point2-point1);
				sum -= arr[point1++];
			}
			if(point2 == N) break;
			sum += arr[point2++];	// S 보다 클 때까지 뒤 포인터를 올린다.
		} // while
		
		System.out.println(ANS == 100001? 0 : ANS);
	} // main
}
