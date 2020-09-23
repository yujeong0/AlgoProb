package Baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Baekjoon_2635_수이어가기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int maxCount = 0, count, prev, cur;
		List<Integer> list = new ArrayList<>();
		List<Integer> maxList = null;
		for (int i = N; i >= 1; i--) {
			prev = N;
			cur = i;
			
			list.add(prev);
			list.add(cur);

			int num;
			while(true) {
				num = prev - cur;
				if(num < 0) break;
				list.add(num);
				prev = cur;
				cur = num;
			}
			
			if(maxCount < list.size()) {
				maxCount = list.size();
				maxList = new ArrayList<>(list);
			}
			
			list.clear();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(maxCount + "\n");
		for(int num: maxList)
			sb.append(num + " ");
		
		System.out.println(sb.toString());
		sc.close();
	}	// end of main

}
