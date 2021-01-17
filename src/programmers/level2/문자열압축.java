package programmers.level2;

import java.util.HashMap;
import java.util.Map;

public class 문자열압축 {
    public int solution(String s) {
        int answer = 0;
        
        for (int len = 0; len < s.length(); len++) {
			cut(len, s);
		}
        return answer;
    }
    
    public void cut(int len, String s) {
    	Map<String, Integer> map = new HashMap<>();
    	for (int i = 0; i < s.length(); i+=len) {
    		String cur;
    		if(i+len <= s.length()) {
    			cur = s.substring(i, i+len);
    		}
    		else cur = s.substring(i);
    		
    		map.put(cur, 1);
    		int j = i+len;
    		int cnt = 0;
    		String next;
    		while(true) {
    			if(j+len <= s.length()) {
    				next = s.substring(j, j+len);
        		}
        		else next = s.substring(j);
    			
				if(cur.equals(next)) {
					map.replace(cur, map.get(cur)+1);
					cnt++;
				}
				else break;
			}
    		
    		for (int k = 0; k < cnt; k++) {
				i += len;
			}
		}
    }
    
    void getLength(Map<String, Integer> map) {
    	StringBuilder sb = new StringBuilder();
    	
    }
    
}
