package programmers.level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {
	static class Job implements Comparable<Job> {
		int request, totalTime;

		public Job(int request, int totalTime) {
			this.request = request;
			this.totalTime = totalTime;
		}

		@Override
		public int compareTo(Job o) {
			return this.totalTime-o.totalTime;
		}
	}
	
	public int solution(int[][] jobs) {
    	int N = jobs.length;
    	PriorityQueue<Job> q = new PriorityQueue<>();
		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
    	
		int total = 0,second = 0,idx = 0;
		Job j;
		while(true) {
			if(idx == N && q.isEmpty()) break;
			while(idx < N && jobs[idx][0] <= second) {
				q.offer(new Job(jobs[idx][0],jobs[idx][1]));
				idx++;
			}
			if(q.isEmpty()) {
				second = jobs[idx][0];
			}
			else {
				j = q.poll();
				total += second + j.totalTime - j.request;
				second += j.totalTime;
			}
		}
		
        return total / N;
    }
    
	public static void main(String[] args) {
		new 디스크컨트롤러().service();
	}
	public void service() {
		System.out.println(solution(new int[][] {{100,100},{1000,1000}}));
	}
}
