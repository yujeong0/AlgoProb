package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_4008_숫자만들기 {

	static int N, MAX, MIN;
	static int[] input = new int[13];
	static char[] operators = new char[13];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int tmp;
		char c;
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = Integer.MIN_VALUE;
			MIN = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int o = 0;
			for (int i = 0; i < 4; i++) {	// 연산자 개수만큼 저장 (+, -, *, /)
				tmp = Integer.parseInt(st.nextToken());

				if(i == 0)		c = '+';
				else if(i == 1)	c = '-';
				else if(i == 2)	c = '*';
				else			c = '/';
				
				for (int j = 0; j < tmp; j++) 
					operators[o++] = c;
				
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) 	// 숫자 저장
				input[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(operators, 0, N-1);	// 먼저 오름차순으로 정렬해놓고 permu 한 번 돌리고 정렬바꾸고 다시 돌리고 반복
			
			do {
				cal();
			} while(nextPermutation());
			
			sb.append("#" + testcase + " " + (MAX-MIN) +"\n");
		}	// end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
private static boolean nextPermutation() {
		
		int i = N-2;
		while(i>0 && operators[i-1] >= operators[i]) i--;
		if(i == 0) return false;	
		
		int j = N-2;
		while(operators[i-1] >= operators[j]) --j;
		
		swap(i-1, j);
		
		int k = N-2;
		while(i < k) {	
			swap(i++, k--);
		}
	
		return true;
	}
	
	public static void swap(int i, int j) {
		char temp = operators[i];
		operators[i] = operators[j];
		operators[j] = temp;
	}
	
	private static void cal() {
		int sum = input[0];
		int j = 1;
		for (int i = 0; i < N-1; i++) {
			if(operators[i] == '+') sum += input[j++];
			else if(operators[i] == '-') sum -= input[j++];
			else if(operators[i] == '*') sum *= input[j++];
			else if(operators[i] == '/') sum /= input[j++];
		}
		
		if(MAX < sum) MAX = sum;
		if(MIN > sum) MIN = sum;
		
	}
} // end of class
