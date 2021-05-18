package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 메뉴리뉴얼 {
    public String[] solution(String[] orders, int[] course) {
    	staticOrders = orders;
    	
    	Arrays.sort(orders);
    	for (int j = 0; j < orders.length; j++) {
    		N = orders[j].length();
    		selected = new char[N];
    		for (int k = 0; k < course.length; k++) {
    			if(N < course[k]) break;
    			combination(0, 0, course[k], j);
    		}
    	}
    	
    	String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
        
        return answer;
    }
    
    Set<String> set = new HashSet<>();
    List<String> list = new ArrayList<>();
    int N;
    char[] selected;
    String[] staticOrders;
    public void combination(int start, int cnt, int R, int idx) {
    	if(cnt == R) {
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < R; i++) {
				sb.append(selected[i]);
			}
    		if(set.contains(sb.toString())) {
    			list.add(sb.toString());
    		}
    		else set.add(sb.toString());
    		return;
    	}
    	
    	for (int i = start; i < N; i++) {
    		selected[cnt] = staticOrders[idx].charAt(i);
			combination(i+1, cnt+1, R, idx);
    	}
    }
    public static void main(String[] args) {
		System.out.println(Arrays.toString(new 메뉴리뉴얼().solution(
				new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, 
				new int[] {2,3,4}
		)));
	}
}
