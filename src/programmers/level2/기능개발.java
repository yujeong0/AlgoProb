package programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {
	
	public static int[] solution(int[] progresses, int[] speeds) {
		int N = progresses.length;
		int[] days = new int[101];
		boolean[] end = new boolean[101];
		
		Queue<int[]> queue = new LinkedList<>();
		List<Integer> waitList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			queue.offer(new int[] {i, 100-progresses[i], 0});
		}
		
		int[] progress;
		while(!queue.isEmpty()) {
			progress = queue.poll();
			progress[1] -= speeds[progress[0]];
			progress[2]++;
			if(progress[1] > 0) {
				queue.offer(progress);
			}
			else {
				if(progress[0] == 0 || end[progress[0]-1]) {
					end[progress[0]] = true;
					days[progress[2]]++;
					
					for(int i = waitList.size()-1; i >= 0; i--) {
						int num = waitList.get(i);
						if(num > progress[0]) {
							boolean pass = true;
							for (int j = num-1; j >= 0; j--) {
								if(!end[j]) {
									if(!waitList.contains(j)) {
										pass = false;
										break;
									}
								}
 							}
							if(pass) {
								days[progress[2]]++;
								waitList.remove(i);
								end[num] = true;
							}
						}
					}
				}
				else {
					waitList.add(progress[0]);
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			if(days[i] == 0) continue;
			list.add(days[i]);
		}
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
        return answer;
    }
	
	public static void main(String[] args) {
		solution(new int[] {95, 90, 99, 99, 80, 99}, new int[] {1, 1, 1, 1, 1, 1});
	}
	
}
