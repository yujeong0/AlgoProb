package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SW_Professional_5643_키순서 {
	
	static Set<Integer>[] big, small;
	
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			big = new HashSet[N+1];	// 나보다 큰 애들
			small = new HashSet[N+1];	// 나보다 작은 애들
			M = Integer.parseInt(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				big[i] = new HashSet<>();
				small[i] = new HashSet<>();
			}
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				big[a].add(b);
				small[b].add(a);
				
				for (int n : small[a]) {
					small[b].add(n);
				}
				for (int n : big[b]) {
					big[a].add(n);
				}
				for (int n1 : small[a]) {
					for (int n : big[a]) {
						big[n1].add(n);
					}
				}
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
//				System.out.println("big " + i);
//				System.out.println(big[i]);
//				System.out.println();
//				System.out.println("small " + i);
//				System.out.println(small[i]);
				if(big[i].size() + small[i].size() + 1 == N) {
					cnt++;
				}
			}
			sb.append("#").append(testcase).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
} // class
