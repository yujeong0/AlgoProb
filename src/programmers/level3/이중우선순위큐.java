package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 이중우선순위큐 {
    public int[] solution(String[] operations) {
    	PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->(o2-o1));
    	
    	for (int i = 0; i < operations.length; i++) {
    		switch(operations[i].charAt(0)) {
    		case 'I':
    			pq.offer(Integer.parseInt(operations[i].substring(2)));
    			break;
    		case 'D':
    			if(operations[i].charAt(2)-'0' == 1) {
    				pq.poll();
    			}
    			else {
    				int size = pq.size();
    				List<Integer> list = new ArrayList<>();
    				for (int q = 0; q < size-1; q++) {
						list.add(pq.poll());
					}
    				pq.poll();
    				for (int j = 0; j < list.size(); j++) {
						pq.add(list.get(j));
					}
    			}
    			break;
    		}
		}
    	
    	if(pq.size() == 0) return new int[] {0,0};
        int[] answer = new int[2];
        answer[0] = pq.peek();
        int last = 0;
        while(!pq.isEmpty()) {
        	last = pq.poll();
        }
        answer[1] = last;
        return answer;
    }
    public static void main(String[] args) {
		new 이중우선순위큐().service();
	}
    public void service() {
    	System.out.println(Arrays.toString(solution(new String[] {"I 7","I 5","I -5","D -1"})));
    }
}
