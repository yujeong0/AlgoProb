package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_11723_집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
//		Set<Integer> set = new HashSet<Integer>();
		boolean[] arr = new boolean[21];
		StringBuffer sb = new StringBuffer();
		int x;
		for (int t = 0; t < M; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "add":
				x = Integer.parseInt(st.nextToken());
				arr[x] = true;
//				set.add(x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				arr[x] = false;
//				set.remove(x);
				break;
			case "check":
				x = Integer.parseInt(st.nextToken());
//				System.out.println(arr[x]? 1 : 0);
				sb.append(arr[x]? 1 : 0).append("\n");
//				System.out.println(set.contains(x)? 1 : 0);
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				arr[x] = !arr[x];
//				if(set.contains(x)) set.remove(x);
//				else set.add(x);
				break;
			case "all":
				for (int i = 1; i <= 20; i++) {
					arr[i] = true;
				}
				break;
			case "empty":
				for (int i = 1; i <= 20; i++) {
					arr[i] = false;
				}
				break;
			}
		} // t
		
		System.out.println(sb.toString());
	}
}
