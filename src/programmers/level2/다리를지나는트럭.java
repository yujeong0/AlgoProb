package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
	static class Truck {
		int weight, time;

		public Truck(int weight, int time) {
			this.weight = weight;
			this.time = time;
		}
	}
	
    public int solution(int bridge_length, int weight, int[] truck_weights) {
    	Queue<Truck> waitQ = new LinkedList<>();
    	Queue<Truck> bridgeQ = new LinkedList<>();
    	
    	for (int i = 0; i < truck_weights.length; i++) {
			waitQ.offer(new Truck(truck_weights[i], bridge_length));
		}
    	
    	Truck t;
    	int time = 1;
    	int curWeight = 0;
    	while(!waitQ.isEmpty() || !bridgeQ.isEmpty()) {
    		int size = bridgeQ.size();
    		while(size > 0) {
    			t = bridgeQ.poll();
    			if(t.time == 0)
    				curWeight -= t.weight;
    			else if(t.time > 0) {
    				bridgeQ.offer(new Truck(t.weight, t.time-1));
    			}
    			size--;
    		}
    		
    		if(!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
    			t = waitQ.poll();
    			bridgeQ.offer(new Truck(t.weight, t.time-1));
    			curWeight += t.weight;
    		}
    		
    		if(waitQ.isEmpty() && bridgeQ.isEmpty()) break;
    		
    		time++;
    	}
    	
        return time;
    }
    
	public static void main(String[] args) {
		new 다리를지나는트럭().service();
	}
	public void service() {
		System.out.println(solution(2, 10, new int[] {7,4,5,6}));
	}
    
}
