package summercoding2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {
    public int[] solution(int[] t, int[] r) {
        // 손님번호별 도착시간
        PriorityQueue<int[]> arriveQ = new PriorityQueue<>(new Comparator<int[]> () {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
        });
        for (int i = 0; i < t.length; i++) {
        	arriveQ.offer(new int[] {i, t[i]});
        }
        
        // 리프트 대기 큐 손님 등급 좋은 순대로 정렬 int[] = {손님아이디, 도착시간, 등급}
        PriorityQueue<int[]> waitQ = new PriorityQueue<>(new Comparator<int[]> () {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		if(o1[2] == o2[2]) {
        			if(o1[1] == o2[1])
        				return o1[0]-o2[0];
        			return o1[1]-o2[1];
        		}
        		return o1[2]-o2[2];
        	}
        });

        int[] answer = new int[t.length];
        int ansIdx = 0;
        int curLift = 0;
        while(ansIdx < t.length) {
	        while(!arriveQ.isEmpty()) {
	        	if(arriveQ.peek()[1] <= curLift) {
	        		int[] arr = arriveQ.poll();
	        		waitQ.offer(new int[] {arr[0], curLift, r[arr[0]]});
	        	}
	        	else break;
	        }
	        
	        if(!waitQ.isEmpty()) {
	        	answer[ansIdx++] = waitQ.poll()[0];
	        }
	        
	        curLift++;
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution2().solution(
				new int[] {0,1,3,0}, new int[] {0,1,2,3})));
	}
}
