package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실배정 {
	public static void main(String[] args) throws Exception {
		new BOJ_11000_강의실배정().solve();
	}// main

	void solve() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for (int T = 0; T < N; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			arr[T][0] = s;
			arr[T][1] = t;
		} // t
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		pq.add(arr[0][1]);
		for (int i = 1; i < N; i++) {
			if(pq.peek() <= arr[i][0]) {
				pq.poll();
			}
			pq.add(arr[i][1]);
		}
		
		System.out.println(pq.size());
	}
}
