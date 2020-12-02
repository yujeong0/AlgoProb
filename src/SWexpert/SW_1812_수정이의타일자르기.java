package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_1812_수정이의타일자르기 {
	
	static int N, m;
	static int[] arrK;
	static int[] calTwo = new int[31];
	static int[] M;
	public static void main(String[] args) throws Exception {
		
		int num = 1;
		for (int i = 0; i < 31; i++) {
			calTwo[i] = num;
			num *= 2;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			arrK = new int[N];
			for (int i = 0; i < N; i++) {
				arrK[i] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += calTwo[arrK[i]];
			}
			
			if(sum <= m) {
				sb.append("#").append(testcase).append(" 1\n");
			}
			else {
				M = new int[m+1];
				Arrays.sort(arrK);
				sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
			}
			
		} // tc
		
		System.out.println(sb.toString());
		
	} // main
	
	private static int solve() {
		int count = 1;
		boolean success;
		for (int i = N-1; i >= 0; i--) {
			int size = calTwo[arrK[i]];
			success = false;
			for (int j = 1; j <= m-size+1; j++) {
				boolean canGO = true;
				for(int k = j; k < j+size; k++) {
					if(m - M[k] < size) {
						canGO = false;
						break;
					}
				}
				if(canGO) {
					for(int k = j; k < j+size; k++) {
						M[k] += size;
					}
					success = true;
					break;
				}
			}
			if(!success) {
				count++;
				M = new int[m+1];
			}
		}
		
		return count;
	}
	
	
}
