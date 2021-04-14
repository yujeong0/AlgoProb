package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class 모의SW_5658_보물상자비밀번호 {
	static int N, K;
	static Set<String> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			set = new HashSet<>();	
			
			String originalStr = br.readLine();
			String str = originalStr;
			for (int i = 0; i < N/4; i++) {
				for (int j = 0; j <= str.length()-(N/4); j+=N/4) {	// N/4만큼 떼서 set에 넣기
					set.add(str.substring(j, j+N/4));
				}
				char[] arr = str.toCharArray();
				char end = arr[arr.length-1];
				for (int j = arr.length-1; j >= 1; j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = end;
				str = String.copyValueOf(arr);
//				System.out.println(str);
			} // 회전횟수
			
			// set 완료했으니 모두 10진수로 바꿔서 정렬해야함
			Integer[] arr = new Integer[set.size()];
			int idx = 0;
			for(String s : set) {
				int n = 1;
				int sum = 0;
				for (int i = s.length()-1; i >= 0; i--) {
					char c = s.charAt(i);
					int number;
					switch(c) {
					case 'A':
						number = 10;
						break;
					case 'B':
						number = 11;
						break;
					case 'C':
						number = 12;
						break;
					case 'D':
						number = 13;
						break;
					case 'E':
						number = 14;
						break;
					case 'F':
						number = 15;
						break;
					default:
						number = c - '0';
						break;
					}
					sum += number * n;
					n *= 16;
				}
				arr[idx++] = sum;
			}
			
			Arrays.sort(arr, Collections.reverseOrder());
			
			sb.append("#").append(testcase).append(" ").append(arr[K-1]).append("\n");
		} //tc
		
		System.out.println(sb.toString());
				
	} // main
	
} //class