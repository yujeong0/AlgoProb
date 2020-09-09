package kakao.blind2019;

import java.util.LinkedList;
import java.util.Queue;

public class 무지의먹방라이브 {
	static class Food{
		int foodNum;
		int quan;
		Food(int foodNum, int quan) {
			this.foodNum = foodNum;
			this.quan = quan;
		}
	}
	
	static Queue<Food> q = new LinkedList<>();
	
	public static void main(String[] args) {
		int[] food_times = { 3,1,2 };
		long k = 5;
		
		System.out.println(solution(food_times, k));
	}
	
	public static int solution(int[] food_times, long k) {
		int foodsCount = 0;
		for (int i = 0; i < food_times.length; i++) {
			foodsCount += food_times[i];
			q.offer(new Food(i+1, food_times[i]));
		}
		if(foodsCount <= k) return -1;

		if(food_times.length >= k) return (int)k;
		
		long second = 0;
		Food food;
        while(true) {
        	if(second == k) break;
        	food = q.poll();
        	if(food.quan != 1) {
        		food.quan--;
        		q.offer(food);
        	}
        	second++;
        }
        
        return q.poll().foodNum;
    }
}
