package kakao.blind2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test02 {

	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = { 2,3,4 };
		System.out.println(Arrays.toString(solution(orders, course)));
	}
	
	static Set<Character> set = new HashSet<>();
	static List<String> answer = new ArrayList<>();
	static int len;
	static boolean[] selected;
	static String alpha;	// orders 에 포함되어 있는 알파벳들
	static int[] sCourse;
	static String[] sOrders;

	public static String[] solution(String[] orders, int[] course) {
		sOrders = orders;
		sCourse = course;
		
		makeSet(orders, course);
		subset(0);
		
//		System.out.println(answer.toString());
		Collections.sort(answer);
		
		return answer.toArray(new String[answer.size()]);
	}

	private static void makeSet(String[] orders, int[] course) {
        char[] arr;
        for (int i = 0; i < orders.length; i++) {
			arr = orders[i].toCharArray();
			for (int j = 0; j < arr.length; j++) {
				set.add(arr[j]);
			}
		}
        
        len = set.size();
        selected = new boolean[len];
        
        StringBuilder sb = new StringBuilder();
        for(char c : set) {
//        	System.out.print(c + " ");
        	sb.append(c);
        }
        alpha = sb.toString();
	}
	
	private static void subset(int cnt) {
		if(cnt == len) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < selected.length; i++) {
				if(selected[i] == true) {
					sb.append(alpha.charAt(i));
				}
			}
			if(hasNumber(sb.length())) {	// course에 있는 숫자면
//				System.out.println(sb.toString());
				if(isOrderedOverTwo(sb.toString())){	// 2명 이상이 주문한 조합이면	
					answer.add(sb.toString());
				}
			}
			
			return;
		}
		
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}
	
	private static boolean hasNumber(int len) {
		for (int i = 0; i < sCourse.length; i++) {
			if(sCourse[i] == len) return true;
		}
		return false;
	}
	
	private static boolean isOrderedOverTwo(String subset) {
		boolean isContain;
		int count = 0;	// 주문한 손님 수
		for (int i = 0; i < sOrders.length; i++) {
			isContain = true;
			for (int j = 0; j < subset.length(); j++) {
				if(!sOrders[i].contains(Character.toString(subset.charAt(j)))) {	// 해당 손님이 j 음식 주문했는가?
					isContain = false;
					break;
				}
			}
			if(isContain) count++;
			if(count >= 2) return true;
		}
		
		return false;
	}
}
