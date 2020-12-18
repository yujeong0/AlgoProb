package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 위장 {
    public int solution(String[][] clothes) {
    	Map<String, List<String>> map = new HashMap<>();
    	int N = clothes.length;
    	List<String> group;
    	for (int i = 0; i < N; i++) {
			group = map.get(clothes[i][1]);
    		if(group == null) {
    			List<String> list = new ArrayList<>();
    			list.add(clothes[i][0]);
    			map.put(clothes[i][1], list);
			}
    		else {
    			group.add(clothes[i][0]);
    		}
		}
    	
    	int answer = 1;
    	for (List<String> items : map.values()) {
    		int len = items.size();
    		answer *= len+1;
    	}
    	
        return answer-1;
    }
    public static void main(String[] args) {
		new 위장().service();
	}
    public void service() {
    	System.out.println(solution(new String[][] {
    		{"yellow_hat", "headgear"}, 
    		{"blue_sunglasses", "eyewear"}, 
    		{"green_turban", "headgear"}
    	}
    	));
    }
}
