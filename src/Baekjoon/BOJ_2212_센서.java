package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212_센서 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] sensors = new int[N];
		int[] diff = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		
		if(K == N) {
			System.out.println(0);
			return;
		}
		
		Arrays.sort(sensors);
		
		for (int i = 0; i < N-1; i++) {
			diff[i] = sensors[i+1] - sensors[i];
		}
		Arrays.sort(diff);
		
		int SUM = 0;
		for (int i = 0; i < diff.length-(K-1); i++) {
			SUM += diff[i];
		}
		
		System.out.println(SUM);
	}
	
}
