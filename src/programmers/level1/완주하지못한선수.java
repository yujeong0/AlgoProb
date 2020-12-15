package programmers.level1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 완주하지못한선수 {
	
	public static void main(String[] args) {
		new 완주하지못한선수().service();
	}
	
	public void service() {
		System.out.println(solution(new String[] {"mislav", "stanko", "mislav", "ana"}, new String[] {"stanko", "ana", "mislav"}));
	}
	public String solution(String[] participant, String[] completion) {
    	Map<String, Integer> map = new HashMap<>();
    	int N = participant.length;
    	for (int i = 0; i < N; i++) {
    		Integer cnt = map.get(participant[i]);
			if(cnt != null) {
				map.replace(participant[i], cnt, cnt+1);
			}
			else {
				map.put(participant[i], 1);
			}
		}
    	
    	for (int i = 0; i < N-1; i++) {
    		Integer cnt = map.get(completion[i]);
    		if(cnt > 1) {
    			map.replace(completion[i], cnt, cnt-1);
    		}
    		else {
    			map.remove(completion[i]);
    		}
		}
    	
    	Set<String> set = map.keySet();
        String answer = set.toArray()[0].toString();
        return answer;
    }
}
