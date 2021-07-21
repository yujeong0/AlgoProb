package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class BOJ_10866_덱 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String msg = st.nextToken();
			switch(msg) {
			case "push_front": {
				int num = Integer.parseInt(st.nextToken());
				deque.addFirst(num);
				break;
			}
			case "push_back": {
				int num = Integer.parseInt(st.nextToken());
				deque.addLast(num);
				break;
			}
			case "pop_front": {
				Integer f = deque.pollFirst();
				if(f == null) {
					System.out.println(-1);
				} else {
					System.out.println(f);
				}
				break;
			}
			case "pop_back": {
				Integer f = deque.pollLast();
				if(f == null) {
					System.out.println(-1);
				} else {
					System.out.println(f);
				}
				break;
			}
			case "size": {
				System.out.println(deque.size());
				break;
			}
			case "empty": {
				System.out.println(deque.isEmpty()? 1 : 0);
				break;
			}
			case "front": {
				try {
					Integer f = deque.getFirst();
					System.out.println(f);
				} catch(NoSuchElementException e) {
					System.out.println(-1);
				}
				break;
			}
			case "back": {
				try {
					Integer f = deque.getLast();
					System.out.println(f);
				} catch(NoSuchElementException e) {
					System.out.println(-1);
				}
				break;
			}
			}
		}
		
	}
}
