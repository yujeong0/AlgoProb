package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baekjoon_2605_줄세우기 {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	// 학생 수
		int[] numbers = new int[N];	// 각 학생이 뽑은 번호
		
		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(list.size() - numbers[i], i+1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i: list)
			sb.append(i + " ");
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	
	
}
