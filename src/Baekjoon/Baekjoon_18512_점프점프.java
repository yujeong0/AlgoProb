package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baekjoon_18512_점프점프 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int Y = sc.nextInt();
		int P1 = sc.nextInt();
		int P2 = sc.nextInt();
		
		if(P1 == P2) {
			System.out.println(P1);
			return;
		}
		
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		
		for (int i = 0; i < 10000; i++) {
			list1.add(P1);
			list2.add(P2);
			P1 += X;
			P2 += Y;
		}

		for (int i = 0; i < 10000; i++) {
			if(list1.contains(list2.get(i))) {
				System.out.println(list2.get(i));
				return;
			}
		}
		
		System.out.println(-1);
		
	}
	
}
