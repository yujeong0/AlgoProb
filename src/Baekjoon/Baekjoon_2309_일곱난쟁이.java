package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_2309_일곱난쟁이 {

	static boolean[] selected = new boolean[9];
	static int[] people = new int[9];
	static int[] numbers = new int[7];
	static boolean end = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 9; i++) {
			people[i] = sc.nextInt();
		}
		
		combination(0, 0);
		
		sc.close();
	}
	
	private static void combination(int start, int cnt) {
		if(cnt == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += people[numbers[i]];
				if(sum > 100) return;
			}
			if(sum != 100) return;
			
			int[] height = new int[7];
			for (int i = 0; i < 7; i++) {
				height[i] = people[numbers[i]];
			}
			Arrays.sort(height);
			for (int i = 0; i < 7; i++) {
				System.out.println(height[i]);
			}
			
			end = true;
			return;
		}
		
		for (int i = start; i < 9; i++) {
			selected[i] = true;
			numbers[cnt] = i;
			combination(i+1, cnt+1);
			if(end) return;
			selected[i] = false;
		}
		
	}
	
}
