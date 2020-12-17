package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 최댓값과최솟값 {
    public String solution(String s) {
    	StringTokenizer st= new StringTokenizer(s, " ");
    	List<Integer> list = new ArrayList<>();
    	while(st.hasMoreTokens()) {
    		list.add(Integer.parseInt(st.nextToken()));
    	}
    	int N = list.size();
    	
    	Collections.sort(list);
    	
        String answer = list.get(0) + " " + list.get(N-1);
        return answer;
    }
}
