package programmers.level2;

import java.util.HashMap;
import java.util.Map;

public class 위장 {
    public int solution(String[][] clothes) {
    	Map<String, Integer> map = new HashMap<>();
    	int N = clothes.length;
    	String group;
    	for (int i = 0; i < N; i++) {
			group = clothes[i][1];
    		if(map.containsKey(group)) {
    			map.put(group, map.get(group)+1);
			}
    		else {
    			map.put(group, 1);
    		}
		}
    	
    	int answer = 1;
    	for (int n : map.values()) {
    		answer *= n+1;
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
