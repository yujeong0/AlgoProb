package Baekjoon.삼성SW역량;

import java.util.Scanner;

public class Baekjoon_16637_괄호추가하기 {

	static char[] input;
	static int N;
	static int MAX;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		input = sc.nextLine().toCharArray();
		
		MAX = input[0] - '0';
		if(N == 1) {
			System.out.println(MAX);
		}
		else {
			for (int i = 1; i < N-1; i+=2) {
				switch(input[i]) {	// 홀수번쨰는 연산자
				case '+':
					MAX += input[i+1]-'0';
					break;
				case '-':
					MAX -= input[i+1]-'0';
					break;
				case '*':
					MAX *= input[i+1]-'0';
					break;
				}
			}	// 괄호 하나도 안 넣은 계산 끝!
			if(N == 3) {
				System.out.println(MAX);
			}
			else {
				int sum = input[0]-'0';
				switch(input[1]) {	// 홀수번쨰는 연산자
				case '+':
					sum += input[2]-'0';
					break;
				case '-':
					sum -= input[2]-'0';
					break;
				case '*':
					sum *= input[2]-'0';
					break;
				}
				
				// index 0과 1을 괄호처리한 것
				solve(3, sum, true);
				solve(3, sum, false);
				
				// index 0과 1을 괄호처리하지 않은 것
				solve(1, input[0]-'0', true);
				solve(1, input[0]-'0', false);
				
				System.out.println(MAX);
			}
		}
	} // main
	
	private static void solve(int cur, int sum, boolean brack) {
		if(cur >= N || cur+1 >= N) {	// 마지막 연산자 인덱스까지 오면 그만~
			if(MAX < sum) MAX = sum;
			return;
		}
		
		if(brack && cur+3 < N) {	// 괄호있다면 sum input[cur](+ - *)  (input[cur+1]input[cur+2]input[cur+3])
			int tmpSum = input[cur+1]-'0';
			switch(input[cur+2]) {	
			case '+':
				tmpSum += input[cur+3]-'0';
				break;
			case '-':
				tmpSum -= input[cur+3]-'0';
				break;
			case '*':
				tmpSum *= input[cur+3]-'0';
				break;
			}
			
			switch(input[cur]) {	
			case '+':
				sum += tmpSum;
				break;
			case '-':
				sum -= tmpSum;
				break;
			case '*':
				sum *= tmpSum;
				break;
			}
			
			// 괄호없이 그냥가기
			solve(cur+4, sum, false);
			// 괄호 추가
			solve(cur+4, sum, true);
			
		} else if(!brack) { // brack == false라면 sum input[cur] input[cur+1]
			int tmpSum = sum;
			switch(input[cur]) {	
			case '+':
				tmpSum += input[cur+1]-'0';
				break;
			case '-':
				tmpSum -= input[cur+1]-'0';
				break;
			case '*':
				tmpSum *= input[cur+1]-'0';
				break;
			}
			
			// 괄호없이 그냥가기
			solve(cur+2, tmpSum, false);
			// 괄호 추가
			solve(cur+2, tmpSum, true);
		}
	}
	
}
