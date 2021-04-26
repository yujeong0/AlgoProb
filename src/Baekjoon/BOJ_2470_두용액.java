package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(nums);
		
		int head = 0, tail = N-1;
		long MIN = Long.MAX_VALUE;
		long val1 = 0, val2 = 0;
		long SUM;
		do {
			if(head >= tail) break;
			SUM = nums[head] + nums[tail];

//			if(Math.abs(SUM) > MIN) break;
			if(MIN > Math.abs(SUM)) {
				MIN = Math.abs(SUM);
				val1 = nums[head];
				val2 = nums[tail];
			}
			if(MIN == 0) break;
			else if(SUM > 0) tail--;
			else if(SUM < 0) head++;
		} while(true);
		
		System.out.println(val1 + " " + val2);
	} // main
	
}
