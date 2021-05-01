package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_5430_AC {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			Deque<Integer> deque = new LinkedList<>();
			String order = br.readLine().replaceAll("RR", "");
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
			for (int j = 0; j < N; j++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			boolean isReverse = false;
			boolean isEnd = false;
		out:for (int j = 0; j < order.length(); j++) {
				char c = order.charAt(j);
				switch(c) {
				case 'R':
					isReverse = !isReverse;
					break;
				case 'D':
					if(deque.isEmpty()) {
						sb.append("error\n");
						isEnd = true;
						break out;
					}
					if(isReverse) deque.pollLast();
					else deque.pollFirst();

					break;
				}
				
			}
			
			if(!isEnd) {
				sb.append("[");
				if(isReverse) {
					while(!deque.isEmpty()) {
						sb.append(deque.pollLast());
						if(!deque.isEmpty()) sb.append(',');
					}
				}
				else {
					while(!deque.isEmpty()) {
						sb.append(deque.pollFirst());
						if(!deque.isEmpty()) sb.append(',');
					}
				}
				sb.append("]\n");
			}
		} // T
	
		System.out.println(sb.toString());
	}
}
