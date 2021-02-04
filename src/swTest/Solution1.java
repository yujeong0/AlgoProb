package swTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution1 {
	
	static List<int[]> people;
	static List<int[]> exit;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			int N = Integer.parseInt(br.readLine());
			people = new ArrayList<>();
			exit = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1) {
						people.add(new int[] {i, j});
					}
					else if(num == 2) {
						exit.add(new int[] {i, j});
					}
				}
			}
			
			numbers = new int[people.size()];
			selected = new boolean[people.size()];
			
			combi(0);
			
			sb.append("#").append(testcase).append(" ").append(MIN).append("\n");
		} //tc
	
		System.out.println(sb.toString());
	} //main
	
	static int[] numbers;
	static boolean[] selected;
	static int MIN;
	static void combi(int cnt) {
		if(cnt == people.size()) {
			for (int i = 0; i < selected.length; i++) {
				if(selected[i])	numbers[i] = 1;
				else numbers[i] = 2;
			}

			MIN = Math.min(MIN, solve());
			return;
		}
		
		selected[cnt] = true;
		combi(cnt+1);
		selected[cnt] = false;
		combi(cnt+1);
	}
	
	private static int solve() {
		PriorityQueue<Integer> q1 = new PriorityQueue<>();
		PriorityQueue<Integer> q2 = new PriorityQueue<>();
		
		int[] exitPos;
		int dist;
		for (int i = 0; i < people.size(); i++) {
			exitPos = exit.get(numbers[i]-1);
			dist = Math.abs(people.get(i)[0]-exitPos[0]) + Math.abs(people.get(i)[1]-exitPos[1]);
			switch(numbers[i]) {
			case 1:
				q1.offer(dist);
				break;
			case 2:
				q2.offer(dist);
				break;
			}
		}
		
		int sum1 = 0;
		if(q1.size() > 0) {
			sum1 = q1.poll()+1;
			int val;
			while(!q1.isEmpty()) {
				val = q1.poll();
				if(sum1 > val) {
					sum1++;
				}
				else {
					sum1 = val+1;
				}
			}
		}
		
		int sum2 = 0;
		if(q2.size() > 0) {
			sum2 = q2.poll()+1;
			int val;
			while(!q2.isEmpty()) {
				val = q2.poll();
				if(sum2 > val) {
					sum2++;
				}
				else {
					sum2 = val+1;
				}
			}
		}
		
		return Math.max(sum1, sum2);
	}
}
