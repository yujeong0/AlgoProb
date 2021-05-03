package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_10773_제로 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)	list.remove(list.size()-1);
			else list.add(n);
		}
	
		int ans = 0;
		for(int n : list) {
			ans += n;
		}
		System.out.println(ans);
	}
}
