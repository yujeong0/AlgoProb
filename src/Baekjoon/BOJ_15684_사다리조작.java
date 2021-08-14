package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {
	static int N, M, H;
	static int[][] ori_ledder;
	
	static class Ledder implements Comparable<Ledder>{
		int cnt; // 사다리 추가로 놓은 개수
		int[][] ledder;	// 사다리 상태
		int[] last; // 마지막으로 사다리 놓은 위치
		public Ledder(int cnt, int[][] ledder, int[] last) {
			this.cnt = cnt;
			this.last = last;
			this.ledder = ledder;
		}
		@Override
		public int compareTo(Ledder o) {
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ori_ledder = new int[H+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			ori_ledder[a][b] = 1;
		}
		
		if(check(ori_ledder)) {	// 자기 번호로 가는 사다리가 완성되면
			System.out.println(0);
		} else {
			if(N == 0) System.out.println(-1);
			else System.out.println(solve());
		}
		
	} // main
	
	private static int solve() {
		PriorityQueue<Ledder> pq = new PriorityQueue<>();
		pq.offer(new Ledder(0, ori_ledder, new int[] {0,1}));
		
		Ledder p;
		while(!pq.isEmpty()) {
			p = pq.poll();
			
			// 사다리 가능한 곳 하나에 놓는다.
			int[][] copy = new int[H+1][N+1];
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= N; j++) {
					copy[i][j] = p.ledder[i][j];
				}
			}
			// 사다리 가능한 곳 찾기
			int i = p.last[0];
			int j = p.last[1];
			
			if(j == N-1 && i == H) {	// 사다리를 더 이상 놓을 수 없는 경우는 안 됨
				continue;
			}
			
			boolean canPutLedder = false;
			while(true) {
				i++;
				if(i == H+1) {
					i = 1;
					j++;
					if(j == N) {	// 사다리를 더 이상 놓을 수 없는 경우는 안 됨
						break;
					}
				}
				if(copy[i][j] == 0) {
					if(j == 1) {
						if(j < N-1) {
							if(copy[i][j+1] == 0) {
								canPutLedder = true;
								break;
							}
						} else {
							canPutLedder = true;
							break;
						}
					} else if(j > 1 && copy[i][j-1] == 0) {
						if(j < N-1) {
							if(copy[i][j+1] == 0) {
								canPutLedder = true;
								break;
							}
						} else {
							canPutLedder = true;
							break;
						}
							
					}
				}
			}
			if(!canPutLedder) continue;
			copy[i][j] = 1;
			
			if(check(copy)) {	// 자기 번호로 가는 사다리가 완성되면
				return p.cnt+1;
			}
			
			pq.offer(new Ledder(p.cnt, p.ledder, new int[] {i, j}));
			if(p.cnt+1 == 3) {	// 추가 사다리를 이미 2개 + 1 놓은 경우는 안 됨
				continue;
			}
			pq.offer(new Ledder(p.cnt+1, copy, new int[] {i, j}));
		}
		
		return -1;
	} // solve

	private static boolean check(int[][] arr) { // 자기 번호로 가는 지 체크하는 함수
		for (int num = 1; num <= N; num++) {
			
			int i = 1, j = num;
			while(true) {
				if(i == H+1) break;
				
				if(j > 1 && arr[i][j-1] == 1) { // 왼쪽에 있는 상황
					j--;
				} else if(arr[i][j] == 1){	// 오른쪽에 있는 상황
					j++;
				}
				i++;
			} // while
			
			if(j != num) return false;
		}
		
		return true;
	} // check
	
}
