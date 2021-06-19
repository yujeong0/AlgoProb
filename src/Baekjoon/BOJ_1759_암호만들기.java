package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static int L, C;
	static char[] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[L];
		input = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		solve(0, 0, 0, 0);
		
	} // main
	
	static char[] arr;
	private static void solve(int start, int cnt, int vCnt, int cCnt) {	// vCnt : 모음개수, cCnt : 자음개수
		if(cnt == L) {
			if(vCnt < 1 || cCnt < 2) return;
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < L; j++) {
				sb.append(arr[j]);
			}
			System.out.println(sb.toString());
			return;
		}
		for (int i = start; i < C; i++) {
			arr[cnt] = input[i];
			boolean isMo = isMo(input[i]);
			solve(i+1, cnt+1, isMo? vCnt+1 : vCnt, isMo? cCnt : cCnt+1);
		}
	}
	
	static boolean isMo(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return true;
		}
		return false;
	}
}
