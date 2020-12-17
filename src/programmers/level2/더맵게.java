package programmers.level2;

import java.util.PriorityQueue;

public class 더맵게 {
    public int solution(int[] scoville, int K) {
    	PriorityQueue<Integer> q = new PriorityQueue<>();
    	for (int i = 0; i < scoville.length; i++) {
			q.offer(scoville[i]);
		}
    	int a, b, answer = 0;
    	while(!q.isEmpty()) {
    		if(q.peek() >= K) break;
    		if(q.size() <= 1 && q.peek() < K) {
    			return -1;
    		}
    		
			a = q.poll();
			b = q.poll();
			q.offer(a + b*2);
			answer++;
    	}
        return answer;
    }
    public static void main(String[] args) {
    	new 더맵게().service();
	}
    public void service() {
    	System.out.println(solution(new int[] {1,2}, 7));
    }
}
