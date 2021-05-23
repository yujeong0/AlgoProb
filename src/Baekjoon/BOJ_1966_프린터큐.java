package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1966_프린터큐 {
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int[] count = new int[10];
			Queue<int[]> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				count[n]++;
				q.offer(new int[] {i, n});
			}
			
			int ans = 0;
		out:while(!q.isEmpty()) {
				int[] n = q.poll();
				for (int i = 9; i > 0; i--) {
					if(i == n[1]) {
						ans++;
						count[i]--;
						if(target == n[0]) {
							System.out.println(ans);
							break out;
						}
						break;
					}
					if(count[i] > 0) {
						q.offer(n);
						break;
					}
				}
			} // while
		
		} // T
	} // main
}
