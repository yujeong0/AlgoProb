package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1316_그룹단어체커 {
	public static void main(String[] args) throws Exception {

		// 49 ~ 74
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] arr = st.nextToken().toCharArray();
			boolean[] check = new boolean[26];
			boolean isEnd = false;
			char prev = '0';
			for (int j = 0; j < arr.length; j++) {
				if(!check[arr[j]-'0' - 49]) {
					check[arr[j]-'0' - 49] = true;
					prev = arr[j];
				}
				else {
					if(prev != arr[j]) {
						isEnd = true;
						break;
					}
				}
			}
			if(!isEnd) ans++;
		}
		
		System.out.println(ans);
	}
}
