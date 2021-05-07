package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620_나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws Exception {
		Map<String, Integer> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		for (int i = 1; i <= N; i++) {
			map1.put(arr[i-1], i);
		}
		for (int i = 1; i <= N; i++) {
			map2.put(i, arr[i-1]);
		}
		
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if(Character.isDigit(s.charAt(0))) {
				System.out.println(map2.get(Integer.parseInt(s)));
			}
			else System.out.println(map1.get(s));
		}
	}
}
