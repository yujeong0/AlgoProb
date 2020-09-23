package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_14696_딱지놀이 {
	static class Number implements Comparable<Number> {
		int num;
		Number(int num){
			this.num = num;
		}
		
		@Override
		public int compareTo(Number o) {
			return o.num - this.num;
		}

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		
		PriorityQueue<Number> Aqueue = new PriorityQueue<>();
		PriorityQueue<Number> Bqueue = new PriorityQueue<>();
		
		for (int round = 0; round < N; round++) {
			st = new StringTokenizer(br.readLine(), " ");
			int AvalCnt = Integer.parseInt(st.nextToken());	// A의 그림 총 개수
			for (int i = 0; i < AvalCnt; i++) {
				Aqueue.add(new Number(Integer.parseInt(st.nextToken())));
			}

			st = new StringTokenizer(br.readLine(), " ");
			int BvalCnt = Integer.parseInt(st.nextToken());	// B의 그림 총 개수
			for (int i = 0; i < BvalCnt; i++) {
				Bqueue.add(new Number(Integer.parseInt(st.nextToken())));
			}
			
			int a, b;
			boolean end = false;
			while(!Aqueue.isEmpty() && !Bqueue.isEmpty()) {
				a = Aqueue.poll().num;
				b = Bqueue.poll().num;
				
				if(a>b) {
					sb.append("A\n");
					end = true;
					break;
				}else if (a<b) {
					sb.append("B\n");
					end = true;
					break;
				}
			}
			
			if(!end) {
				// A나 B에 아직 남아있는 경우
				if(!Aqueue.isEmpty() && Bqueue.isEmpty()) sb.append("A\n");
				else if(!Bqueue.isEmpty() && Aqueue.isEmpty()) sb.append("B\n");
				
				else sb.append("D\n");	// 무승부
			}
			
			Aqueue.clear();
			Bqueue.clear();
		}	// end of round
		
		System.out.println(sb.toString());
		
	}	// end of main
	
}
