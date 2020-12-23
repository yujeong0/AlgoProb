package programmers.level2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class 프린터 {
    public int solution(int[] priorities, int location) {
    	Queue<int[]> q = new LinkedList<>();
    	for (int i = 0; i < priorities.length; i++) {
			q.offer(new int[] {i, priorities[i]});
		}

    	int[] front;
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		front = q.poll();
    		
    		Iterator iter = q.iterator();
    		boolean in = false;
    		while(iter.hasNext()) {
    			if(front[1] < ((int[])iter.next())[1]) {
    				q.offer(new int[] {front[0], front[1]});
    				in = true;
    				break;
    			}
    		}
    		
    		if(!in) {
    			if(front[0] == location) return cnt;
    			cnt++;
    		}
    	}
    	
    	return -1;
    }
    
    public static void main(String[] args) {
		new 프린터().service();
	}
    public void service() {
    	System.out.println(solution(new int[] {2,1,3,2}, 2));
    }
}
